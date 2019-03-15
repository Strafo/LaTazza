package testBackend.daoTest;

import backend.Personale;
import backend.daopkg.Dao;
import backend.daopkg.Database;
import backend.daopkg.PersonaleDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Questo tester crea un database h2 di tipo in memory.
 * Quindi è necessario configurarlo con le tabelle necessarie e i valori per il testing.
 * Successivamente si può eseguire il testing con il database temporaneo appena creato.
 * Essendo di tipo in memory quando l'ultima connessione viene chiusa il database viene "pulito"
 */
public class DaoTest{


    private Dao dao;
    private Database database;
    private int numInserimentiTupleDB;//controlliamo il numero di risultati per semplicità. Il controllo dei valori si fa "visualmente"


    @BeforeEach
    /**
     * Carica il database con le tabelle necessarie.
     * Le def. di quest tab sono nello script guiIS/src/database/config/databaseConfig.sql
     */
    void setUp() {
        database=new Database();
        try {
            database.createDatabaseConnection("jdbc:h2:mem:databaseTest");//database per il testing :mem (in memory)
        } catch (Database.DataBaseCreationException e) {
            fail(e.getMessage());
        }
        dao=new PersonaleDao(database);
        updateTable("guiIS/src/database/config/databaseConfig.sql");//DATABASE CONFIG SQL FILE

    }

    @AfterEach
    void tearDown(){
        try {
            database.closeConnection();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test//todo renderlo paramentrico per il path di updateTable da seguire
    void getAllTest() {
        updateTable("guiIS/src/database/config/personaleDafualtSet.sql");//inserisco un po di personale per il testing
        List list=dao.getAll();
        for(Object i:list){
            System.out.println(i.toString());//testing "visuale"
        }
        assertEquals(numInserimentiTupleDB,list.size());
    }

    @Test
    void saveTest()  {
        updateTable("guiIS/src/database/config/personaleDafualtSet.sql");//inserisco un po di personale per il testing
        dao.save(new Personale("zio","pino",true));
        dao.save(new Personale("ciccio","pasticcio",false));

        List list=dao.getAll();
        assertEquals(numInserimentiTupleDB+2,list.size());

    }


    @Test
    void updateTest() {
        updateTable("guiIS/src/database/config/personaleDafualtSet.sql");//inserisco un po di personale per il testing
        Personale pers=new Personale("andrea","straforini",true);
        dao.update(pers,new Personale("andrea","straforini",false));

        List list=dao.getAll();

        for(Object i:list){
            if(((Personale)i).getCognome().equals("straforini")&&((Personale)i).getNome().equals("andrea")) {
                assertFalse(((Personale)i).isAttivo());
            }
        }
        assertEquals(numInserimentiTupleDB,list.size());


    }

    @Test
    void deleteTest(){
        updateTable("guiIS/src/database/config/personaleDafualtSet.sql");//inserisco un po di personale per il testing
        dao.delete(new Personale("andrea","straforini",true));

        List list=dao.getAll();

        for(Object i:list){
            if(((Personale)i).getCognome().equals("straforini")&&((Personale)i).getNome().equals("andrea")) {
                fail("Tupla non eliminata.");
            }
        }
        assertEquals(numInserimentiTupleDB-1,list.size());

    }


    void updateTable(String sqlFilePath) {

        String currentDirPath = System.getProperty("user.dir");
        String[] pathPart=currentDirPath.split("LaTazza/guiIS");//tapullata incredibile :D
        String prjPath=pathPart[0]+"LaTazza/";

        //PER IL DEBUGGING
        /*System.out.println("Current dir using System:" +currentDirPath);
        for(String i:pathPart) {
            System.out.println("part:" +i);
        }
        fail("END");*/


        try {
            //legge il file sql e lo suddivide in query
            File file = new File(prjPath+sqlFilePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            String[] parts=str.split(";");

            /*PER DEBUGGING
            System.out.println("START:");
            int j=0;
            for(String i:parts) {
                System.out.println("part["+j+"]:" +i);
                j++;
            }
            System.out.println("END");
            */


            this.numInserimentiTupleDB=parts.length;
            Connection conn = database.createAndGetConnection();
            Statement statement = conn.createStatement();
            //esegue le query
            for(String i:parts) {
                statement.execute(i);
            }

        } catch (IOException | SQLException | Database.DataBaseCreationException e) {
            fail(e.getMessage());
        }
    }
}

