package backend.dataAccessLayer.gatewaysPkg.receiverPkg;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import java.sql.Connection;
import java.util.List;

public abstract class AbstractDaoReceiver<T extends AbstractEntryDB> {

    protected Connection dataBaseConnection;

    protected AbstractDaoReceiver(Connection dataBaseConnection) {
        this.dataBaseConnection=dataBaseConnection;
    }

    public abstract List<T> getAll()throws Exception;

    public abstract boolean save(T t )throws Exception;

    public abstract boolean update(T t)throws Exception;

    public abstract boolean delete(T t)throws Exception;

}


