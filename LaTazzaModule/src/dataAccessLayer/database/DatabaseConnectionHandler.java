package dataAccessLayer.database;
import utils.PathHandler;
import java.sql.*;

public class DatabaseConnectionHandler {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DEFAULT_OPT="DB_CLOSE_DELAY=-1;";
    private static final String DB_CONNECTION_DEFAULT = "jdbc:h2:file:";
    private static final String DB_PATH="./databaseLaTazza";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";


    private Connection connection;
    private String path;

    public DatabaseConnectionHandler(){
        this(DB_PATH);
    }

    public DatabaseConnectionHandler(String databasePath){
        PathHandler.modifyPath(databasePath);
        this.path=DB_CONNECTION_DEFAULT+databasePath;

    }

    public void initDataBase() throws SQLException, ClassNotFoundException {

        if(connection==null||connection.isClosed()){
            Class.forName(DB_DRIVER);
            System.out.println("Apro connessione con:"+path+";"+DEFAULT_OPT);
            connection = DriverManager.getConnection(path+";"+DEFAULT_OPT, DB_USER, DB_PASSWORD);
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


    //forza chiusura connessione db.
    protected void finalize(){
        try {
            connection.close();
        } catch (SQLException ignored) { }
    }

}
