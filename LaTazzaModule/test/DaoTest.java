import dataAccessLayer.rowdatapkg.Cassa;
import businessLogicLayer.Debito;
import dataAccessLayer.gatewaysPkg.DaoInvoker;
import dataAccessLayer.gatewaysPkg.IDaoFacade;
import dataAccessLayer.database.DatabaseConnectionHandler;
import businessLogicLayer.commandPkg.InitBackEndCommand;
import utils.Euro;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import dataAccessLayer.rowdatapkg.*;
import dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;
import dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoVendita;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.LaTazzaLogger;
import utils.PathHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Questo tester crea un backend.database h2 di tipo in memory.
 * Quindi è necessario configurarlo con le tabelle necessarie e i valori per il testing.
 * Successivamente si può eseguire il testing con il database temporaneo appena creato.
 * Essendo di tipo in memory quando l'ultima connessione viene chiusa il backend.database viene "pulito"
 * ATTENZIONE: se cambia il file DefaultSetEntry.sql deve cambiare anche nEntry                     <----------------------------------------------------------------------------------
 * Nota: il test del metodo  multipleIncorrectQueriesTransaction ci mette tanto perchè viene chiamato Thread.sleep
 */
public class DaoTest{

    private boolean PRINT_LIST=true;
    private IDaoFacade dao;
    private DatabaseConnectionHandler database;
    private Integer[] nEntry={14,4,4,4,5,5};//numero di inserimetni del file DefaultSetEntry.sql per le tabelle(in ordine):personale,cialde,visitatore,rifornimento,MovimentoDEbito,MovimentoVendita

    private String DATABASECONFIGFILE="/test/databaseConfig.sql";
    private String DEFAULTDATABASEENTRYSETFILE="/test/DafualtEntrySet.sql";
    private String CURRENTWORKINGDIRECTORY;
    /**
     * Inizializza il logger per il backend.database.
     * Tutti i log si trovano nel file ./LaTazza.log
     */
    @BeforeAll
    static void setUpLogger(){
        LaTazzaLogger.initLogger(false);
    }

    /**
     * Carica il backend.database con le tabelle necessarie.
     * Le def. di quest tab sono nello script LaTazza/guiIS/src/backend/database/config
     */
    @BeforeEach
    void setUp() {
        database=new DatabaseConnectionHandler("databaseTest","jdbc:h2:mem:","");//backend.database per il testing :mem (in memory)
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
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,MovimentoDebito.class,MovimentoVendita.class, MagazzinoEntry.class,Cassa.class})
    void getAllTest(Class<? extends AbstractEntryDB> cls) {
        try {
            dao=new DaoInvoker(database.getConnection(), InitBackEndCommand.daoCollection);
            List list=dao.getAll(cls);
            checkNEntry(Object.class,0,list.size());
            printList(list);
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }
    }

    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,MovimentoDebito.class,MovimentoVendita.class,MovimentoVendita.class})
    void saveTest(Class<? extends AbstractEntryDB>  cls)  {
        try {
            dao=new DaoInvoker(database.getConnection(),InitBackEndCommand.daoCollection);
            assertTrue(dao.save(createInstance(cls,true)));
            assertTrue(dao.save(createInstance(cls,true)));
            List list=dao.getAll(cls);
            checkNEntry(Object.class,2,list.size());
            printList(list);
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }

    }


    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,MovimentoDebito.class,MovimentoVendita.class})
    void updateTest(Class< AbstractEntryDB>  cls) {
        try {
            dao=new DaoInvoker(database.getConnection(),InitBackEndCommand.daoCollection);
            List<AbstractEntryDB> list=dao.getAll( cls);
            AbstractEntryDB entryDB=list.get(0);
            System.out.print("MODIFICO:" + entryDB.toString());
            modifyInstance(entryDB);
            System.out.println(" \nCON " + entryDB.toString());
            assertTrue(dao.update(entryDB));

            printList(list);
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }
    }

    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,MovimentoDebito.class,MovimentoVendita.class,MovimentoVendita.class})
    void deleteTest(Class<? extends AbstractEntryDB>cls){


        try {
            dao=new DaoInvoker(database.getConnection(),InitBackEndCommand.daoCollection);
            assertTrue(dao.delete(createInstance(cls,false)));
            List list=dao.getAll(cls);
            checkNEntry(Object.class,-1,list.size());
            printList(list);
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }


    }


    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,MovimentoDebito.class,MovimentoVendita.class,MovimentoVendita.class})
    void multipleCorrectQueriesTransaction(Class<? extends AbstractEntryDB>cls){
        try {
            List list = null;
            AbstractEntryDB entryDB;
            dao=new DaoInvoker(database.getConnection(),InitBackEndCommand.daoCollection);
            for(int i=1;i<=5;i++) {
                dao.startTransaction();
                assertTrue(dao.save(createInstance(cls,true)));
                assertTrue(dao.save(entryDB=createInstance(cls,true)));
                assertTrue(dao.delete(entryDB));
                list=dao.getAll(cls);
                dao.endTransaction();
                checkNEntry(Object.class,i,list.size());
            }
            printList(list);
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }
    }



    @ParameterizedTest
    @ValueSource(classes={Personale.class,CialdeEntry.class,Visitatore.class,RifornimentoEntry.class,MovimentoDebito.class,MovimentoVendita.class,MovimentoVendita.class})
    void multipleIncorrectQueriesTransaction(Class<? extends AbstractEntryDB>cls){

        try {
            System.out.flush();
            System.out.println("LE SEGUENTI 5 ECCEZIONI SONO PREVISTE(potrebbero essere oltre i separtori perchè stdout e sterr non sono sincronizzati)---------------------------------");

            List list ;
            AbstractEntryDB entryDB;
            dao=new DaoInvoker(database.getConnection(),InitBackEndCommand.daoCollection);
            for(int i=1;i<=5;i++) {
                dao.startTransaction();
                assertTrue(dao.save(entryDB=createInstance(cls,true)));
                assertFalse(dao.save(entryDB));
                dao.endTransaction();
                assertFalse(dao.getTransactionStatus());
                list=dao.getAll(cls);
                checkNEntry(Object.class,0,list.size());//transazione fallita nessuna modifica
            }
            System.err.flush();
            Thread.sleep(500);
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        }catch(Exception exc){
            exc.printStackTrace();
            fail("Impossibile trovare costruttore");
        }


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
        if(cls==MovimentoDebito.class){
            assertEquals(nEntry[4]+expectedSizeOffset,actualSize);
        }
        if(cls==MovimentoVendita.class){
            assertEquals(nEntry[5]+expectedSizeOffset,actualSize);
        }

    }



    private int j=0;//serve per createinstance : (j=pari) crea movimentovendita con un visitatore; j=dispari crea movimentovendita con un personale; Serve perche nei test parametrici viene passato MovimetnoVednita 2 volte--> quindi una volta uso visitatore la seconda Personale

    private AbstractEntryDB createInstance(Class<?> cls, boolean random) {
        try{
        if (random) {
            Random r = new Random();
            String randomchars="";
            char c;
            for(int contatore=0;contatore<10;contatore++){
                c = (char)(r.nextInt(26) + 'a');
                randomchars=randomchars.concat(String.valueOf(c));
            }
            if (cls == Personale.class) {
                return new Personale("zio", "pino" + randomchars, true);//get time serve per "randomizzare"
            }
            if (cls == CialdeEntry.class) {
                return new CialdeEntry("caffè" + randomchars, new Euro(0, 5));
            }
            if (cls == Visitatore.class) {
                return new Visitatore("ciccio", "pasticcio" + randomchars);
            }
            if (cls == RifornimentoEntry.class) {
                return new RifornimentoEntry(getRandomTimeStamp(), 100, "caffè");
            }
            if (cls == MovimentoDebito.class) {
                return new MovimentoDebito(getRandomTimeStamp(),new Personale("andrea","straforini"), new Euro(3, 5));

            }
            if (cls == MovimentoVendita.class) {
                if((j++&1)==0){//j pari
                    return new MovimentoVendita(getRandomTimeStamp(), new Visitatore("fabri","fibra"), 100,new CialdeEntry("thè"),true);
                }else{//j dispari
                    return new MovimentoVendita(getRandomTimeStamp(), new Personale("andrea","straforini"), 6,new CialdeEntry("cioccolata"),false);
                }
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
            }//yyyy-mm-dd hh:mm:ss[.fffffffff]
            if (cls == RifornimentoEntry.class) {
                return new RifornimentoEntry(Timestamp.valueOf("2000-12-31 00:00:00"), 2, "caffè");
            }
            if (cls == MovimentoDebito.class) {
                return new MovimentoDebito(Timestamp.valueOf("2019-01-01 00:00:00"),new Personale("andrea","straforini"), new Euro(3, 5));

            }
            if (cls == MovimentoVendita.class) {

                if((j++&1)==0){//j pari
                    return new MovimentoVendita(Timestamp.valueOf("2019-01-01 00:00:00"), new Visitatore("salmo","lebon"), 6,new CialdeEntry("cioccolata"),true);
                }else{//j dispari
                    return new MovimentoVendita(Timestamp.valueOf("2019-01-01 00:00:00"), new Personale("andrea","straforini"), 10,new CialdeEntry("caffè"),true);
                }

            }

            fail("istanza non riconosciuta");

        }
    }catch(Exception e){
        fail(e.toString());
    }
        return null;
    }

    private void modifyInstance(Object cls) {//modifica l'istanza passsata
        try{
                if (cls instanceof Personale) {
                    ((Personale)cls).setNome("CICCIO");
                    ((Personale)cls).setCognome("PASTICCIO");
                    ((Personale)cls).setAttivo(false);
                    return;
                }
                if (cls instanceof CialdeEntry) {
                    ((CialdeEntry)cls).setPrezzo(new Euro(1000,10));
                    ((CialdeEntry)cls).setTipo("Gusto cipolla");
                    return;
                }
                if (cls instanceof Visitatore) {
                    ((Visitatore)cls).setNome("PIPPO");
                    ((Visitatore)cls).setCognome("BAUDO");
                    return;
                }
                if (cls instanceof RifornimentoEntry) {
                    ((RifornimentoEntry)cls).setData(getRandomTimeStamp());
                    ((RifornimentoEntry)cls).setQta(1000);
                    ((RifornimentoEntry)cls).setTipoCialda("caffè");
                    return;
                }
                if (cls instanceof MovimentoDebito) {
                    ((MovimentoDebito)cls).setImporto(new Euro(1000,1000));
                    ((MovimentoDebito)cls).setCliente(new Personale("andrea","straforini"));
                    ((MovimentoDebito)cls).setData(getRandomTimeStamp());
                    return;

                }
                if (cls instanceof MovimentoVendita) {
                    ((MovimentoVendita)cls).setContanti(false);
                    ((MovimentoVendita)cls).setQuantita(100000);
                    ((MovimentoVendita)cls).setTipo(new CialdeEntry("caffè"));
                    ((MovimentoVendita)cls).setData(getRandomTimeStamp());
                    ((MovimentoVendita)cls).setCliente(new Visitatore("salmo","lebon"));
                    return;
                }
                fail("istanza non riconosciuta");
        }catch(Exception e){
            fail(e.toString());
        }
    }

    private Timestamp getRandomTimeStamp(){
        long offset = Timestamp.valueOf("1990-01-01 00:00:00").getTime();
        //long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long end=System.currentTimeMillis();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));

    }

    private void printList(List l) {
        System.out.println("LISTA-------------------------------------------------");
        if (PRINT_LIST) {
            for (Object i : l) {
                System.out.println(i.toString());
            }
        }
        System.out.println("------------------------------------------------------\n\n\n");

    }



}



