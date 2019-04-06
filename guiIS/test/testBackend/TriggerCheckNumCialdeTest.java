package testBackend;

import org.junit.jupiter.api.AfterEach;
import utils.Euro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class TriggerCheckNumCialdeTest {

    private static final  String TABLE="LATAZZASCHEMA.CASSA";
    private TriggersTest t;
    private Connection c;
    private static ResultSet rs;
    private static PreparedStatement stat;
   private static String nome="'Jacopo'";
    private static String cognome="'Dapueto'";
    private static String data="'2019-07-10 9:00:00'";


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
    void testTrigger() {
        try {
            stat=c.prepareStatement("insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ("+nome+","+cognome+", 'caffe',2000,"+data+", false)" );
            stat.executeUpdate();
            assertFalse(isNotDeleted());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }


    private  boolean isNotDeleted() throws SQLException{
        stat= c.prepareStatement("select numero_cialde from LATAZZASCHEMA.COMPRA_DIPENDENTE where contanti=false and nome="+nome+" and cognome="+cognome+" and data="+ data );
        rs= stat.executeQuery();
        return(rs.next());
    }





}
