package backend.database.config;

import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewDebito {
    protected static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";
    protected static Connection connection;

    protected static Euro getDebitoCorrente(Object[] newRow)  throws SQLException {

        ResultSet rs;
        PreparedStatement stat= connection.prepareStatement("select euro, centesimi " +
                "from " + TABLE_NAME_DEBITO+
                " where nome=? and cognome=?");

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        rs=stat.executeQuery();
        if(rs.next()) return new Euro(rs.getLong(1), rs.getInt(2));
        return new Euro(0,0);
    }

    public static void initView(Connection conn) {
        TriggerPagamentoDebito.initTrigger(conn);
        TriggerVenditaCredito.initTrigger(conn);
    }


}
