package backend.daopkg.gateways;
import utils.LaTazzaLogger;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;

public abstract class AbstractDao<T> implements Dao<T> {

    private final String table_name;
    private Connection dataBaseConnection;


    protected AbstractDao(String table_name) {
        this.table_name=table_name;
    }


    public String getTableName() {
        return table_name;
    }

    public Connection getDataBaseConnection() {
        return dataBaseConnection;
    }


    public void setDataBaseConnection(Connection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }



    public  List<T> getAll(){
        try{
            return getLambdaGetAll().apply(dataBaseConnection);
        }catch(Exception exc){
            LaTazzaLogger.getLOGGER().log(Level.SEVERE, "Errore esecuzione getAll query.", exc);
            return null;
        }
    }

    public  boolean save(T t ){
        try{
            return getLambdaSave().test(dataBaseConnection,t);
        }catch(Exception exc){
            LaTazzaLogger.getLOGGER().log(Level.SEVERE, "Errore esecuzione save query.", exc);
            return false;
        }

    }

    public  boolean update(T t){
        try{
            return getLambdaUpdate().test(dataBaseConnection,t);
        }catch(Exception exc){
            LaTazzaLogger.getLOGGER().log(Level.SEVERE, "Errore esecuzione update query.", exc);
            return false;
        }
    }

    public  boolean delete(T t){
        try{
            return getLambdaDelete().test(dataBaseConnection,t);
        }catch(Exception exc){
            LaTazzaLogger.getLOGGER().log(Level.SEVERE, "Errore esecuzione delete query.", exc);
            return false;
        }
    }


    public abstract ThrowingFunction<Connection,List<T>> getLambdaGetAll();

    public abstract ThrowingBiPredicate<Connection,T> getLambdaUpdate();

    public abstract ThrowingBiPredicate<Connection,T> getLambdaSave();

    public abstract ThrowingBiPredicate<Connection,T> getLambdaDelete();
}


