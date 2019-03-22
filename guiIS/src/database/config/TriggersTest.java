package database.config;

import java.sql.SQLException;

public class TriggersTest {

    private static final String URL="jdbc:h2:file:C:/Users/simoc/IdeaProjects/LaTazza/guiIS/src/database/config";
    public static void main(String[] args) throws SQLException {

        Trigger1 trigger1= new Trigger1(URL);


    }
}
