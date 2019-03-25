package database.config;

import java.sql.*;

public class TriggersTest {

    private static final String URL="jdbc:h2:file:C:/Users/simoc/IdeaProjects/LaTazza/guiIS/src/database/config";
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn= DriverManager.getConnection(URL);



        Statement stat= conn.createStatement();
        ResultSet rs=stat.executeQuery();
        /*
        stat.execute("CREATE TRIGGER check_num_Cialde " +
                "AFTER INSERT ON LATAZZASCHEMA.COMPRA_VISITATORE FOR EACH ROW " +
                "CALL \"database.congig.TriggersTest$Trigger1\" ");
    */
    }
}