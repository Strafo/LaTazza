package backend.database.config;

import org.h2.api.Trigger;

import java.sql.*;


public class TriggerPagamentoDebito extends TriggerDebito implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerPagamentoDebito\"";
    private static final String TABLE_NAME = "LATAZZASCHEMA.PAGAMENTO_DEBITO";
    private static final String TRIGGER_NAME = "Update_Table_Debito";
    private static final String CREATE_TRIGGER = "CREATE TRIGGER " + TRIGGER_NAME + " AFTER INSERT ON " + TABLE_NAME + " FOR EACH ROW CALL " + TRIGGER_PATH;

    private static double getDebitoPagato(Connection conn, Object[] newRow) throws SQLException {


        PreparedStatement stat = conn.prepareStatement("select importo " +
                "from " + TABLE_NAME +
                " where nome=? and cognome=? and data= ? ");

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.setTimestamp(3, (Timestamp) newRow[2]);

        ResultSet rs = stat.executeQuery();
        if (rs.next()) return rs.getDouble(1);
        return 0.0;
    }

    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        PreparedStatement stat = conn.prepareStatement("update " + TABLE_NAME_DEBITO + " set importo="
                + (getCurrentDebito(conn, newRow)-getDebitoPagato(conn, newRow)) + " where nome=? and cognome=? ");
        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.executeUpdate();

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initView(Connection conn){
        initView(conn,CREATE_TRIGGER);
    }
}


