package dataAccessLayer.gatewaysPkg;
import dataAccessLayer.gatewaysPkg.receiverPkg.AbstractDaoReceiver;
import dataAccessLayer.gatewaysPkg.receiverPkg.SimpleDaoReceiverFactory;
import dataAccessLayer.rowdatapkg.AbstractEntryDB;
import javafx.util.Pair;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import utils.LaTazzaLogger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class DaoInvoker implements IDaoFacade {

    private SimpleDaoReceiverFactory factory;
    private Connection dataBaseConnection;
    private AbstractDaoReceiver concreteDaoReceiver;//il dao corrente (vengono switchati i tipi di dao a run time)
    private boolean transactionStatus=true;//stato di errore della transazione--> false da fare rollback ; true da committare

    public DaoInvoker(Connection dataBaseConnection, Collection<Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>>> daoCollection){
        this.dataBaseConnection=dataBaseConnection;
        this.factory=new SimpleDaoReceiverFactory(dataBaseConnection,daoCollection);
    }


    @Override
    public <T extends AbstractEntryDB> List<T> getAll(Class<T> t) {
        List<T> result;
        try{
            concreteDaoReceiver=factory.createDao(t);

            result=concreteDaoReceiver.getAll();
            handleTransactionStatus(result);
            return result;
        }catch(Exception exc){
            exc.printStackTrace();
            handleException("GETALL",exc);
            handleTransactionStatus(null);
            return null;
        }
    }

    @Override
    public <T extends AbstractEntryDB> boolean save(T t) {
        boolean exitStatus;
        try{
            concreteDaoReceiver=factory.createDao(t.getClass());
            exitStatus=concreteDaoReceiver.save(t);
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
             concreteDaoReceiver=factory.createDao(t.getClass());
             if(exitStatus=concreteDaoReceiver.update(t)){
                t.removeMemento();//se update andato a buon fine lo stato precedente della classe può essere eliminato(lo stato prec. è salvato grazie al pattern memento)
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
            concreteDaoReceiver=factory.createDao(t.getClass());
            exitStatus=concreteDaoReceiver.delete(t);
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

    public void setTransactionStatus(boolean newStat){
        transactionStatus=newStat;
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



    private void handleException(String queryType,Exception exc){
        if(exc.getCause() instanceof JdbcSQLIntegrityConstraintViolationException){
            LaTazzaLogger.log(
                    new LogRecord(
                            Level.INFO,
                            "ConstraintViolation esecuzione "+ queryType +" query.\n"+ exc.toString()+" "+((JdbcSQLIntegrityConstraintViolationException) exc.getCause()).getSQL()+"\n\n"));
        }else {
            LaTazzaLogger.log(Level.SEVERE, "Errore esecuzione " + queryType + " query.\n", exc);
        }
    }

}
