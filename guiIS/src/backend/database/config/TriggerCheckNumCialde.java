package backend.database.config;
import org.h2.api.Trigger;

import java.sql.*;

public class TriggerCheckNumCialde implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.TriggerCheckNumCialde\"";
    private static final String TABLE_NAME_VISITATORE="LATAZZASCHEMA.COMPRA_VISITATORE";
    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TRIGGER_NAME_VISITATORE="check_num_cialde_visitatore";
    private static final String TRIGGER_NAME_DIPENDENTE="check_num_cialde_dipendente";
    private static final String CREATE_TRIGGER_STATEMENT_VISITATORE = "CREATE TRIGGER " + TRIGGER_NAME_VISITATORE + " AFTER INSERT ON "+ TABLE_NAME_VISITATORE+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_DIPENDETE = "CREATE TRIGGER " + TRIGGER_NAME_DIPENDENTE + " AFTER INSERT ON "+ TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;


    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) {


    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        PreparedStatement stat;
        ResultSet cV, cP, rif;

        String q1="select sum(numero_cialde) " +
                " from LATAZZASCHEMA.COMPRA_VISITATORE T " +
                " where tipo_cialda = ? ";
        String q2="select sum(numero_cialde)\n" +
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n" +
                " where tipo_cialda = ?";
        String q3="select sum(qta*50)\n" +
                " from LATAZZASCHEMA.RIFORNIMENTO\n" +
                " where tipo_cialda=?";


        stat= conn.prepareStatement(q1);
        stat.setNString (1, (String) newRow[2]); // sostituisce newRow[2] (tipo_cialda) al ?
        cV=stat.executeQuery();
        cV.next();

        stat= conn.prepareStatement(q2);
        stat.setNString (1, (String) newRow[2]);
        cP = stat.executeQuery();
        cP.next();

        stat= conn.prepareStatement(q3);
        stat.setNString (1, (String) newRow[2]);
        rif=stat.executeQuery();
        rif.next();


        if ((cV.getInt(1) + cP.getInt(1) > rif.getInt(1)))
            throw new SQLException("Numero di cialde da comprare superiore a quelle disponibili in magazzino.");

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

            stat.execute(CREATE_TRIGGER_STATEMENT_DIPENDETE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
