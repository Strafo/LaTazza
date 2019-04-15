package dataAccessLayer.database.config;

import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;

public class TriggerDebitoVenditaCredito extends ViewDebito implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.TriggerDebitoVenditaCredito\"";
    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TABLE_NAME_CIALDE="LATAZZASCHEMA.CIALDE";
    private static final String TRIGGER_NAME="Update_Table_Debiti_Pagati";
    private static final String CREATE_TRIGGER_STATEMENT_DEBITO = "CREATE TRIGGER " + TRIGGER_NAME+ " AFTER INSERT ON "+TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final int timestamp=4;
    private static final int contanti=5;
    private static final int tipoCialda=2;

    public static Euro getPrezzo(Connection conn, Object[] newRow) throws SQLException {


         stat= conn.prepareStatement("select prezzo_euro, prezzo_centesimi  " +
                "from " + TABLE_NAME_CIALDE+" where tipo=?" );

        stat.setNString(1, (String) newRow[tipoCialda]);
        rs= stat.executeQuery();
        if(rs.next()) return new Euro(rs.getLong(euro), rs.getInt(centesimi));
        return new Euro(0,0);
    }

    private static int getNumeroCialde(Connection conn,Object[] newRow) throws SQLException{
        stat= conn.prepareStatement("select numero_cialde " +
                "from " + TABLE_NAME_DIPENDENTE +
                " where contanti=false and nome=? and cognome=? and data=?" );
        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        stat.setTimestamp(3, (Timestamp) newRow[timestamp]);
        rs= stat.executeQuery();
        if(!rs.next()) return -1;//se la tupla cercata nella select viene precedentemente eliminata dal trigger CheckNumCialde
        return rs.getInt(1);
    }


    private static Euro getNewDebito(Connection conn,Object[] newRow)  throws SQLException{

        int qtaCialde= getNumeroCialde(conn,newRow);
        Euro currentDebito= getDebitoCorrente(conn, newRow);
        if(qtaCialde==-1)
            return currentDebito;//se la tupla cercata nella select viene precedentemente eliminata dal trigger CheckNumCialde
        Euro importoVendita= getPrezzo(conn,newRow);
        importoVendita.moltiplicaImporto(qtaCialde);
        return currentDebito.aggiungiImporto(importoVendita);

    }


    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {


        boolean isContanti=(boolean)newRow[contanti];
        if(isContanti) return;

        Euro importo=getNewDebito(conn, newRow);
        updateDebito(conn,importo,newRow);
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }



    public static void initTrigger(Connection conn){
        Statement stat= null;
        try {
            stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER_STATEMENT_DEBITO);
            stat.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
