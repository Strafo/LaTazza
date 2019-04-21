import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class TriggerCompraVisitatoreTest {

    private static final  String TABLE="LATAZZASCHEMA.VISITATORE";
    private TriggersTest t;
    private Connection c;
    private static ResultSet rs;
    private static PreparedStatement stat;

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

        }catch (Exception exc){
            fail(exc.getMessage());
        }
    }

    private boolean executeSelect() throws SQLException{
        stat=c.prepareStatement("SELECT * from " + TABLE + " where nome='Maria' and cognome='giongiangela'");
        rs=stat.executeQuery();
        return rs.next();
    }

    @Test
    void testTrigger() {
        try {
            stat =c.prepareStatement("insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Maria','giongiangela', 'caffe',1,'2018-07-10 9:00:00');");
            stat.executeUpdate();
            assertTrue(executeSelect());

        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}
