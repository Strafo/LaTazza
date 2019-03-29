package backend.database.config;
import org.h2.api.Trigger;

import java.sql.*;

public class TriggerCheckNumCialdeDipendente extends TriggerCheckNumCialde implements Trigger  {

    private static final String TRIGGER_PATH="\"backend.database.config.TriggerCheckNumCialdeDipendente\"";

    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TRIGGER_NAME_DIPENDENTE="check_num_cialde_dipendente";
    private static final String CREATE_TRIGGER_STATEMENT_DIPENDETE = "CREATE TRIGGER " + TRIGGER_NAME_DIPENDENTE + " AFTER INSERT ON "+ TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;

    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) {

    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {


        PreparedStatement stat;

        if (checkNumCialde(conn, newRow) < 0 ) {
           // throw new SQLException("Numero di cialde da comprare superiore a quelle disponibili in magazzino.");
            stat=conn.prepareStatement("DELETE from " + TABLE_NAME_DIPENDENTE + " where nome='"+ newRow[0] +"' and cognome='"+ newRow[1] +"' and data='" + newRow[5] +"'" );
            int num=stat.executeUpdate();

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

            stat.execute(CREATE_TRIGGER_STATEMENT_DIPENDETE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
