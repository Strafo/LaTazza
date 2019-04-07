package backend.database.config;

import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewDebito {
    //SUPPONENDO CHE NESSUN PAGAMENTO SIA MAGGIORE DEL DEBITO
    protected static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";
    protected static Connection connection;
    protected static  ResultSet rs;
    protected static PreparedStatement stat;
    protected static final int nome=0;
    protected static final int cognome=1;
    protected static final int attivo=2;
    protected static final int euro=1;
    protected static final int centesimi=2;


    protected static void updateDebito(Connection conn,Euro debito, Object[] newRow) throws  SQLException{
        stat = conn.prepareStatement("update " + TABLE_NAME_DEBITO + " set euro="
                + debito.getEuro() + ", centesimi= "+ debito.getCentesimi()+" where nome=? and cognome=? ");
        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        stat.executeUpdate();
    }


    protected static Euro getDebitoCorrente(Connection conn,Object[] newRow)  throws SQLException {


        stat= conn.prepareStatement("select euro, centesimi " +
                "from " + TABLE_NAME_DEBITO+
                " where nome=? and cognome=?");

        stat.setNString(1, (String) newRow[nome]);
        stat.setNString(2, (String) newRow[cognome]);
        rs=stat.executeQuery();

        if(rs.next()) return new Euro(rs.getLong(euro), rs.getInt(centesimi));
        return new Euro(0,0);//se la tupla cercata nella select viene precedentemente eliminata dal trigger CheckNumCialde
    }

    public static void initView(Connection conn) throws SQLException {
        TriggerDebitoPagamentoDebito.initTrigger(conn);
        TriggerDebitoVenditaCredito.initTrigger(conn);
        TriggerDebitoModifyPersonale.initTrigger(conn);
    }


}
