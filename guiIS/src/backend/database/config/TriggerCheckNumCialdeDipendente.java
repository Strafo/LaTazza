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


        if (checkNumCialde(conn, newRow) < 0 ) {

            deleteVendita(conn,TABLE_NAME_DIPENDENTE,newRow);
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
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
