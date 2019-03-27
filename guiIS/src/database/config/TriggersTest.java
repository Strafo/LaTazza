package database.config;

import database.DataBase;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class TriggersTest {

    private Connection conn;
    private DataBase database;
    private static final String URL="jdbc:h2:mem:databaseTest";
    //private static final String URL="jdbc:h2:C:/Users/simoc/IdeaProjects/LaTazza/guiIS/src/database/config";
    private final String PATH="guiIS\\src\\database\\config\\";
    private Scanner inFile;
    private boolean schemaExists=false;




    public TriggersTest() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        database= new DataBase(URL);
        database.initDataBase();
        conn = database.getConnection();
    }


    public void updateTable(String sqlFileName) throws SQLException {

        try {
            StringBuilder file= new StringBuilder();
            inFile= new Scanner(new FileReader(PATH+sqlFileName));
            while(inFile.hasNext()) {
                file.append(inFile.nextLine()).append("\n");
            }
            //System.out.println("----------------------------------\n"+schema+"\n------------------------");
            Statement stmt=conn.createStatement();
            stmt.addBatch(file.toString());
            stmt.executeUpdate(file.toString());
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            schemaExists=false;
            System.exit(0);
            inFile.close();
            conn.close();
        }
        System.out.println("File red and updated");
        schemaExists=true;

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        TriggersTest T= new TriggersTest();
        Connection conn= T.getDatabase().getConnection();
        Statement stat= conn.createStatement();
        T.updateTable("databaseConfig.sql");
        T.updateTable("Insert.sql");


        /*
        stat.execute("CREATE TRIGGER check_num_Cialde " +
                "AFTER INSERT ON LATAZZASCHEMA.COMPRA_VISITATORE FOR EACH ROW " +
                "CALL \"TriggersTest$Trigger1\" ");
        */
        T.getDatabase().closeDataBase();
    }

    DataBase getDatabase(){return database;}
}