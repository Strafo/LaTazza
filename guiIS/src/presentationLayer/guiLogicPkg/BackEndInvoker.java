package presentationLayer.guiLogicPkg;

import backend.businessLogicLayer.ControllerCialde;
import backend.businessLogicLayer.ControllerContabilita;
import backend.businessLogicLayer.ControllerDebito;
import backend.businessLogicLayer.ControllerPersonale;
import backend.dataAccessLayer.gatewaysPkg.IDaoFacade;
import backend.database.DatabaseConnectionHandler;
import presentationLayer.guiLogicPkg.commandPkg.Command;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class BackEndInvoker {

    private ControllerContabilita controllerContabilita;
    private ControllerPersonale controllerPersonale;
    private ControllerCialde controllerCialde;
    private ControllerDebito controllerDebito;
    private DatabaseConnectionHandler databaseConnectionHandler;
    private IDaoFacade dao;
    private Map<ObserverSubscriptionType, Observable> subscriptions;


    public enum ObserverSubscriptionType{
        CIALDELIST,
        PERSONALELIST,
        RIFORNIMENTOLIST,
        CONTABILITALIST,
        DEBITOLIST,

    }

    public BackEndInvoker(){

    }


    public boolean executeCommand(Command command) {
        try{
            return command.execute();
        }catch (Exception e ){
            //todo handle exception
            return false;
        }
    }

    public void addObserver(ObserverSubscriptionType subscriptionType, Observer observer){
        subscriptions.get(subscriptionType).addObserver(observer);
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
