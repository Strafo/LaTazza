package dataAccessLayer.database.config;

import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewCassa {

    //SUPPONENDO CHE NESSUNA OPERAZIONE FACCIA DIVENTARE NEGATIVO IL SALDO DELLA CASSA
    protected static final String TABLE_NAME_CASSA = "LATAZZASCHEMA.CASSA";
    protected static Connection connection;
    protected static PreparedStatement stat;
    protected static ResultSet rs;
    protected static final int nome=0;
    protected static final int cognome=1;


    protected static void updateCassa(Connection conn,Euro importo) throws SQLException{

        stat = conn.prepareStatement("update " + TABLE_NAME_CASSA + " set euro= " + importo.getEuro()+", centesimi= "+importo.getCentesimi());
        stat.executeUpdate();
    }

    protected static Euro getCassaCorrente(Connection conn) throws SQLException {

        stat= conn.prepareStatement("select euro, centesimi " +
                "from " + TABLE_NAME_CASSA);
        rs=stat.executeQuery();
        if(rs.next()) return  new Euro(rs.getLong(1), rs.getInt(2));
        return new Euro(0,0);
    }


    public static void initView(Connection conn)throws SQLException{
        TriggerCassaPagamentoDebito.initTrigger(conn);
        TriggerCassaVendita.initTrigger(conn);
        TriggerCassaRifornimento.initTrigger(conn);
    }


}
