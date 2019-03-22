package backend.daopkg.gateways;
import backend.daopkg.rowdatapkg.AbstractEntryDB;
import java.util.List;

public interface DaoInterface<T extends AbstractEntryDB> {

    //Optional<T> get(Map keys); TODO TOBEIMPLEMENTED

    List<T> getAll(Class<T> t);

    boolean save(T t);

    boolean update(T t);//todo

    boolean delete(T t);

}
