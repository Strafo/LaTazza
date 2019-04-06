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


public class ViewCassaTest {

    private static final  String TABLE="LATAZZASCHEMA.CASSA";
    private TriggersTest t;
    private Connection c;
    private static ResultSet rs;
    private static PreparedStatement stat;
    private static int cassaEuro=385;
    private static int cassaCentesimi=49;
    private static Euro cassaAfterInsert= new Euro(cassaEuro,cassaCentesimi);

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

            assertTrue(rs.next());
            assertEquals(cassaAfterInsert.getEuro(), rs.getInt(1) );
            assertEquals(cassaAfterInsert.getCentesimi(), rs.getInt(2));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }


    @Test
    void testRifornimento(){
        try {
            int qta=40;
            Euro importo= new Euro(0,40);
            importo.moltiplicaImporto(qta);
            cassaAfterInsert.sottraiImporto(importo);
            stat=c.prepareStatement("insert into LATAZZASCHEMA.RIFORNIMENTO values ('2019-05-12 12:00:00',"+qta+" ,'caffe')" );
            stat.executeUpdate();
            executeSelect();
            assertTrue(rs.next());
            assertEquals(cassaAfterInsert.getEuro(), rs.getInt(1) );
            assertEquals(cassaAfterInsert.getCentesimi(), rs.getInt(2));
        } catch (SQLException e) {
            fail(e.getMessage());
        }

    }


    @Test
    void testPagamentoDebito(){
        try {
            Euro importo= new Euro(3,50);
            stat=c.prepareStatement("insert into LATAZZASCHEMA.PAGAMENTO_DEBITO values ('Jacopo','Dapueto', '2019-03-11 14:00:00',"+importo.getEuro()+", "+importo.getCentesimi()+")" );
            stat.executeUpdate();
            cassaAfterInsert.aggiungiImporto(importo);
            executeSelect();
            assertTrue(rs.next());
            assertEquals(cassaAfterInsert.getEuro(), rs.getInt(1) );
            assertEquals(cassaAfterInsert.getCentesimi(), rs.getInt(2));
        } catch (SQLException e) {
            fail(e.getMessage());
        }

    }

    @Test
    void testVendita(){
        try {
            int qta=2;
            Euro importo= new Euro(0,50);
            importo.moltiplicaImporto(qta);
            stat=c.prepareStatement("insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi', 'caffe',"+qta+",'2018-07-11 13:00:00',true)");
            stat.executeUpdate();
            cassaAfterInsert.aggiungiImporto(importo);
            executeSelect();
            assertTrue(rs.next());
            assertEquals(cassaAfterInsert.getEuro(), rs.getInt(1) );
            assertEquals(cassaAfterInsert.getCentesimi(), rs.getInt(2));
        } catch (SQLException e) {
            fail(e.getMessage());
        }

    }







}
