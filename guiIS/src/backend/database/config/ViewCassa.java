package backend.database.config;

import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewCassa {

    protected static final String TABLE_NAME_CASSA = "LATAZZASCHEMA.CASSA";
    protected static final String TABLE_NAME_CIALDE="LATAZZASCHEMA.CIALDE";
    protected static Connection conn;
    protected static PreparedStatement stat;
    protected static ResultSet rs;


    protected static Euro getCurrent() throws SQLException {

        stat= conn.prepareStatement("select euro, centesimi " +
                "from " + TABLE_NAME_CASSA);
        rs=stat.executeQuery();
        if(rs.next()) return  new Euro(rs.getLong(1), rs.getInt(2));
        return new Euro(0,0);
    }

    protected static Euro getPrezzo(Object[] newRow) throws SQLException {

        stat = conn.prepareStatement("select prezzo_euro, prezzo_centesimi " +
                "from " + TABLE_NAME_CIALDE + " where tipo=?");

        stat.setNString(1, (String) newRow[2]);
        rs = stat.executeQuery();
        if (rs.next()) return new Euro(rs.getLong(1), rs.getInt(2));
        return new Euro(0, 0);
    }

    public static void initView(Connection conn){
        ViewCassaContanti.initView(conn);
        TriggerCassaCredito.initView(conn);
    }


}
