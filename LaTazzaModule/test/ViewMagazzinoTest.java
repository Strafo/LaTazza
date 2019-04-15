import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Euro;

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
    private static int qtaCaffe=162;


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
            while (rs.next()) {
                switch (rs.getString(1)) {
                    case "caffe":
                        assertEquals(qtaCaffe, rs.getInt(2));
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
                stat.executeUpdate();
                executeSelect();
                String the= new String("the");
                while(rs.next()){
                    assertFalse(the.equals(rs.getString(1)));
                }
            } catch (SQLException e) {
                fail(e.getMessage());
            }

        }

    @Test
    void testRifornimento(){
        try {
            int qta=40;
            stat=c.prepareStatement("insert into LATAZZASCHEMA.RIFORNIMENTO values ('2019-05-12 12:00:00',"+qta+" ,'caffe')" );
            stat.executeUpdate();
            executeSelect();
            while (rs.next()) {
                switch (rs.getString(1)) {
                    case "caffe":
                        assertEquals(qtaCaffe+qta, rs.getInt(2));
                        break;
                }
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }

    }






}
