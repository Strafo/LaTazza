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
        TriggerMagazzino.initView(conn);

        //MaterializedViewDebito.initView(conn);




        //TriggerPagamentoDebito.initView(conn);

        //MaterializedViewCassaVisitatore.initView(conn);

        T.updateTable("Insert.sql");


/*
        PreparedStatement stat;
        ResultSet resultSet;


        stat =conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.compra_dipendente" );
        resultSet=stat.executeQuery();
        while(resultSet.next())
            System.out.println(resultSet.getString(1)+", "+resultSet.getString(2)+", "+resultSet.getNString(3)+", "+ resultSet.getBoolean(4)+", "+resultSet.getInt(5)+", "+resultSet.getTimestamp(6));
        */
System.out.println("--------------MAIN----------------------------------");
        ResultSet rs;
        PreparedStatement prep;
        prep=conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.DEBITO " );
        rs=prep.executeQuery();
        while(rs.next())
            System.out.println("\n"+rs.getString(1) + ", " + rs.getString(2)+": "+ rs.getDouble(3));




        T.getDatabase().closeDataBase();
    }


}