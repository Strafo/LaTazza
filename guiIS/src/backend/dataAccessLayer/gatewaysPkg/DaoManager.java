package backend.dataAccessLayer.gatewaysPkg;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import utils.LaTazzaLogger;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class DaoManager implements DaoInterface {

    private Connection dataBaseConnection;
    private AbstractDao subdao;//il dao corrente (vengono switchati i tipi di dao a run time)
    private boolean transactionStatus=true;//stato di errore della transazione--> false da fare rollback ; true da committare

    public DaoManager(Connection dataBaseConnection){
        this.dataBaseConnection=dataBaseConnection;
    }

    public Connection getDataBaseConnection() {
        return dataBaseConnection;
    }

    @Override
    public <T extends AbstractEntryDB> List<T> getAll(Class<T> t) {
        List<T> result;
        try{
            subdao=instantiateSpecificDao(t);
            result=subdao.getAll();
            handleTransactionStatus(result);
            return result;
        }catch(Exception exc){
            handleException("GETALL",exc);
            handleTransactionStatus(null);
            return null;
        }
    }

    @Override
    public <T extends AbstractEntryDB> boolean save(T t) {
        boolean exitStatus;
        try{
            subdao=instantiateSpecificDao(t);
            exitStatus=subdao.save(t);
            handleTransactionStatus(exitStatus);
            return exitStatus;
        }catch(Exception exc){
            handleException("SAVE",exc);
            handleTransactionStatus(false);
            return false;
        }
    }

    @Override
    public <T extends AbstractEntryDB> boolean update(T t) {
        boolean exitStatus;
        try{
             subdao=instantiateSpecificDao(t);
             if(exitStatus=subdao.update(t)){
                t.removeMemento();//todo il caretaker per mento è separato in due classi...(questa e la chiamante) si riesce a mettere tutto in posto (ad esempio risucendo a fare un passaggio per riferimento a update e chiamando t.undochage()
             }
             handleTransactionStatus(exitStatus);
             return exitStatus;
        }catch(Exception exc){
            handleException("UPDATE",exc);
            return false;
        }
    }

    @Override
    public <T extends AbstractEntryDB> boolean delete(T t) {
        boolean exitStatus;
        try{
            subdao=instantiateSpecificDao(t);
            exitStatus=subdao.delete(t);
            handleTransactionStatus(exitStatus);
            return exitStatus;
        }catch(Exception exc){
            handleException("DELETE",exc);
            return false;
        }
    }

    @Override
    public void startTransaction() {
        try {
            dataBaseConnection.setAutoCommit(false);
            transactionStatus=true;
            return;
        } catch (Exception e) {
            transactionStatus=false;
            handleException("START TRANSACTION",e);
        }
    }

    @Override
    public void endTransaction() {
        try {
            if (transactionStatus) {
                dataBaseConnection.commit();
            } else {
                dataBaseConnection.rollback();
            }
            dataBaseConnection.setAutoCommit(true);
        }catch (Exception e){
            handleException("END TRANSACTION",e);
        }
    }

    public boolean executeStatement(String sqlStatement){
        try{
            Statement pst;
            ResultSet rst;
            ResultSetMetaData rsmd ;
            int columnsNumber;
            pst=dataBaseConnection.createStatement();
            if(pst.execute(sqlStatement)) {//true->multiple res
                rst = pst.getResultSet();
                rsmd = rst.getMetaData();
                columnsNumber = rsmd.getColumnCount();
                while (rst.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rst.getString(i);
                        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                    System.out.println();
                }
            }
            return true;
        }catch(Exception e){
            handleException("EXTERN SQLSTATEMENT",e);
        }
        return false;
    }



    public boolean getTransactionStatus() {
        return transactionStatus;
    }

    private void handleTransactionStatus(boolean newState){
        try {
            if(!dataBaseConnection.getAutoCommit()) {//se transazione con multiple query
                transactionStatus&=newState;
            }
        } catch (Exception e) {
            handleException("HANDLE TRANSACTION STATUS",e);
        }
    }

    private void handleTransactionStatus(List newState){
        try {
            if(!dataBaseConnection.getAutoCommit()) {//se transazione con multiple query
                if(newState==null){
                    transactionStatus=false;
                }
            }
        } catch (Exception e) {
            handleException("HANDLE TRANSACTION STATUS",e);
        }
    }


    private <T extends AbstractEntryDB> AbstractDao instantiateSpecificDao(T t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return t.getCorrespondigDaoClass().getConstructor(Connection.class).newInstance(dataBaseConnection);
    }

    private <T extends AbstractEntryDB> AbstractDao instantiateSpecificDao(Class<T> t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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
