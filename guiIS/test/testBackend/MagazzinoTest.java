package testBackend;

import backend.businessLogicLayer.Magazzino;
import backend.dataAccessLayer.gatewaysPkg.DaoInvoker;
import backend.dataAccessLayer.gatewaysPkg.IDaoFacade;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.MagazzinoEntry;
import backend.database.DatabaseConnectionHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;

class MagazzinoTest {
    private Magazzino magazzino;
    private CialdeEntry caffe;
    private int qtaScatole;
    private TriggersTest t;
    private Connection conn;
    private LaTazzaApplication app;
    private Map<CialdeEntry,Integer> stato;

    @BeforeEach
    void setUp() {


        try {

            t=new TriggersTest();
            t.initDataBase();
            conn=t.getConn();
            //dao= new DaoInvoker(conn, LaTazzaApplication.daoCollection);
            magazzino=new Magazzino(LaTazzaApplication.dao);
            qtaScatole=magazzino.getQtaCialdeScatole();

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
    void tearDown(){
        try {
            t.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}