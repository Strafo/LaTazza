package testBackend;

import backend.businessLogicLayer.Magazzino;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.fail;

class MagazzinoTest {
    private Magazzino magazzino;
    private TriggersTest t;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stat;
    private Map<CialdeEntry,Integer> stato;
    private CialdeEntry caffe;
    private int qtaScatole;

    @BeforeEach
    void setUp() {

        try {

            t=new TriggersTest();
            conn=t.getConn();
            t.initDataBase();
            stato= new HashMap<>();
            stato.put(new CialdeEntry("caffe", new Euro(0,50)), 300);
            stato.put(new CialdeEntry("LaPastaAlSaleConIPomodoriniDiMelanzane", new Euro(0,50)), 20);
            magazzino=new Magazzino(stato);

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