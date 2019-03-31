package backend.database.config;

import org.h2.api.Trigger;

import java.sql.*;

public class MaterializedViewCassaRifornimento implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.MaterializedViewCassaRifornimento\"";
    private static final String TABLE_NAME_RIFORNIMENTO="LATAZZASCHEMA.Rifornimento";
    private static final String TABLE_NAME_CASSA="LATAZZASCHEMA.CASSA";
    private static final String TRIGGER_NAME="Update_Table_Cassa_Rifornimento";
    private static final String CREATE_TRIGGER_STATEMENT_RIFORNIMENTO = "CREATE TRIGGER " + TRIGGER_NAME+ " AFTER INSERT ON "+ TABLE_NAME_RIFORNIMENTO+" FOR EACH ROW CALL "+TRIGGER_PATH;


    private static double getValue(Connection conn) throws SQLException {
        PreparedStatement stat= conn.prepareStatement("select sum(numero_cialde)*0.50 " +
                "from " + TABLE_NAME_RIFORNIMENTO );
        ResultSet rs=stat.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }



    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {


        double value=getValue(conn);
        //System.out.println("Value: "+ value);
        PreparedStatement stat= conn.prepareStatement("update "+TABLE_NAME_CASSA+" set importo= importo-?");
        stat.setDouble(1,value);
        stat.executeUpdate();

        //inizio Test

        /*
        PreparedStatement stat1= conn.prepareStatement("select importo from "+"LATAZZASCHEMA.CASSA");
        ResultSet rs= stat1.executeQuery();

        if(rs.next())
            System.out.println("Valore Aggiornato Dopo Rifornimento "+ rs.getDouble(1));

        */


        //fine test


    }


    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initView(Connection conn)  {
        Statement stat= null;
        try {
            stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER_STATEMENT_RIFORNIMENTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
