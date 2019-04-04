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



    DataBase getDatabase(){return database;}


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        TriggersTest T= new TriggersTest();
        Connection conn= T.getDatabase().getConnection();
        T.updateTable("databaseConfig.sql");
        TriggerCheckNumCialde.initTrigger(conn);
        ViewMagazzino.initView(conn);
        ViewDebito.initView(conn);

        //MaterializedViewCassaVisitatore.initView(conn);

        T.updateTable("Insert.sql");

        ResultSet rs;
        PreparedStatement prep;
/*
        System.out.println("--------------MAIN----------------------------------");

        prep=conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.COMPRA_DIPENDENTE " );
        rs=prep.executeQuery();
        while(rs.next())
            System.out.println("\n"+rs.getString(1) + ", " + rs.getString(2)+": "+ rs.getString(3));
*/
/*
System.out.println("--------------MAIN----------------------------------");

        prep=conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.DEBITO " );
        rs=prep.executeQuery();
        while(rs.next())
            System.out.println("\n"+rs.getString(1) + ", " + rs.getString(2)+": "+ rs.getInt(3)+ "." + rs.getInt(4));
*/
        T.getDatabase().closeDataBase();
    }


}