package dataAccessLayer.gatewaysPkg.receiverPkg;
import dataAccessLayer.rowdatapkg.AbstractEntryDB;
import javafx.util.Pair;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Collection;

public class SimpleDaoReceiverFactory {

    private Connection connection;
    private Collection<Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>>> daoCollection;//non si può usare Map perchè Class non implementa Comparable

    public SimpleDaoReceiverFactory(Connection connection, Collection<Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>>> daoCollection){
        this.connection=connection;
        this.daoCollection=daoCollection;
    }

    public <T extends AbstractEntryDB> AbstractDaoReceiver createDao(Class<T> t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>> i:daoCollection) {
            if(i.getKey()==t){
                return i.getValue().getConstructor(Connection.class).newInstance(connection);
            }
        }
        return null;//todo
    }
}
