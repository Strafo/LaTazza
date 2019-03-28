package backend.database.config;


import backend.dataAccessLayer.gatewaysPkg.DaoManager;
import backend.database.DataBase;

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
    private final String PATH="guiIS\\src\\backend\\database\\config\\";
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
            System.out.println("PATH:  "+PATH+sqlFileName);
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

        schemaExists=true;

    }
    DataBase getDatabase(){return database;}


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        TriggersTest T= new TriggersTest();
        Connection conn= T.getDatabase().getConnection();
        Statement stat= conn.createStatement();
        T.updateTable("databaseConfig.sql");

        try {
            stat.execute("CREATE TRIGGER check_num_Cialde " +
                    "AFTER INSERT ON LATAZZASCHEMA.COMPRA_VISITATORE FOR EACH ROW " +
                    "CALL \"backend.database.config.TriggerCheckNumCialde\" ");
        }catch (SQLException e){
            System.out.println("Cristo Morto: " );
            e.printStackTrace();
        }

        T.updateTable("Insert.sql");
        DaoManager dao= new DaoManager(conn);
/*
        List<Personale> listaPersonale=dao.getAll(Personale.class);//ottengo la lista del personale nel DB.
        for (Personale p: listaPersonale) {
            System.out.println(p.toString());
        }

*/
        T.getDatabase().closeDataBase();
    }


}