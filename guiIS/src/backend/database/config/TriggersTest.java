package backend.database.config;


import backend.database.DataBase;

import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;


public class TriggersTest {

    private Connection conn;
    private DataBase database;
    private static final String URL="jdbc:h2:mem:databaseTest";
    //private static final String URL="jdbc:h2:C:/Users/simoc/IdeaProjects/LaTazza/guiIS/src/database/config";
    private final String PATH="guiIS\\src\\backend\\database\\config\\";
    private Scanner inFile;



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

            //stmt.addBatch(file.toString());
            stmt.executeUpdate(file.toString());
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            inFile.close();
            conn.close();
        }

    }
    DataBase getDatabase(){return database;}


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        TriggersTest T= new TriggersTest();
        Connection conn= T.getDatabase().getConnection();
        T.updateTable("databaseConfig.sql");
        TriggerCheckNumCialdeVisitatore.initTrigger(conn);
        TriggerCheckNumCialdeDipendente.initTrigger(conn);
        MaterializedViewMagazzino.initView(conn);
        MaterializedViewDebito.initView(conn);
        MaterializedViewCassaRifornimento.initView(conn);
        T.updateTable("Insert.sql");

//gesu

        T.getDatabase().closeDataBase();
    }


}