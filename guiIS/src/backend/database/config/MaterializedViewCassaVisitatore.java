package backend.database.config;

import org.h2.api.Trigger;
import utils.Euro;

import java.sql.*;


public class MaterializedViewCassaVisitatore extends ViewCassa implements Trigger {

    private static final String TRIGGER_PATH = "\"backend.database.config.MaterializedViewCassaVisitatore\"";
    private static final String TABLE_NAME_COMPRA_VISITATORE = "LATAZZASCHEMA.Compra_Visitatore";
    private static final String TABLE_NAME_COMPRA_DIPENDENTE = "LATAZZASCHEMA.Compra_Dipendente";
    private static final String TRIGGER_NAME_VISITATORE = "Update_Table_Cassa_Visitatore";
    private static final String TRIGGER_NAME_DIPENDENTE = "Update_Table_Cassa_Dipendente";
    private static final String CREATE_TRIGGER_VISITATORE = "CREATE TRIGGER " + TRIGGER_NAME_VISITATORE+ " AFTER INSERT ON " + TABLE_NAME_COMPRA_VISITATORE + " FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final String CREATE_TRIGGER_DIPENDENTE = "CREATE TRIGGER " + TRIGGER_NAME_DIPENDENTE + " AFTER INSERT ON " + TABLE_NAME_COMPRA_DIPENDENTE + " FOR EACH ROW CALL " + TRIGGER_PATH;


    public static Euro multEuro(Euro euro1, Euro euro2){

        return new Euro(0,0);
    }


    private static Euro updateCassa(Connection conn, Object[] newRow, String tableName) throws SQLException {
        PreparedStatement stat= conn.prepareStatement("select numero_cialde " +
                "from " + tableName+ (tableName.equals(TABLE_NAME_COMPRA_DIPENDENTE)? " where contanti= true ":" ")+
                " and nome=? and cognome=? and data=? ");
        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.setTimestamp(3, (Timestamp) newRow[4]);
        ResultSet rs=stat.executeQuery();
        if(rs.next()){
            Euro prezzo=getPrezzo(conn, newRow);
            prezzo.moltiplicaImporto(rs.getInt(1));
            return  prezzo.aggiungiImporto(getCurrent(conn));
        }
        return new Euro(0,0);
    }


    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        Euro incassoVisitatore=  updateCassa(conn,newRow, TABLE_NAME_COMPRA_VISITATORE);
        Euro incassoDipendenti= updateCassa(conn, newRow, TABLE_NAME_COMPRA_DIPENDENTE);
        PreparedStatement statV = conn.prepareStatement("update " + TABLE_NAME_CASSA + " set euro= " +
                                                           incassoVisitatore.getEuro()+", centesimi= "+incassoVisitatore.getCentesimi());
        statV.executeUpdate();
        PreparedStatement statD  = conn.prepareStatement("update " + TABLE_NAME_CASSA + " set euro= " +
                incassoDipendenti.getEuro()+", centesimi= "+incassoDipendenti.getCentesimi());
        statD.executeUpdate();
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initView(Connection conn) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER_DIPENDENTE);
            stat.execute(CREATE_TRIGGER_VISITATORE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
