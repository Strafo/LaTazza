package backend.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionHandler {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DEFAULT_OPT="DB_CLOSE_DELAY=-1;";
    private static final String DB_CONNECTION_DEFAULT = "jdbc:h2:file:~/databaseLaTazza;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private Connection connection;
    private String path;

    public DatabaseConnectionHandler(){
        this(DB_CONNECTION_DEFAULT);
    }

    public DatabaseConnectionHandler(String databasePath){
        this.path=databasePath;

    }

    public void initDataBase() throws SQLException, ClassNotFoundException {

        if(connection==null||connection.isClosed()){
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(path+DEFAULT_OPT, DB_USER, DB_PASSWORD);
        }

    }

    public void closeDataBase() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getPath() {
        return path;
    }


}
