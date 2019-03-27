package database.config;

import database.DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.fail;


public class TriggersTest {

    private Connection conn;
    private DataBase database;
    private static final String URL="jdbc:h2:mem:default";


    public TriggersTest() throws SQLException, ClassNotFoundException {
        database= new DataBase(URL);
        database.initDataBase();
    }

    public DataBase getDatabase(){ return database;}

    public void updateTable(String sqlFilePath) {

        String currentDirPath = System.getProperty("user.dir");

        //String[] pathPart=currentDirPath.split("LaTazza/guiIs");//tapullata incredibile :D
       String path=currentDirPath+sqlFilePath;//pathPart[0];
        System.out.println("PATHHHH:  "+path);

        //PER IL DEBUGGING
        /*System.out.println("Current dir using System:" +currentDirPath);
        for(String i:pathPart) {
            System.out.println("part:" +i);
        }fail("END");*/

        try {
            //legge il file sql e lo suddivide in query
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            String[] parts=str.split(";");

            //PER DEBUGGING
            /*System.out.println("START:");int j=0;
            for(String i:parts) {
                System.out.println("part["+j+"]:" +i);
                j++;
            }
            System.out.println("END");*/


            conn = database.getConnection();
            Statement statement = conn.createStatement();
            //esegue le query
            for(String i:parts) {
                statement.execute(i);
            }

        } catch (IOException | SQLException e) {
            fail(e.getMessage());
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

    System.out.println("eeeeeeeeeeee "+ System.getProperty("user.dir"));


        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TriggersTest T= new TriggersTest();
        Connection conn= T.getDatabase().getConnection();
        Statement stat= conn.createStatement();
        T.updateTable("\\guiIS\\src\\database\\config\\databaseConfig.sql");
        T.updateTable("\\guiIS\\src\\database\\config\\DefaultEntrySet.sql");

        //ResultSet rs=stat.executeQuery();
        /*
        stat.execute("CREATE TRIGGER check_num_Cialde " +
                "AFTER INSERT ON LATAZZASCHEMA.COMPRA_VISITATORE FOR EACH ROW " +
                "CALL \"TriggersTest$Trigger1\" ");
        */
    }
}