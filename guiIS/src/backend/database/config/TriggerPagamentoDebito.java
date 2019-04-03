package backend.database.config;

import backend.businessLogicLayer.Euro;
import org.h2.api.Trigger;

import java.sql.*;


public class TriggerPagamentoDebito extends ViewDebito implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerPagamentoDebito\"";
    private static final String TABLE_NAME = "LATAZZASCHEMA.PAGAMENTO_DEBITO";
    private static final String TRIGGER_NAME = "Update_Table_Debito";
    private static final String CREATE_TRIGGER = "CREATE TRIGGER " + TRIGGER_NAME + " AFTER INSERT ON " + TABLE_NAME + " FOR EACH ROW CALL " + TRIGGER_PATH;

    private static Euro getDebitoPagato(Object[] newRow) throws SQLException {


        PreparedStatement stat = connection.prepareStatement("select euro,centesimi " +
                "from " + TABLE_NAME +
                " where nome=? and cognome=? and data= ? ");

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.setTimestamp(3, (Timestamp) newRow[2]);

        ResultSet rs = stat.executeQuery();
        if (rs.next()) return new Euro(rs.getLong(1), rs.getInt(2) );
        return new Euro(0,0);
    }

    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        Euro debito=getDebitoCorrente(newRow);
        Euro pagato=getDebitoPagato(newRow);
        debito.sottraiImporto(pagato);
        PreparedStatement stat = conn.prepareStatement("update " + TABLE_NAME_DEBITO + " set euro="
                + pagato.getEuro() + ", centesimi= "+ pagato.getCentesimi()+" where nome=? and cognome=? ");
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

    public static void initTrigger(Connection conn){
        Statement stat= null;
        try {
            stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER);
            stat.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


