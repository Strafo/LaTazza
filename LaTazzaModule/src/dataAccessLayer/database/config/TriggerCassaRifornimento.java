package dataAccessLayer.database.config;

import org.h2.api.Trigger;
import utils.Euro;
import java.sql.*;

public class TriggerCassaRifornimento extends ViewCassa implements Trigger {

    private static final String TRIGGER_PATH="\"dataAccessLayer.database.config.TriggerCassaRifornimento\"";
    private static final String TABLE_NAME_RIFORNIMENTO="LATAZZASCHEMA.Rifornimento";
    private static final String TRIGGER_NAME="Update_Table_Cassa_Rifornimento";
    private static final String CREATE_TRIGGER_STATEMENT_RIFORNIMENTO = "CREATE TRIGGER " + TRIGGER_NAME+ " AFTER INSERT ON "+ TABLE_NAME_RIFORNIMENTO+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final int costoCialdeEuro= 0;
    private static final int costoCialdeCentesimi=40;
    private static final int qtaCialde=1;

    private static Euro nuovaCassa(Connection conn,int numeroCialde) throws SQLException {
        Euro costo=new Euro(costoCialdeEuro,costoCialdeCentesimi);
        costo.moltiplicaImporto(numeroCialde);
        Euro cassaCorrente=getCassaCorrente(conn);
        return cassaCorrente.sottraiImporto(costo);
    }



    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
        connection=conn;
        int quantita= (int) newRow[qtaCialde];
        Euro nuovaCassa= nuovaCassa(conn,quantita);

        updateCassa(conn,nuovaCassa);
    }


    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initTrigger(Connection conn)  {

        try {
            Statement stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER_STATEMENT_RIFORNIMENTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
