package database.config;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Trigger1 implements Trigger {


    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) throws SQLException {


    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        PreparedStatement stat;
        ResultSet cV, cP, magazzino;

        String q1="select sum(numero_cialde) " +
                " from LATAZZASCHEMA.COMPRA_VISITATORE T " +
                " where tipo_cialda = ?; ";
        String q2="select sum(numero_cialde)\n" +
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n" +
                " where tipo_cialda = ?";
        String q3="select sum(QTA*50)\n" +
                " from LATAZZASCHEMA.MAGAZZINO\n" +
                " where tipoCialda=?";

        stat= conn.prepareStatement(q1);
        stat.setNString (1, (String) newRow[2]); // sostituisce newRow[2] (tipo_cialda) al ?
        cV=stat.executeQuery(q1);
        cV.next();

        stat= conn.prepareStatement(q2);
        stat.setNString (1, (String) newRow[2]);
        cP = stat.executeQuery(q2);
        cP.next();

        stat= conn.prepareStatement(q3);
        stat.setNString (1, (String) newRow[2]);
        magazzino=stat.executeQuery(q3);
        magazzino.next();


        if ((cV.getInt((Integer) newRow[0]) + cP.getInt((Integer) newRow[0])) > magazzino.getInt((Integer) newRow[0]))
            throw new SQLException("Numero di cialde da comprare superiore a quelle disponibili in magazzino.");

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }
}
