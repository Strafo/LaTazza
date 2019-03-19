package backend.daopkg.gateways;
import java.util.List;

public interface Dao<T> {

    //Optional<T> get(Map keys); TODO TOBEIMPLEMENTED

    List<T> getAll();

    boolean save(T t);

    boolean update(T t);//todo

    boolean delete(T t);
}