package backend.daopkg.gateways;
import java.util.List;

public interface Dao<T> {

    //Optional<T> get(Map keys); TOBEIMPLEMENTED

    List<T> getAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}