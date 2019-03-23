package database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TriggersTest {

    private static final String URL="jdbc:h2:file:C:/Users/simoc/IdeaProjects/LaTazza/guiIS/src/database/config";
    public static void main(String[] args) throws SQLException {

        Connection conn= DriverManager.getConnection(URL);

        Trigger1 trigger1= new Trigger1(conn);

        trigger1.getStat().execute("CREATE TRIGGER CHECKNUMCIALDE " +
                "AFTER UPDATE ON LATAZZASCHEMA.COMPRA_VISITATORE FOR EACH ROW " +
                "CALL \"Trigger1.class.getname()\" ");

        trigger1.close();
    }
}