package backend.daopkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class Database {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:file:/home/strafo/Scrivania/Unige/terzo_anno/ing_software/LaTazza/guiIS/src/database/persistence;DB_CLOSE_DELAY=-1;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";


    private Connection connection;




    public void createDatabaseConnection() throws DataBaseCreationException {

        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName(DB_DRIVER);
                } catch (ClassNotFoundException e) {
                    throw new DataBaseCreationException("Errore durante la creazione del database.(Data base driver non trovato.");
                }
                try {
                    connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                } catch (SQLException e) {
                    throw new DataBaseCreationException("Errore durante la creazione del database.(Utente o password o Path non validi.");
                }
            }
        }catch (SQLException e){
            throw new DataBaseCreationException("Errore durante la creazione del database.Impossibile stabilire connessione");
        }
    }

    public Optional<Connection> getConnection(){
        return Optional.ofNullable(connection);
    }

    public Connection createAndGetConnection() throws DataBaseCreationException {
        createDatabaseConnection();
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }


    /******************************************
     * CUSTOM EXCEPTIONS
     *****************************************/

    public class DataBaseCreationException extends Exception{
        public DataBaseCreationException() {
            super();
        }

        public DataBaseCreationException(String message) {
            super(message);
        }

        public DataBaseCreationException(String message, Throwable cause) {
            super(message, cause);
        }

        public DataBaseCreationException(Throwable cause) {
            super(cause);
        }

        protected DataBaseCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }


}
