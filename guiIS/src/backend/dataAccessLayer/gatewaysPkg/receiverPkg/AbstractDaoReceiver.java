package backend.dataAccessLayer.gatewaysPkg.receiverPkg;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.util.List;

public abstract class AbstractDaoReceiver<T extends AbstractEntryDB> {

    private Connection dataBaseConnection;

    protected ThrowingFunction<Connection,List<T>> getAllLambda;
    protected ThrowingBiPredicate<Connection,T> updateLambda;
    protected ThrowingBiPredicate<Connection,T>saveLambda;
    protected ThrowingBiPredicate<Connection,T>  deleteLambda;

    protected AbstractDaoReceiver(Connection dataBaseConnection) {
        this.dataBaseConnection=dataBaseConnection;
    }

    public  List<T> getAll(){ return getAllLambda.apply(dataBaseConnection); }

    public  boolean save(T t ){
        return saveLambda.test(dataBaseConnection,t);
    }

    public  boolean update(T t){ return updateLambda.test(dataBaseConnection,t); }

    public  boolean delete(T t){ return deleteLambda.test(dataBaseConnection,t); }



}


