package testBackend;


import backend.database.DatabaseConnectionHandler;
import backend.database.config.TriggerCheckNumCialde;
import backend.database.config.ViewCassa;
import backend.database.config.ViewDebito;
import backend.database.config.ViewMagazzino;

import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;


public class TriggersTest {

    private Connection conn;
    private DatabaseConnectionHandler database;
    private static final String URL="jdbc:h2:mem:databaseTest";
    private static final String userDir=System.getProperty("user.dir");
    private final String PATHConfig="\\src\\backend\\database\\config\\";
    private final String PATHInsert="\\test\\testBackend\\";
    private Scanner inFile;
    private static ResultSet rs;
    private static PreparedStatement stat;

//ciao socio

    public TriggersTest() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        database= new DatabaseConnectionHandler(URL);
        database.initDataBase();
        conn = database.getConnection();
    }


    private void updateTable(String path,String sqlFileName) throws SQLException {

        try {

            StringBuilder file= new StringBuilder();
            //System.out.println("Path: "+System.getProperty("user.dir")+sqlFileName);
            inFile= new Scanner(new FileReader(userDir+path+sqlFileName));
            while(inFile.hasNext()) {
                file.append(inFile.nextLine()).append("\n");
            }
            //System.out.println("----------------------------------\n"+file.toString()+"\n------------------------");
            PreparedStatement stmt=conn.prepareStatement(file.toString());
            stmt.execute();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            inFile.close();
            conn.close();
        }

    }



    DatabaseConnectionHandler getDatabase(){return database;}

    public Connection getConn() {
        return conn;
    }

    public void initDataBase() throws SQLException{
        updateTable(PATHConfig,"databaseConfig.sql");
        TriggerCheckNumCialde.initTrigger(conn);
        ViewMagazzino.initView(conn);
        ViewDebito.initView(conn);
        ViewCassa.initView(conn);
        updateTable(PATHInsert,"Insert.sql");
        System.out.println("---DIPEND");
        executeSelect("LATAZZASCHEMA.COMPRA_DIPENDENTE");
        System.out.println("---VISIT");
        executeSelect("LATAZZASCHEMA.COMPRA_VISITATORE");
        System.out.println("---CASSA");
        executeSelect("LATAZZASCHEMA.CASSA");


    }
    public void closeConnection() throws SQLException {
        database.closeDataBase();
    }

    private void executeSelect(String table) throws SQLException{
        stat=conn.prepareStatement("SELECT * from " + table);
        rs=stat.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
    }



}