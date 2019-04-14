package testBackend;

import backend.businessLogicLayer.Magazzino;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.*;

class MagazzinoTest {
    private Magazzino magazzino;
    private TriggersTest t;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stat;
    private CialdeEntry caffe;
    private int qtaScatole;

    @BeforeEach
    void setUp() {

        try {

            t=new TriggersTest();
            conn=t.getConn();
            t.initDataBase();
            magazzino=null;
            qtaScatole=magazzino.getQtaCialdeScatole();
            caffe= new CialdeEntry("caffe");

        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

    }


    @Test
    void aggiungiScatole() {

        Integer oldQta= magazzino.getCopyStato().get(caffe);
        assertTrue(magazzino.aggiungiScatole(caffe,3));
        int newQta=oldQta+(qtaScatole*3);
        int getNewQta= magazzino.getCopyStato().get(caffe);
        assertEquals(getNewQta, newQta );
    }

    @Test
    void rimuoviCialde() {
        Integer oldQta= magazzino.getCopyStato().get(caffe);
        assertTrue(magazzino.rimuoviCialde (caffe,1));
        int newQta=oldQta-qtaScatole;
        int getNewQta= magazzino.getCopyStato().get(caffe);
        assertEquals(getNewQta, newQta );
    }


    @AfterEach
    void tearDown() throws SQLException {
        t.closeConnection();
    }


}