package backend.database.config;
import org.h2.api.Trigger;

import java.sql.*;

public class TriggerCheckNumCialdeVisitatore extends TriggerCheckNumCialde  implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.TriggerCheckNumCialdeVisitatore\"";
    private static final String TABLE_NAME_VISITATORE="LATAZZASCHEMA.COMPRA_VISITATORE";
    private static final String TRIGGER_NAME_VISITATORE="check_num_cialde_visitatore";
    private static final String CREATE_TRIGGER_STATEMENT_VISITATORE = "CREATE TRIGGER " + TRIGGER_NAME_VISITATORE + " AFTER INSERT ON "+ TABLE_NAME_VISITATORE+" FOR EACH ROW CALL "+TRIGGER_PATH;

    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) {

    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        PreparedStatement stat;
        if (checkNumCialde(conn, newRow) < 0 ) {
            //throw new SQLException("Numero di cialde da comprare superiore a quelle disponibili in magazzino.");
            stat=conn.prepareStatement("DELETE from " + TABLE_NAME_VISITATORE + " where nome='"+ newRow[nome] +"' and cognome='"+ newRow[cognome] +"' and data='" + newRow[timestamp] +"'" );
            int num=stat.executeUpdate();
            stat.close();
        }

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initTrigger(Connection conn)  {

        Statement stat= null;
        try {
            stat = conn.createStatement();

            stat.execute(CREATE_TRIGGER_STATEMENT_VISITATORE);
            stat.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
