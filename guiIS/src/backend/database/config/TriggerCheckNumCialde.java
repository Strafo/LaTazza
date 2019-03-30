package backend.database.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriggerCheckNumCialde{
    private static final int tipoCialda=2;

    protected static int checkNumCialde(Connection conn, Object[] newRow)throws SQLException {


        PreparedStatement stat;
        ResultSet acquistiVisitatore, acquistiPersonale, rifornimento;

        String queryVisitatore="select sum(numero_cialde) " +
                " from LATAZZASCHEMA.COMPRA_VISITATORE T " +
                " where tipo_cialda = ? ";
        String queryDipendente="select sum(numero_cialde)\n" +
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n" +
                " where tipo_cialda = ?";
        String queryRifornimenti="select sum(numero_cialde)\n" +
                " from LATAZZASCHEMA.RIFORNIMENTO\n" +
                " where tipo_cialda=?";


        stat= conn.prepareStatement(queryVisitatore);
        stat.setNString (1, (String) newRow[tipoCialda]); // sostituisce newRow[2] (tipo_cialda) al ?
        acquistiVisitatore=stat.executeQuery();
        acquistiVisitatore.next();

        stat=conn.prepareStatement(queryDipendente);
        stat.setNString (1, (String) newRow[tipoCialda]);
        acquistiPersonale = stat.executeQuery();
        acquistiPersonale.next();

        stat= conn.prepareStatement(queryRifornimenti);
        stat.setNString (1, (String) newRow[tipoCialda]);
        rifornimento=stat.executeQuery();
        rifornimento.next();


        return   rifornimento.getInt(1) - (acquistiVisitatore.getInt(1) + acquistiPersonale.getInt(1));
    }
}
