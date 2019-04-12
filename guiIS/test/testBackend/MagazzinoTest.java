package testBackend;

import backend.businessLogicLayer.Magazzino;
import backend.dataAccessLayer.gatewaysPkg.DaoInvoker;
import backend.dataAccessLayer.gatewaysPkg.IDaoFacade;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.database.DatabaseConnectionHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;
import utils.PathHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class MagazzinoTest {
    private Magazzino magazzino;
    private DatabaseConnectionHandler database;
    private String DATABASECONFIGFILE="/src/backend/database/config/databaseConfig.sql";
    private String DEFAULTDATABASEENTRYSETFILE="/test/testBackend/Insert.sql";
    private String CURRENTWORKINGDIRECTORY;
    private CialdeEntry cialde;
    private IDaoFacade dao;
    private LaTazzaApplication application;




    @BeforeEach
    void setUp() {
        application= new LaTazzaApplication();
        database=new DatabaseConnectionHandler("jdbc:h2:mem:MagazzinoTest");//backend.database per il testing :mem (in memory)

        try {
            database.initDataBase();
        } catch ( SQLException | ClassNotFoundException e) {
            fail(e.getMessage());
        }

        CURRENTWORKINGDIRECTORY=System.getProperty("user.dir");
        String normPath= String.valueOf(CURRENTWORKINGDIRECTORY.charAt(CURRENTWORKINGDIRECTORY.length() - 1));
        if(normPath.equals(PathHandler.getSeparator())){
            CURRENTWORKINGDIRECTORY+=PathHandler.getSeparator();
        }
        System.out.println("Current dir using System:" +CURRENTWORKINGDIRECTORY);

        updateTable(PathHandler.modifyPath(CURRENTWORKINGDIRECTORY+DATABASECONFIGFILE));//DATABASE CONFIG SQL FILE
        updateTable(PathHandler.modifyPath(CURRENTWORKINGDIRECTORY+DEFAULTDATABASEENTRYSETFILE));//inserisco un po di personale per il testing
        dao=new DaoInvoker(database.getConnection(), LaTazzaApplication.daoCollection);
    }


    @Test
    void aggiungiScatole() {
        cialde= new CialdeEntry("caffe", new Euro(0,50));
        if(magazzino.aggiungiScatole(cialde, 20)){
            System.out.println("ciao "+magazzino.getCopyStato().get(cialde));
            //assertTrue(magazzino.getCopyStato().get(cialde) == qta+magazzino.getCopyStato() );
        }

        fail("Errore Aggiunta Scatole");

    }

    @Test
    void rimuoviCialde() {
    }




    private  void updateTable(String sqlFilePath) {
        System.out.println(sqlFilePath);
        try {
            //legge il file sql e lo suddivide in query
            File file = new File(sqlFilePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            String[] parts=str.split(";");


            Connection conn = database.getConnection();
            Statement statement = conn.createStatement();
            //esegue le query
            for(String i:parts) {
                statement.execute(i);
            }

        } catch (IOException | SQLException e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    void tearDown(){
        try {
            database.closeDataBase();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}