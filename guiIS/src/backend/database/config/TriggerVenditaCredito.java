package backend.database.config;

import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;

public class TriggerVenditaCredito extends ViewDebito implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerVenditaCredito\"";
    private static final String TABLE_NAME_DIPENDENTE = "LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TABLE_NAME_CIALDE = "LATAZZASCHEMA.CIALDE";
    private static final String TRIGGER_NAME = "Update_Table_Debiti_Pagati";
    private static final String CREATE_TRIGGER_STATEMENT_DEBITO = "CREATE TRIGGER " + TRIGGER_NAME + " AFTER INSERT ON " + TABLE_NAME_DIPENDENTE + " FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final int timestamp = 4;

    private static Euro getPrezzo(Object[] newRow) throws SQLException {

        stat = connection.prepareStatement("select prezzo_euro, prezzo_centesimi  " +
                "from " + TABLE_NAME_CIALDE + " where tipo=?");

        stat.setNString(1, (String) newRow[2]);
        rs = stat.executeQuery();
        if (rs.next()) return new Euro(rs.getLong(euro), rs.getInt(centesimi));
        return new Euro(0, 0);
    }

    private static int getNumeroCialde(Object[] newRow) throws SQLException {
        stat = connection.prepareStatement("select numero_cialde " +
                "from " + TABLE_NAME_DIPENDENTE +
                " where  nome=? and cognome=? and data=? and contanti=false");
        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        stat.setTimestamp(3, (Timestamp) newRow[timestamp]);
        System.out.println("nome: " + newRow[nome] + " Cognome: " + newRow[cognome] + " timeStamp: " + newRow[timestamp] + " tipo: " + newRow[2]);
        rs = stat.executeQuery();
        if (rs.next()) return rs.getInt(1);
        return -1;
    }


    private static Euro getNewDebito(Object[] newRow) throws SQLException {

        int qtaCialde = getNumeroCialde(newRow);
        System.out.println("numCialde: " + qtaCialde);
        if (qtaCialde == -1)
            return new Euro(0, 0);//se la tupla cercata nella select viene precedentemente eliminata dal trigger CheckNumCialde
        Euro currentDebito = getDebitoCorrente(newRow);
        Euro importoVendita = getPrezzo(newRow);
        importoVendita.moltiplicaImporto(qtaCialde);
        System.out.println("importo: " + importoVendita.toString());
        return currentDebito.aggiungiImporto(importoVendita);

    }


    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {
        this.connection = connection;
    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {


        Euro importo = getNewDebito(newRow);
        stat = conn.prepareStatement("update " + TABLE_NAME_DEBITO + " set euro= "
                + importo.getEuro() + " ,centesimi= " + importo.getCentesimi() + "  where nome=? and cognome=? ");

        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        stat.executeUpdate();
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }


    public static void initTrigger(Connection conn) throws SQLException {

        stat = conn.prepareStatement(CREATE_TRIGGER_STATEMENT_DEBITO);
        stat.execute();
        stat.close();

    }
}