package backend.daopkg.gateways;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    private final String table_name;
    private Connection dataBaseConnection;


    protected AbstractDao(String table_name) {
        this.table_name=table_name;
    }


    public String getTableName() {
        return table_name;
    }

    public Connection getDataBaseConnection() {
        return dataBaseConnection;
    }


    public void setDataBaseConnection(Connection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }



    public abstract List<T> getAll();

    public abstract boolean save(T t );

    public abstract boolean update(T t);

    public abstract boolean delete(T t);


}
