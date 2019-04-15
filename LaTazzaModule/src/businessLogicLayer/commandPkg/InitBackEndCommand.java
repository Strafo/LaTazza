package businessLogicLayer.commandPkg;

import businessLogicLayer.*;
import dataAccessLayer.gatewaysPkg.DaoInvoker;
import dataAccessLayer.gatewaysPkg.receiverPkg.*;
import dataAccessLayer.rowdatapkg.*;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;
import dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoVendita;
import dataAccessLayer.database.ConfigurationDataBase;
import dataAccessLayer.database.DatabaseConnectionHandler;
import javafx.util.Pair;
import businessLogicLayer.BackEndInvoker;
import businessLogicLayer.ObserverSubscriptionType;
import java.sql.SQLException;
import java.util.*;
import static businessLogicLayer.ObserverSubscriptionType.*;


public class InitBackEndCommand implements  Command{
    private BackEndInvoker backEndInvoker;

    public InitBackEndCommand(BackEndInvoker backEndInvoker){
        this.backEndInvoker = backEndInvoker;
    }

    @Override
    public boolean execute() throws SQLException, ClassNotFoundException {

        backEndInvoker.setDatabaseConnectionHandler(new DatabaseConnectionHandler());
        backEndInvoker.getDatabaseConnectionHandler().initDataBase();
        backEndInvoker.setDao(new DaoInvoker(backEndInvoker.getDatabaseConnectionHandler().getConnection(),daoCollection));
        ConfigurationDataBase db= new ConfigurationDataBase(backEndInvoker.getDatabaseConnectionHandler());

        if(!db.existsSchema()) {
            db.createSchema();
            db.initTriggers();
            db.inserimentiIniziali();
        }
        backEndInvoker.setControllerCialde(new ControllerCialde());
        backEndInvoker.setControllerContabilita(new ControllerContabilita());
        backEndInvoker.setControllerPersonale(new ControllerPersonale());
        backEndInvoker.setControllerDebito(new ControllerDebito());
        backEndInvoker.setSubscriptions(new EnumMap<>(ObserverSubscriptionType.class));

        //init subscriptions


        for (ObserverSubscriptionType i :ObserverSubscriptionType.values()) {
            switch(i){

                case CIALDELIST:
                    backEndInvoker.getSubscriptions().put(CIALDELIST,backEndInvoker.getControllerCialde());
                    break;
                case PERSONALELIST:
                    backEndInvoker.getSubscriptions().put(PERSONALELIST,backEndInvoker.getControllerPersonale());
                    break;
                case CONTABILITALIST:
                    backEndInvoker.getSubscriptions().put(CONTABILITALIST,backEndInvoker.getControllerContabilita());
                    break;
                case DEBITOLIST:
                    backEndInvoker.getSubscriptions().put(DEBITOLIST,backEndInvoker.getControllerDebito());
                    break;
            }
        }

        return true;
    }


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
}
