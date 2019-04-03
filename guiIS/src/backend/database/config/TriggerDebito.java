package backend.database.config;

import java.sql.*;

public class TriggerDebito {
    protected static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";

    protected static double getCurrentDebito(Connection conn, Object[] newRow)  throws SQLException {

        ResultSet rs;
        PreparedStatement stat= conn.prepareStatement("select importo " +
                "from " + TABLE_NAME_DEBITO+
                " where nome=? and cognome=?");

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        rs=stat.executeQuery();
        if(rs.next()) return rs.getDouble(1);
        return 0.0;
    }

    protected static void initView(Connection conn, String trigger) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(trigger);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
