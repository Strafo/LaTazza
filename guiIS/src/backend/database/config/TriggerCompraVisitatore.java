package backend.database.config;

import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;

public class TriggerCompraVisitatore implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerCompraVisitatore\"";
    private static final String TABLE_NAME_COMPRA = "LATAZZASCHEMA.COMPRA_VISITATORE";
    private static final String TABLE_NAME_VISITATORE = "LATAZZASCHEMA.VISITATORE";
    private static final String TRIGGER_NAME = "Insert_Visitatore";
    private static final String CREATE_TRIGGER = "CREATE TRIGGER " + TRIGGER_NAME + " BEFORE INSERT ON "+ TABLE_NAME_COMPRA +" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static PreparedStatement stat;
    private static ResultSet rs;
    private static Connection connection;
    protected static final int nome=0;
    protected static final int cognome=1;

    private boolean existsVisitatore(Object[] newRow) throws SQLException{
        stat= connection.prepareStatement("select * " +
                "from " + TABLE_NAME_VISITATORE +" where nome=? and cognome=? " );

        stat.setString(1, (String) newRow[nome]);
        stat.setString(1, (String) newRow[cognome]);
        rs= stat.executeQuery();
        return rs.next();
    }
    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {
        this.connection=connection;
    }

    @Override
    public void fire(Connection connection, Object[] oldRow, Object[] newRow) throws SQLException {
        if(!existsVisitatore(newRow)){
            stat= connection.prepareStatement("insert into "+TABLE_NAME_VISITATORE+" values (?, ?)" );

            stat.setString(1, (String) newRow[nome]);
            stat.setString(1, (String) newRow[cognome]);
            stat.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initTrigger(Connection conn){
        try {
            Statement stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
