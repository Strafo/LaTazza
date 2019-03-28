package backend.database.config;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriggerCheckNumCialde implements Trigger {


    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) {


    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        PreparedStatement stat;
        ResultSet cV, cP, rif;

        String q1="select sum(numero_cialde) " +
                " from LATAZZASCHEMA.COMPRA_VISITATORE T " +
                " where tipo_cialda = ? ";
        String q2="select sum(numero_cialde)\n" +
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n" +
                " where tipo_cialda = ?";
        String q3="select sum(qta*50)\n" +
                " from LATAZZASCHEMA.RIFORNIMENTO\n" +
                " where tipo_cialda=?";


        stat= conn.prepareStatement(q1);
        stat.setNString (1, (String) newRow[2]); // sostituisce newRow[2] (tipo_cialda) al ?
        cV=stat.executeQuery();
        cV.next();
        System.out.println("Somma cialde comprate dai Visitatori per lo stesso tipo:"+ cV.getInt(1));
        stat= conn.prepareStatement(q2);
        stat.setNString (1, (String) newRow[2]);
        cP = stat.executeQuery();
        cP.next();
        System.out.println("Somma cialde comprate dai Dipendenti per lo stesso tipo:"+ cP.getInt(1));

        stat= conn.prepareStatement(q3);
        stat.setNString (1, (String) newRow[2]);
        rif=stat.executeQuery();
        rif.next();
        System.out.println("Somma cialde Rifornimento per lo stesso tipo:"+ rif.getInt(1));


        if ((cV.getInt(1) + cP.getInt(1) > rif.getInt(1)))
            throw new SQLException("Numero di cialde da comprare superiore a quelle disponibili in magazzino.");

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }
}
