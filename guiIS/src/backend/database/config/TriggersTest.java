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
        ViewCassa.initView(conn);

        T.updateTable("Insert.sql");


System.out.println("--------------MAIN---------------------------------- \nDEBITO:\n");
        ResultSet rs;
        PreparedStatement prep;
        prep=conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.DEBITO " );
        rs=prep.executeQuery();
        while(rs.next())
            System.out.println("\n"+rs.getString(1) + ", " + rs.getString(2)+": "+ rs.getLong(3)+"."+rs.getInt(4));

        System.out.println("------------------------------------------------ \nCOMPRA_DIPENDENTE:\n");

        prep=conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.Compra_Dipendente " );
        rs=prep.executeQuery();
        while(rs.next())
            System.out.println("\n"+rs.getString(1) + ", " + rs.getString(2)+", "+ rs.getNString(3)+","+rs.getInt(4)+","+rs.getTimestamp(5));
        System.out.println("------------------------------------------------");
        prep=conn.prepareStatement("select * from LATAZZASCHEMA.CASSA");
        rs=prep.executeQuery();
        while(rs.next())
            System.out.println("CASSA: "+rs.getLong(1)+"."+rs.getInt(2)+" euro");

        T.getDatabase().closeDataBase();


    }



}