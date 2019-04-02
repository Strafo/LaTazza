package backend.database.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriggerDebito {
    private static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";

    protected static double getCurrentDebito(Connection conn, Object[] newRow)  throws SQLException {

        ResultSet rs;
        PreparedStatement stat= conn.prepareStatement("select importo " +
                "from " + TABLE_NAME_DEBITO+
                " where nome=? and cognome=?");
        System.out.println(newRow[0]+", "+newRow[1]);

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        rs=stat.executeQuery();
        if(rs.next()) return rs.getDouble(1);
        return 0.0;
    }

}
