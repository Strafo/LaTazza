package dataAccessLayer.database.config;

import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;


public class TriggerDebitoPagamentoDebito extends ViewDebito implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerDebitoPagamentoDebito\"";
    private static final String TABLE_NAME = "LATAZZASCHEMA.PAGAMENTO_DEBITO";
    private static final String TRIGGER_NAME = "Update_Table_Pagamento_Debito";
    private static final String CREATE_TRIGGER = "CREATE TRIGGER " + TRIGGER_NAME + " AFTER INSERT ON " + TABLE_NAME + " FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final int timestamp=2;

    public static Euro getDebitoPagato(Connection conn,Object[] newRow) throws SQLException {


        stat = conn.prepareStatement("select euro,centesimi " +
                "from " + TABLE_NAME +
                " where nome=? and cognome=? and data= ? ");

        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        stat.setTimestamp(3, (Timestamp) newRow[timestamp]);
        rs = stat.executeQuery();
        if (rs.next()) return new Euro(rs.getLong(euro), rs.getInt(centesimi) );
        return new Euro(0,0);//se la tupla cercata nella select viene precedentemente eliminata dal trigger CheckNumCialde
    }

    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        Euro debito= getDebitoCorrente(conn,newRow);
        Euro pagato=getDebitoPagato(conn,newRow);
        debito.sottraiImporto(pagato);
        updateDebito(conn,debito,newRow);

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


