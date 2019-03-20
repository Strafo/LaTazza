package testBackend;
import backend.Euro;
import backend.clientpkg.Personale;
import backend.clientpkg.Visitatore;
import backend.daopkg.gateways.Dao;
import backend.daopkg.gateways.DaoInterface;
import backend.daopkg.gateways.DaoManager;
import backend.daopkg.gateways.PersonaleDao;
import backend.daopkg.rowdatapkg.*;
import database.DataBase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.LaTazzaLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Questo tester crea un database h2 di tipo in memory.
 * Quindi è necessario configurarlo con le tabelle necessarie e i valori per il testing.
 * Successivamente si può eseguire il testing con il database temporaneo appena creato.
 * Essendo di tipo in memory quando l'ultima connessione viene chiusa il database viene "pulito"
 * ATTENZIONE: se cambia il file DefaultSetEntry.sql deve cambiare anche nEntry                     <----------------------------------------------------------------------------------
 */
public class DaoTest{


    private DaoInterface dao;
    private DataBase database;
    private Integer[] nEntry={14,4,4,4,5,3,2};//numero di inserimetni del file DefaultSetEntry.sql per le tabelle(in ordine):personale,cialde,visitatore,rifornimento,pagamento_debito,compra visitatore,compra dipendente

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
    }

    @AfterEach
    void tearDown(){
        try {
            database.closeDataBase();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,PagamentoDebitoEntry.class,CompraVisitatoreEntry.class,CompraDipendenteEntry.class})
    void getAllTest(Class<? extends AbstractEntryDB> cls) {
        try {
            dao=new DaoManager(database.getConnection());
            List list=dao.getAll(cls);
            checkNEntry(Object.class,0,list.size());
            printList(list);
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }
    }

    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,PagamentoDebitoEntry.class,CompraVisitatoreEntry.class,CompraDipendenteEntry.class})
    void saveTest(Class<? extends AbstractEntryDB>  cls)  {
        try {
            dao=new DaoManager(database.getConnection());
            assertTrue(dao.save(createInstance(cls,true)));

            List list=dao.getAll(cls);
            checkNEntry(Object.class,2,list.size());
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }

    }


    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,PagamentoDebitoEntry.class,CompraVisitatoreEntry.class,CompraDipendenteEntry.class})
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

    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,PagamentoDebitoEntry.class,CompraVisitatoreEntry.class,CompraDipendenteEntry.class})
    void deleteTest(Class<?>cls){


        try {
            dao=new DaoManager(database.getConnection());
            assertTrue(dao.delete(createInstance(cls,false)));

            List list=dao.getAll(cls);
            checkNEntry(Object.class,-1,list.size());
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }


    }


    private  void updateTable(String sqlFilePath) {

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


    private void checkNEntry(Class<?>cls,int expectedSizeOffset,int actualSize){

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





    private AbstractEntryDB createInstance(Class<?> cls, boolean random) {
        try{
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        if (random) {
            String randomchar = Long.toString((new Date()).getTime());
            if (cls == Personale.class) {
                return new Personale("zio", "pino" + randomchar, true);//get time serve per "randomizzare"
            }
            if (cls == CialdeEntry.class) {
                return new CialdeEntry("caffè" + randomchar, new Euro(0, 5));
            }
            if (cls == Visitatore.class) {
                return new Visitatore("ciccio", "pasticcio" + randomchar);
            }
            if (cls == RifornimentoEntry.class) {
                return new RifornimentoEntry(getRandomDate(), 100, "caffè");
            }
            if (cls == PagamentoDebitoEntry.class) {
                return new PagamentoDebitoEntry("andrea", "straforini", getRandomDate(), new Euro(3, 5));
            }
            if (cls == CompraVisitatoreEntry.class) {
                return new CompraVisitatoreEntry(getRandomDate(), 6, "cioccolata", "salmo", "lebon");
            }
            if (cls == CompraDipendenteEntry.class) {
                return new CompraDipendenteEntry(getRandomDate(), 2, "caffè", "andrea", "straforini", true);
            }
            fail("istanza non riconosciuta");
            return null;
        } else {
            if (cls == Personale.class) {
                return new Personale("andrea", "squillante", true);
            }
            if (cls == CialdeEntry.class) {
                return new CialdeEntry("tortaalgustoditorta", new Euro(0, 5));
            }
            if (cls == Visitatore.class) {
                return new Visitatore("fabri", "fibra");
            }
            if (cls == RifornimentoEntry.class) {
                return new RifornimentoEntry(format.parse("2000-12-31"), 2, "caffè");
            }
            if (cls == PagamentoDebitoEntry.class) {
                return new PagamentoDebitoEntry("andrea", "straforini", format.parse("2019-01-01"), new Euro(3, 666));
            }
            if (cls == CompraVisitatoreEntry.class) {
                return new CompraVisitatoreEntry( format.parse("2019-01-01"), 6, "cioccolata", "salmo", "lebon");
            }
            if (cls == CompraDipendenteEntry.class) {
                return new CompraDipendenteEntry(format.parse("2019-01-01"), 2, "caffè", "andrea", "oneto", true);
            }
            fail("istanza non riconosciuta");

        }
    }catch(Exception e){
        fail(e.toString());
    }
        return null;
    }



    private Date getRandomDate(){//https://stackoverflow.com/questions/3985392/generate-random-date-of-birth
        Random  rnd;
        long    ms;

        // Get a new random instance, seeded from the clock
        rnd = new Random();

        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

        // Construct a date
        return new Date(ms);

    }

    private void printList(List l){
        for(Object i:l){
            System.out.println(i.toString());
        }
    }

}



