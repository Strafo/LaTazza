package presentationLayer.guiLogicPkg;

import backend.businessLogicLayer.*;
import backend.dataAccessLayer.gatewaysPkg.receiverPkg.*;
import backend.dataAccessLayer.gatewaysPkg.IDaoFacade;
import backend.dataAccessLayer.gatewaysPkg.DaoInvoker;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.MagazzinoEntry;
import backend.dataAccessLayer.rowdatapkg.RifornimentoEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoVendita;
import backend.database.DatabaseConnectionHandler;
import javafx.util.Pair;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public  class LaTazzaApplication implements Runnable {

	private LaTazzaFrame laTazzaFrame;//Finestra dell'applicazione
    public static DatabaseConnectionHandler databaseConnectionHandler;
    public static IDaoFacade dao;
    public static ControllerContabilita controllerContabilita;
    public static ControllerPersonale controllerPersonale;

    /**
     * Mappa dove viene creata l'associazione tra le classi della businessLogic
     * e le classi *DaoReceiver  per il database (contengono il codice per l'interazione con le tabelle del db)
     * La mappa viene usata dalla class SimpleDaoReceiverFactory
     */
    public static Collection<Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>>> daoCollection
            =new LinkedList<Pair<Class<? extends AbstractEntryDB>, Class<? extends AbstractDaoReceiver>>>(){{
        add(new Pair<>(RifornimentoEntry.class,RifornimentoDaoReceiver.class));
        add(new Pair<>(CialdeEntry.class,CialdeDaoReceiver.class));
        add(new Pair<>(MovimentoDebito.class,MovimentoDebitoDaoReceiver.class));
        add(new Pair<>(MovimentoVendita.class,MovimentoVenditaDaoReceiver.class));
        add(new Pair<>(Personale.class,PersonaleDaoReceiver.class));
        add(new Pair<>(Visitatore.class,VisitatoreDaoReceiver.class));
        add(new Pair<>(Cassa.class,CassaDaoReceiver.class));
        add(new Pair<>(MagazzinoEntry.class,MagazzinoDaoReceiver.class));

    }};

    public static void main(String[] args)  {
        java.awt.EventQueue.invokeLater(new LaTazzaApplication());

    }

    public void run(){
        databaseConnectionHandler =new DatabaseConnectionHandler();//inizializzo databaseConnectionHandler
        try {
            databaseConnectionHandler.initDataBase();
            dao=new DaoInvoker(databaseConnectionHandler.getConnection(),daoCollection);
        } catch ( SQLException| ClassNotFoundException e) {
            e.printStackTrace();//todo fare una migliore gestione degli errori
            System.exit(1);
        }
        controllerPersonale=new ControllerPersonale();
        controllerContabilita=new ControllerContabilita();
        this.initFrame();

    }

	private void initFrame(){
        laTazzaFrame=new LaTazzaFrame();
        laTazzaFrame.setVisible(true);
        laTazzaFrame.setLocationCenter();

    }




}
