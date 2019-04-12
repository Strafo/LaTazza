package backend.database;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import backend.database.DatabaseConnectionHandler;
import backend.database.config.TriggerCheckNumCialde;
import backend.database.config.ViewCassa;
import backend.database.config.ViewDebito;
import backend.database.config.ViewMagazzino;
import utils.PathHandler;


public class ConfigurationDataBase {

    public static DatabaseConnectionHandler databaseConnectionHandler;

    private static final String PATHConfig="\\guiIS\\src\\backend\\database\\config\\";
    private static final String userDir=System.getProperty("user.dir");
    private static final String createSchemaFile="databaseConfig.sql";
    private static final String insertFile="insertCongif.sql";

    public ConfigurationDataBase(DatabaseConnectionHandler database){
        databaseConnectionHandler=database;
    }

    public boolean existsSchema()  {
        PreparedStatement stat;
        ResultSet rs;
        Connection connection=databaseConnectionHandler.getConnection();
        try {
            //faccio una select su una delle tabelle dello schema, se non c'è vuol dire che non è stato creato lo schema
            stat=connection.prepareStatement("SELECT * from LATAZZASCHEMA.CIALDE ");
            rs=stat.executeQuery();

            rs.next();
        }catch (SQLException e){ return false;}

        return true;
    }

    private void updateTable(String sqlFileName) throws SQLException {
        Scanner inFile=null;
        Connection connection=databaseConnectionHandler.getConnection();
        try {

            StringBuilder file= new StringBuilder();
            inFile= new Scanner(new FileReader(PathHandler.modifyPath(userDir+PATHConfig+sqlFileName)));

            while(inFile.hasNext()) file.append(inFile.nextLine()).append("\n");

            PreparedStatement stmt=connection.prepareStatement(file.toString());
            stmt.execute();
            stmt.close();
        } catch ( Exception e ) {
            inFile.close();
            System.exit(0);


        }

    }

    public void createSchema() throws SQLException {
        updateTable(createSchemaFile);
    }

    public  void inserimentiIniziali() throws SQLException{
        updateTable(insertFile);
    }


    public void initTriggers() throws SQLException {
        ViewCassa.initView(databaseConnectionHandler.getConnection());
        ViewDebito.initView(databaseConnectionHandler.getConnection());
        ViewMagazzino.initView(databaseConnectionHandler.getConnection());
        TriggerCheckNumCialde.initTrigger(databaseConnectionHandler.getConnection());
    }
}
