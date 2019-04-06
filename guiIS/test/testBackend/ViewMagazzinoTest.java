package testBackend;

import utils.Euro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class ViewMagazzinoTest {

    private static final  String TABLE="LATAZZASCHEMA.MAGAZZINO";
    private TriggersTest t;
    private Connection c;
    private static ResultSet rs;
    private static PreparedStatement stat;

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


    private void executeSelect() throws SQLException{
        stat=c.prepareStatement("SELECT * from " + TABLE);
        rs=stat.executeQuery();
    }


    @Test
    void testView() {
        try {

            executeSelect();
            while (rs.next()) {
                switch (rs.getString(1)) {
                    case "caffe":
                        assertEquals(162, rs.getInt(2));
                        break;
                    case "the":
                        assertEquals(130, rs.getInt(2));
                }
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }


        @Test
        void testUpdate(){
            try {
                executeSelect();
                stat=c.prepareStatement("update " +TABLE+" set tipo='Camomilla' where tipo='the' " );
                rs=stat.executeQuery();
                String the= new String("the");
                while(rs.next()){
                    assertFalse(the.equals(rs.getString(1)));
                }
            } catch (SQLException e) {
                fail(e.getMessage());
            }

        }






}
