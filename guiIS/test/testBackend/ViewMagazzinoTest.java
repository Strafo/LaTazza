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

    private TriggersTest t;
    private Connection c;
    private static ResultSet rs;
    private static PreparedStatement stat;

    @BeforeEach
    void setUp(){
        try {
            t= new TriggersTest();
            c=t.getConn();
        }catch (Exception exc){
            fail(exc.getMessage());
        }
    }




    @Test
    void testView() {
        try {
            t.initDataBase();
            stat=c.prepareStatement("SELECT * from LATAZZASCHEMA.MAGAZZINO");
            rs=stat.executeQuery();
            while(rs.next()){
                switch (rs.getString(1)) {
                    case "caffe": assertEquals(162,rs.getInt(2));
                }
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }

    }






}
