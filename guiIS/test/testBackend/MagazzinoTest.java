package testBackend;

import backend.businessLogicLayer.Magazzino;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.fail;

class MagazzinoTest {
    private Magazzino magazzino;
    private TriggersTest t;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stat;


    @BeforeEach
    void setUp() {

        try {

            t=new TriggersTest();
            conn=t.getConn();
            t.initDataBase();
            //magazzino=new Magazzino();

        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

    }


    @Test
    void aggiungiScatole() {


    }

    @Test
    void rimuoviCialde() {
    }


    @AfterEach
    void tearDown() throws SQLException {
        t.closeConnection();
    }


}