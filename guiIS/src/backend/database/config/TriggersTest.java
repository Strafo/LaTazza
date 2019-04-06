package backend.database.config;


import backend.database.DataBase;
import backend.database.config.TriggerCheckNumCialde;
import backend.database.config.ViewCassa;
import backend.database.config.ViewDebito;
import backend.database.config.ViewMagazzino;

import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;


public class TriggersTest {
    //C:\Users\jacop\Desktop\dev\LaTazza\guiIS\src\backend\database\config\databaseConfig.sql
    private Connection conn;
    private DataBase database;
    private static final String URL="jdbc:h2:mem:databaseTest";
    //private static final String URL="jdbc:h2:C:/Users/simoc/IdeaProjects/LaTazza/guiIS/src/database/config";
    //private final String PATHConfig="guiIS\\src\\backend\\database\\config\\databaseConfig.sql";
    private final String PATHConfig="guiIS\\src\\backend\\database\\config\\databaseConfig.sql";
    private final String PATHInsert="guiIS\\src\\test\\testBackend\\Insert.sql";
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


    private void updateTable(String path) throws SQLException {

        try {
            StringBuilder file= new StringBuilder();
            inFile= new Scanner(new FileReader(path));
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

    public Connection getConn() {
        return conn;
    }

    public void initDataBase() throws SQLException{
        updateTable(PATHConfig);
        TriggerCheckNumCialde.initTrigger(conn);
        ViewMagazzino.initView(conn);
        ViewDebito.initView(conn);
        ViewCassa.initView(conn);
        updateTable(PATHInsert);

    }
    public void closeConnection() throws SQLException {
        database.closeDataBase();
    }



}