package presentationLayer.guiLogicPkg;

import backend.businessLogicLayer.ControllerCialde;
import backend.businessLogicLayer.ControllerContabilita;
import backend.businessLogicLayer.ControllerPersonale;
import backend.dataAccessLayer.gatewaysPkg.IDaoFacade;
import backend.database.DatabaseConnectionHandler;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import presentationLayer.guiLogicPkg.commandPkg.Command;

public class BackEndInvoker implements Observable {

    private ControllerContabilita controllerContabilita;
    private ControllerPersonale controllerPersonale;
    private ControllerCialde controllerCialde;
    private DatabaseConnectionHandler databaseConnectionHandler;
    private IDaoFacade dao;

    public enum ObserverSubscription{
        CIALDELIST,
        PERSONALELIST,
        RIFORNIMENTOLIST,

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

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

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
}
