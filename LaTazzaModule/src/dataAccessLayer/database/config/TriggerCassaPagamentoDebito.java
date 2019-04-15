package dataAccessLayer.database.config;


import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;


public class TriggerCassaPagamentoDebito extends ViewCassa implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerCassaPagamentoDebito\"";
    private static final String TABLE_NAME = "LATAZZASCHEMA.PAGAMENTO_DEBITO";
    private static final String TRIGGER_NAME = "Update_Cassa_Pagamento_Debito";
    private static final String CREATE_TRIGGER = "CREATE TRIGGER " + TRIGGER_NAME + " AFTER INSERT ON " + TABLE_NAME + " FOR EACH ROW CALL " + TRIGGER_PATH;




    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
            Euro pagamento=TriggerDebitoPagamentoDebito.getDebitoPagato(conn,newRow);
            Euro incasso=getCassaCorrente(conn).aggiungiImporto(pagamento);

            updateCassa(conn,incasso);
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


