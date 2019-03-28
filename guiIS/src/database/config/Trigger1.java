package database.config;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Trigger1 implements Trigger {


    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) throws SQLException {

        System.out.println("Madonna scostumata");
    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        System.out.println("RE:Madonna scostumata");
        PreparedStatement stat;
        ResultSet cV, cP, rif;

        System.out.println("1");
        String q1="select sum(numero_cialde) " +
                " from LATAZZASCHEMA.COMPRA_VISITATORE T " +
                " where tipo_cialda = ? ";
        String q2="select sum(numero_cialde)\n" +
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n" +
                " where tipo_cialda = ?";
        String q3="select sum(qta*50)\n" +
                " from LATAZZASCHEMA.RIFORNIMENTO\n" +
                " where tipoCialda=?";
        System.out.println("2");


        stat= conn.prepareStatement(q1);
        System.out.println("3");
        stat.setNString (1, (String) newRow[2]); // sostituisce newRow[2] (tipo_cialda) al ?
        System.out.println("4");
        cV=stat.executeQuery(q1);
        System.out.println("5");
        System.out.println("cv:"+ cV.getInt(1));
        cV.next();
        System.out.println("6");
        stat= conn.prepareStatement(q2);
        stat.setNString (1, (String) newRow[2]);
        cP = stat.executeQuery(q2);
        cP.next();

        stat= conn.prepareStatement(q3);
        stat.setNString (1, (String) newRow[2]);
        rif=stat.executeQuery(q3);
        rif.next();


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
