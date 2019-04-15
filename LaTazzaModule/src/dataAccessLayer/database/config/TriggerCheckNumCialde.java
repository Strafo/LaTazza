package dataAccessLayer.database.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriggerCheckNumCialde{
    protected static Connection connection;

    protected static final String TABLE_NAME_VISITATORE="LATAZZASCHEMA.COMPRA_VISITATORE";
    protected static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    protected static final String TABLE_NAME_RIFORNIMENTO="LATAZZASCHEMA.RIFORNIMENTO";
    private static final int tipoCialda=2;
    protected static final int nome=0;
    protected static final int cognome=1;
    protected static final int timestamp=4;

    public static void initTrigger(Connection conn)  {
        TriggerCheckNumCialdeDipendente.initTrigger(conn);
        TriggerCheckNumCialdeVisitatore.initTrigger(conn);

    }

    protected static void deleteVendita(Connection conn,String table, Object[] newRow) throws SQLException {
        PreparedStatement stat;
        stat=conn.prepareStatement("DELETE from " + table + " where nome='"+ newRow[nome] +"' and cognome='"+ newRow[cognome] +"' and data='" + newRow[timestamp] +"'" );
        stat.executeUpdate();
    }


    protected static int checkNumCialde(Connection conn, Object[] newRow)throws SQLException {

        return  TriggerMagazzinoUpdate.statoCialdaMagazzino(conn,newRow) ;
    }
}
