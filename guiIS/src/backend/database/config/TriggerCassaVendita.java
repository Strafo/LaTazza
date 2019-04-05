package backend.database.config;

import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;


public class TriggerCassaVendita extends ViewCassa implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.TriggerCassaVendita\"";
    private static final String TABLE_NAME_COMPRA_VISITATORE = "LATAZZASCHEMA.Compra_Visitatore";
    private static final String TABLE_NAME_COMPRA_DIPENDENTE = "LATAZZASCHEMA.Compra_Dipendente";
    private static final String TRIGGER_NAME_VISITATORE = "Update_Table_Cassa_Visitatore";
    private static final String TRIGGER_NAME_DIPENDENTE = "Update_Table_Cassa_Dipendente";
    private static final String CREATE_TRIGGER_VISITATORE = "CREATE TRIGGER " + TRIGGER_NAME_VISITATORE+ " AFTER INSERT ON " + TABLE_NAME_COMPRA_VISITATORE + " FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final String CREATE_TRIGGER_DIPENDENTE = "CREATE TRIGGER " + TRIGGER_NAME_DIPENDENTE + " AFTER INSERT ON " + TABLE_NAME_COMPRA_DIPENDENTE + " FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final int nome=0;
    private static final int cognome=1;
    private static final int contanti=5;
    private static final int timestamp=4;

    private static Euro nuovoImporto(Connection conn,Euro prezzo, int numeroCialde) throws SQLException{
        prezzo.moltiplicaImporto(numeroCialde);
        return  prezzo.aggiungiImporto(getCassaCorrente(conn));
    }

    private static int getNumeroCialde(Connection conn,Object[] newRow, String tableName) throws SQLException{
        String whereCondition=(tableName.equals(TABLE_NAME_COMPRA_DIPENDENTE)? " contanti=true and ":"");

        stat= conn.prepareStatement("select numero_cialde " + " from " + tableName+" where"+ whereCondition +
                " nome=? and cognome=? and data=? ");
        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        stat.setTimestamp(3, (Timestamp) newRow[timestamp]);
        ResultSet rs=stat.executeQuery();
        if(!rs.next()) return -1;
        return rs.getInt(1);
    }

    private static Euro nuovaCassa (Connection conn,Object[] newRow, String tableName) throws SQLException {

        int numeroCialde= getNumeroCialde(conn,newRow,tableName);
        if( numeroCialde ==-1) return   getCassaCorrente(conn);
        Euro prezzo=TriggerDebitoVenditaCredito.getPrezzo(conn,newRow);
        return nuovoImporto(conn,prezzo,numeroCialde);

    }


    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection connection, Object[] oldRow, Object[] newRow) throws SQLException {

        Euro importo;
        if(newRow.length == 5) importo= nuovaCassa(connection,newRow, TABLE_NAME_COMPRA_VISITATORE);
        else // vuol dire che c'è il campo 'modalita' in più e quindi è stato attivato da 'Update_Table_Cassa_Dipendente'
            {
            boolean isContanti=(boolean)newRow[contanti];
            if(!isContanti) return;
            importo = nuovaCassa(connection, newRow, TABLE_NAME_COMPRA_DIPENDENTE);
            }


        updateCassa(connection,importo);

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initTrigger(Connection conn) {
        try {
            Statement stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER_DIPENDENTE);
            stat.execute(CREATE_TRIGGER_VISITATORE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
