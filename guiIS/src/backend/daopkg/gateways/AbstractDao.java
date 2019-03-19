package backend.daopkg.gateways;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import utils.LaTazzaLogger;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
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
            handleException("GETALL",exc);
            return null;
        }
    }

    public  boolean save(T t ){
        try{
            return getLambdaSave().test(dataBaseConnection,t);
        }catch(Exception exc){
            handleException("SAVE",exc);
            return false;
        }

    }

    public  boolean update(T t){
        try{
            return getLambdaUpdate().test(dataBaseConnection,t);
        }catch(Exception exc){
            handleException("UPDATE",exc);
            return false;
        }
    }

    public  boolean delete(T t){
        try{
            return getLambdaDelete().test(dataBaseConnection,t);
        }catch(Exception exc){
            handleException("DELETE",exc);
            return false;
        }
    }

    private void handleException(String queryType,Exception exc){
        if(exc.getCause() instanceof JdbcSQLIntegrityConstraintViolationException){
            LaTazzaLogger.getLOGGER().log(
                    new LogRecord(
                            Level.INFO,
                            "ConstraintViolation esecuzione "+ queryType +" query.\n"+ exc.toString()+" "+((JdbcSQLIntegrityConstraintViolationException) exc.getCause()).getSQL()+"\n\n"));
        }else{
            LaTazzaLogger.getLOGGER().log(Level.SEVERE,"Errore esecuzione "+queryType+" query.\n",exc);
        }

    }


    public abstract ThrowingFunction<Connection,List<T>> getLambdaGetAll();

    public abstract ThrowingBiPredicate<Connection,T> getLambdaUpdate();

    public abstract ThrowingBiPredicate<Connection,T> getLambdaSave();

    public abstract ThrowingBiPredicate<Connection,T> getLambdaDelete();
}


