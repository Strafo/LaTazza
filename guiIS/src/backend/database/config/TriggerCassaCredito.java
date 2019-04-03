package backend.database.config;


import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;


public class TriggerCassaCredito extends ViewCassa implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerCassaCredito\"";
    private static final String TABLE_NAME = "LATAZZASCHEMA.PAGAMENTO_DEBITO";
    private static final String TRIGGER_NAME = "Update_Cassa_Credito";
    private static final String CREATE_TRIGGER = "CREATE TRIGGER " + TRIGGER_NAME + " AFTER INSERT ON " + TABLE_NAME + " FOR EACH ROW CALL " + TRIGGER_PATH;

    private static Euro getDebitoPagato(Connection conn, Object[] newRow) throws SQLException {

        ResultSet rs;
        PreparedStatement stat = conn.prepareStatement("select euro,centesimi " +
                "from " + TABLE_NAME +
                " where nome=? and cognome=? and data= ? ");

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.setTimestamp(3, (Timestamp) newRow[2]);
        rs = stat.executeQuery();
        if (rs.next()) return new Euro(rs.getLong(1),rs.getInt(2));
        return new Euro(0,0);
    }

    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        Euro incasso=getCurrent(conn).aggiungiImporto(getDebitoPagato(conn,newRow));
        PreparedStatement statD  = conn.prepareStatement("update " + TABLE_NAME_CASSA + " set euro= " +
                incasso.getEuro()+", centesimi= "+incasso.getCentesimi());
        statD.executeUpdate();


    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initView(Connection conn){

        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


