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
    private String options;

    public DatabaseConnectionHandler(){
        this(DB_PATH);
    }

    public DatabaseConnectionHandler(String databasePath){
        PathHandler.modifyPath(databasePath);
        this.path=DB_CONNECTION_DEFAULT+databasePath;
        options=DEFAULT_OPT;

    }

    public DatabaseConnectionHandler(String databasePath,String jdbcH2Mode,String options){
        PathHandler.modifyPath(databasePath);
        this.path=jdbcH2Mode+databasePath;
        this.options=options;
    }

    public void initDataBase() throws SQLException, ClassNotFoundException {

        if(connection==null||connection.isClosed()){
            Class.forName(DB_DRIVER);
            System.out.println("Apro connessione con:"+path+";"+options);
            connection = DriverManager.getConnection(path+";"+options, DB_USER, DB_PASSWORD);
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
