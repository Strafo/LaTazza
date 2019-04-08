package testBackend;

import org.junit.jupiter.api.AfterEach;
import utils.Euro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class ViewDebitoTest {

    private static final  String TABLE="LATAZZASCHEMA.DEBITO";
    private TriggersTest t;
    private Connection c;
    private static ResultSet rs;
    private static PreparedStatement stat;
    private static int debitoEuro=3;
    private static int debitoCentesimi=51;
    private static Euro debitoAfterInsert;
    private static Euro debitoNullo;


    @AfterEach
    void tearDown(){

        try {
            t.closeConnection();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @BeforeEach
    void setUp(){
        debitoNullo= new Euro(0,0);
        debitoAfterInsert= new Euro(debitoEuro,debitoCentesimi);
        try {
            t= new TriggersTest();
            c=t.getConn();
            t.initDataBase();
            executeSelect();
        }catch (Exception exc){
            fail(exc.getMessage());
        }
    }


    private void executeSelect() throws SQLException{
        stat=c.prepareStatement("SELECT * from " + TABLE);
        rs=stat.executeQuery();
    }
    @Test
    void testView() {
        try {
            checkDebito();
        } catch (SQLException e) {
            //e.printStackTrace();
            fail(e.getMessage());
        }
    }


    @Test
    void testVenditaCredito(){
        try {

            int qta=2;
            Euro importo= new Euro(0,50);
            importo.moltiplicaImporto(qta);
            stat=c.prepareStatement("insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi', 'caffe',"+qta+",'2018-07-11 13:00:00',false)");
            stat.executeUpdate();
            debitoNullo.aggiungiImporto(importo);
            executeSelect();
            while(rs.next()) {
                String cognome= rs.getString("cognome");
                System.out.println(cognome);
                    if(cognome.equals("Dapueto")) {
                        assertEquals(debitoAfterInsert.getEuro(), rs.getInt("euro"));
                        assertEquals(debitoAfterInsert.getCentesimi(), rs.getInt("centesimi"));
                    }
                    else
                        if(cognome.equals("Campisi")) {
                            assertEquals(debitoNullo.getEuro(), rs.getInt("euro"));
                            assertEquals(debitoNullo.getCentesimi(), rs.getInt("centesimi"));
                        }
                        else {

                            assertEquals(( new Euro(0,0)).getEuro(), rs.getInt("euro"));
                            assertEquals(( new Euro(0,0)).getCentesimi(), rs.getInt("centesimi"));
                        }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @Test
    void testPagamentoDebito(){
        try {

            Euro importo= new Euro(3,50);
            stat=c.prepareStatement("insert into LATAZZASCHEMA.PAGAMENTO_DEBITO (NOME, COGNOME, DATA, EURO, CENTESIMI) values ('Jacopo','Dapueto', '2019-03-11 14:00:00',"+importo.getEuro()+", "+importo.getCentesimi()+")" );
            stat.executeUpdate();
            debitoAfterInsert.sottraiImporto(importo);
            executeSelect();
            checkDebito();
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    private void checkDebito() throws SQLException {

        while (rs.next()) {
            String cognome=rs.getString("cognome");

            if(cognome.equals("Dapueto")) {
                assertEquals(debitoAfterInsert.getEuro(), rs.getInt("euro"));
                assertEquals(debitoAfterInsert.getCentesimi(),  rs.getInt("centesimi"));
            }
            else {

                assertEquals(debitoNullo.getEuro(), rs.getInt("euro"));
                assertEquals(debitoNullo.getCentesimi(),rs.getInt("centesimi"));
            }

        }
    }


}
