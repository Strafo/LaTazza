package testBackend;
import backend.clientpkg.Personale;
import backend.clientpkg.Visitatore;
import backend.daopkg.gateways.Dao;
import backend.daopkg.gateways.PersonaleDao;
import backend.daopkg.rowdatapkg.*;
import database.DataBase;
import org.junit.jupiter.api.*;
import utils.LaTazzaLogger;

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
 * ATTENZIONE: se cambia il file DefaultSetEntry.sql deve cambiare anche nEntry                     <----------------------------------------------------------------------------------
 */
public class DaoTest{


    private Dao<Personale> dao;
    private DataBase database;
    private Integer[] nEntry={14,3,3,4,5,3,2};//numero di inserimetni per le tabelle(in ordine):personale,cialde,visitatore,rifornimento,pagamento_debito,compra visitatore,compra dipendente

    /**
     * Inizializza il logger per il database.
     * Tutti i log si trovano nel file ./LaTazza.log
     */
    @BeforeAll
    static void setUpLogger(){
        LaTazzaLogger.initLogger(false);
    }

    /**
     * Carica il database con le tabelle necessarie.
     * Le def. di quest tab sono nello script guiIS/src/database/config/databaseConfig.sql
     */
    @BeforeEach
    void setUp() {
        database=new DataBase("jdbc:h2:mem:databaseTest");//database per il testing :mem (in memory)
        try {
            database.initDataBase();
        } catch ( SQLException | ClassNotFoundException e) {
            fail(e.getMessage());
        }
        updateTable("guiIS/src/database/config/databaseConfig.sql");//DATABASE CONFIG SQL FILE
        updateTable("guiIS/src/database/config/DafualtEntrySet.sql");//inserisco un po di personale per il testing
        this.dao=new PersonaleDao(database.getConnection());
    }

    @AfterEach
    void tearDown(){
        try {
            database.closeDataBase();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test//todo renderlo paramentrico per il path di updateTable da seguire
    void getAllTest() {
        List list=dao.getAll();
        printList(list);
        checkNEntry(Object.class,0,list.size());
    }

    @Test
    void saveTest()  {
        assertTrue(dao.save(new Personale("zio","pino",true)));
        assertTrue(dao.save(new Personale("ciccio","pasticcio",false)));

        List list=dao.getAll();
        checkNEntry(Object.class,2,list.size());


    }


    @Test
    void updateTest() {
        /*Personale pers=new Personale("andrea","straforini",true);
        //dao.update(pers,new Personale("andrea","straforini",false));TODO

        List list=dao.getAll();

        for(Object i:list){
            if(((Personale)i).getCognome().equals("straforini")&&((Personale)i).getNome().equals("andrea")) {
                assertFalse(((Personale)i).isAttivo());
            }
        }
        assertEquals(numInserimentiTupleDB,list.size());

*/
    }

    @Test
    void deleteTest(){
        //viola vincolo pagamento debito-->assertfalse Questa delete lancia uneccezione ma è giusto così! violerebbe il constraint di LATAZZASCHEMA.PAGAMENTO_DEBITO
        assertFalse(dao.delete(new Personale("andrea","straforini",true)));

        assertTrue(dao.delete(new Personale("simone","mirto",true)));

        List list=dao.getAll();

        for(Object i:list){
            if(((Personale)i).getCognome().equals("mirto")&&((Personale)i).getNome().equals("simone")) {
                fail("Tupla non eliminata.");
            }
        }
        checkNEntry(Object.class,-1,list.size());


    }


    void updateTable(String sqlFilePath) {

        String currentDirPath = System.getProperty("user.dir");
        String[] pathPart=currentDirPath.split("LaTazza/guiIS");//tapullata incredibile :D
        String prjPath=pathPart[0]+"LaTazza/";

        //PER IL DEBUGGING
        /*System.out.println("Current dir using System:" +currentDirPath);
        for(String i:pathPart) {
            System.out.println("part:" +i);
        }fail("END");*/

        try {
            //legge il file sql e lo suddivide in query
            File file = new File(prjPath+sqlFilePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            String[] parts=str.split(";");

            //PER DEBUGGING
            /*System.out.println("START:");int j=0;
            for(String i:parts) {
                System.out.println("part["+j+"]:" +i);
                j++;
            }
            System.out.println("END");*/

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


    void checkNEntry(Class<?>cls,int expectedSizeOffset,int actualSize){

        if(cls==Personale.class){
            assertEquals(nEntry[0]+expectedSizeOffset,actualSize);
        }
        if(cls==CialdeEntry.class){
            assertEquals(nEntry[1]+expectedSizeOffset,actualSize);
        }
        if(cls==Visitatore.class){
            assertEquals(nEntry[2]+expectedSizeOffset,actualSize);
        }
        if(cls==RifornimentoEntry.class){
            assertEquals(nEntry[3]+expectedSizeOffset,actualSize);
        }
        if(cls==PagamentoDebitoEntry.class){
            assertEquals(nEntry[4]+expectedSizeOffset,actualSize);
        }
        if(cls==CompraVisitatoreEntry.class){
            assertEquals(nEntry[5]+expectedSizeOffset,actualSize);
        }
        if(cls==CompraDipendenteEntry.class){
            assertEquals(nEntry[6]+expectedSizeOffset,actualSize);
        }

    }



    void printList(List l){
        for(Object i:l){
            System.out.println(i.toString());
        }
    }

}

