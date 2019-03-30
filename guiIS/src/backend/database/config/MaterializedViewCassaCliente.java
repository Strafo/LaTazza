package backend.database.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterializedViewCassaCliente {

    private static double getValue(Connection conn,String table) throws SQLException {
        PreparedStatement stat= conn.prepareStatement("select sum(numero_cialde)*50 " +
                "from " + table  +
                " where contanti=true" );
        ResultSet rs=stat.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }
}
