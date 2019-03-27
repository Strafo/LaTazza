package backend.dataAccessLayer.gatewaysPkg;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao<T extends AbstractEntryDB> {

    private Connection dataBaseConnection;

    protected AbstractDao(Connection dataBaseConnection) {
        this.dataBaseConnection=dataBaseConnection;
    }

    public  List<T> getAll(){
        return getLambdaGetAll().apply(dataBaseConnection);
    }

    public  boolean save(T t ){
        return getLambdaSave().test(dataBaseConnection,t);
    }

    public  boolean update(T t){
        return getLambdaUpdate().test(dataBaseConnection,t);
    }

    public  boolean delete(T t){
        return getLambdaDelete().test(dataBaseConnection,t);
    }

    protected abstract ThrowingFunction<Connection,List<T>> getLambdaGetAll();

    protected abstract ThrowingBiPredicate<Connection,T> getLambdaUpdate();

    protected abstract ThrowingBiPredicate<Connection,T> getLambdaSave();

    protected abstract ThrowingBiPredicate<Connection,T> getLambdaDelete();

}


