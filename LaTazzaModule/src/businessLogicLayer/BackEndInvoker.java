package businessLogicLayer;

import dataAccessLayer.gatewaysPkg.IDaoFacade;
import dataAccessLayer.database.DatabaseConnectionHandler;
import businessLogicLayer.commandPkg.Command;
import utils.LaTazzaLogger;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class BackEndInvoker {

    private ControllerContabilita controllerContabilita;
    private ControllerPersonale controllerPersonale;
    private ControllerCialde controllerCialde;
    private ControllerDebito controllerDebito;
    private DatabaseConnectionHandler databaseConnectionHandler;
    private IDaoFacade dao;
    private Map<ObserverSubscriptionType, Observable> subscriptions;

    public BackEndInvoker(){ }

    public boolean executeCommand(Command command) {
        try{
            return command.execute();
        }catch (Exception e ){
            handleException(command,e);
            return false;
        }
    }

    public void addObserver(ObserverSubscriptionType subscriptionType, Observer observer){
        Observable ob;
        try {
            ob=subscriptions.get(subscriptionType);
            ob.addObserver(observer);
        }catch(Exception e){
            handleException(observer,subscriptionType,e);
            throw new Error("Inizializzazione falita");
        }
    }

    private void handleException(Command c,Exception e){
        if(c!=null) {
            LaTazzaLogger.log(
                    Level.WARNING, "Errore esecuzione " +c.getClass().getSimpleName()+"\n\n",e
            );
        }else{
            LaTazzaLogger.log(new LogRecord(Level.WARNING, "Errore esecuzione  comando==null\n\n"));
        }
    }

    private void handleException(Observer ob,ObserverSubscriptionType type,Exception e) {
        if(ob!=null&&type!=null) {
            LaTazzaLogger.log(
                    Level.SEVERE, "Errore addObserver:" + ob.getClass().getSimpleName()+" type:"+
                    type.name()+"\n\n",e);
        }else{
            LaTazzaLogger.log(
                    new LogRecord(Level.SEVERE, "Errore addObserver || type==(null)\n\n"));
        }
    }



    /**GETTER AND SETTER**/

    public  ControllerContabilita getControllerContabilita() {
        return controllerContabilita;
    }

    public void setControllerContabilita(ControllerContabilita controllerContabilita) {
        this.controllerContabilita = controllerContabilita;
    }

    public ControllerPersonale getControllerPersonale() {
        return controllerPersonale;
    }

    public void setControllerPersonale(ControllerPersonale controllerPersonale) {
        this.controllerPersonale = controllerPersonale;
    }

    public ControllerCialde getControllerCialde() {
        return controllerCialde;
    }

    public void setControllerCialde(ControllerCialde controllerCialde) {
        this.controllerCialde = controllerCialde;
    }

    public DatabaseConnectionHandler getDatabaseConnectionHandler() {
        return databaseConnectionHandler;
    }

    public void setDatabaseConnectionHandler(DatabaseConnectionHandler databaseConnectionHandler) {
        this.databaseConnectionHandler = databaseConnectionHandler;
    }

    public IDaoFacade getDao() {
        return dao;
    }

    public void setDao(IDaoFacade dao) {
        this.dao = dao;
    }

    public Map<ObserverSubscriptionType, Observable> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Map<ObserverSubscriptionType, Observable> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public ControllerDebito getControllerDebito() {
        return controllerDebito;
    }

    public void setControllerDebito(ControllerDebito controllerDebito) {
        this.controllerDebito = controllerDebito;
    }


}
