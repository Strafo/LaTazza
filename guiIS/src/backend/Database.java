package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:file:/home/strafo/Scrivania/Unige/terzo_anno/ing_software/LaTazza/guiIS/src/database/persistence;DB_CLOSE_DELAY=-1;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";



    public Connection createDatabaseConnection(){
        Connection con;
        try {
            Class.forName(DB_DRIVER);
        }catch (ClassNotFoundException e) {
            throw new DataBaseCreationException("Errore durante la creazione del database.(Data base driver non trovato.");
        }
        try{
            con=DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
            return  con;
        } catch (SQLException e) {
            throw new DataBaseCreationException("Errore durante la creazione del database.(Utente o password o Path non validi.");
        }
    }




    public class DataBaseCreationException extends RuntimeException{
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
