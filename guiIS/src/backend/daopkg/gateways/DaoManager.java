package backend.daopkg.gateways;
import backend.daopkg.rowdatapkg.AbstractEntryDB;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import utils.LaTazzaLogger;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class DaoManager<T extends AbstractEntryDB> implements DaoInterface<T> {

    private Connection dataBaseConnection;
    private AbstractDao<T> subdao;

    public DaoManager(Connection dataBaseConnection){
        this.dataBaseConnection=dataBaseConnection;
    }

    public Connection getDataBaseConnection() {
        return dataBaseConnection;
    }


    @Override
    public List<T> getAll(Class<T> t) {
        try{
            subdao=instantiateSpecificDao(t);
            return subdao.getAll();
        }catch(Exception exc){
            handleException("GETALL",exc);
            return null;
        }
    }

    @Override
    public boolean save(T t) {
        try{
            subdao=instantiateSpecificDao(t);
            return subdao.save(t);
        }catch(Exception exc){
            handleException("SAVE",exc);
            return false;
        }
    }

    @Override
    public boolean update(T t) {
        try{
            subdao=instantiateSpecificDao(t);
            return subdao.update(t);
        }catch(Exception exc){
            handleException("UPDATE",exc);
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        try{
            subdao=instantiateSpecificDao(t);
            return subdao.delete(t);
        }catch(Exception exc){
            handleException("DELETE",exc);
            return false;
        }
    }

    private AbstractDao<T> instantiateSpecificDao(T t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return t.getCorrespondigDaoClass().getConstructor(Connection.class).newInstance(dataBaseConnection);
    }

    private AbstractDao<T> instantiateSpecificDao(Class<T> t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T obj=t.newInstance();
        return obj.getCorrespondigDaoClass().getConstructor(Connection.class).newInstance(dataBaseConnection);
    }

    private void handleException(String queryType,Exception exc){
        if(exc.getCause() instanceof JdbcSQLIntegrityConstraintViolationException){
            LaTazzaLogger.getLOGGER().log(
                    new LogRecord(
                            Level.INFO,
                            "ConstraintViolation esecuzione "+ queryType +" query.\n"+ exc.toString()+" "+((JdbcSQLIntegrityConstraintViolationException) exc.getCause()).getSQL()+"\n\n"));
        }else {
            LaTazzaLogger.getLOGGER().log(Level.SEVERE, "Errore esecuzione " + queryType + " query.\n", exc);
        }
    }
}
