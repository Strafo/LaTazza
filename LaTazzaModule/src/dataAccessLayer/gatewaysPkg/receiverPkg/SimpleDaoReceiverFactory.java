package dataAccessLayer.gatewaysPkg.receiverPkg;
import dataAccessLayer.rowdatapkg.AbstractEntryDB;
import javafx.util.Pair;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Collection;

/**
 * Questa classe mantiene la collezione di associazioni tra:
 *  rowdataclass -> daoreceiverclass corrispondente.
 *
 *  Esempio:
 *  Personale ->PersonaleDaoReceiver
 *  Magazzino ->MagazzinoDaoReceiver
 *
 *  le classi "rowdata"  si trovano nel pkg omonimo mentre
 *  le classi "daoreceiver" nel pkg gatewaysPkg.
 *
 */
public class SimpleDaoReceiverFactory {

    private Connection connection;
    private Collection<Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>>> daoCollection;//non si può usare Map perchè Class non implementa Comparable

    public SimpleDaoReceiverFactory(Connection connection, Collection<Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>>> daoCollection){
        this.connection=connection;
        this.daoCollection=daoCollection;
    }

    /**
     * @brief
     * Questo metodo itera sulla collezione di associazioni finchè non trova
     * quella giusta.
     * Se non viene trovata lancia runtimeexc
     */
    public <T extends AbstractEntryDB> AbstractDaoReceiver createDao(Class<T> t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>> i:daoCollection) {
            if(i.getKey()==t){
                return i.getValue().getConstructor(Connection.class).newInstance(connection);
            }
        }
        if(t==null)
            throw new RuntimeException("Impossibile creare Dao per t (nullref).");
        else
            throw new RuntimeException("Impossibile creare Dao per la classe t:"+t.getSimpleName());
    }
}
