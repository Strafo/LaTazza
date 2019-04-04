package backend.database.config;

import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewDebito {
    protected static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";
    protected static Connection connection;
    protected static  ResultSet rs;
    protected static PreparedStatement stat;
    protected static final int nome=0;
    protected static final int cognome=1;
    protected static final int attivo=2;
    protected static final int euro=1;
    protected static final int centesimi=2;

    protected static Euro getDebitoCorrente(Object[] newRow)  throws SQLException {


        stat= connection.prepareStatement("select euro, centesimi " +
                "from " + TABLE_NAME_DEBITO+
                " where nome=? and cognome=?");

        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        rs=stat.executeQuery();
        if(rs.next()) return new Euro(rs.getLong(euro), rs.getInt(centesimi));
        return new Euro(0,0);
    }

    public static void initView(Connection conn) throws SQLException {
        TriggerPagamentoDebito.initTrigger(conn);
        TriggerVenditaCredito.initTrigger(conn);
        TriggerDebitoModifyPersonale.initTrigger(conn);
    }


}
