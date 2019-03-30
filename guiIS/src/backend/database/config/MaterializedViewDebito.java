package backend.database.config;

import org.h2.api.Trigger;

import java.sql.*;

public class MaterializedViewDebito implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.MaterializedViewDebito\"";
    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";
    private static final String TRIGGER_NAME="Update_Table_Debito";
    private static final String CREATE_TRIGGER_STATEMENT_DEBITO = "CREATE TRIGGER " + TRIGGER_NAME+ " AFTER INSERT ON "+ TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;




    private static double getDebito(Connection conn, Object[] newRow)  throws SQLException{

        PreparedStatement stat= conn.prepareStatement("select sum(numero_cialde * prezzo) " +
                                                             "from " + TABLE_NAME_DIPENDENTE +
                                                                " join LATAZZASCHEMA.CIALDE on (tipo=tipo_cialda) where contanti=false and nome=? and cognome=?" );
        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        ResultSet rs= stat.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }

        @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        double totDebito=  getDebito(conn, newRow);
        //per test
        //System.out.println(newRow[0]+" "+" "+newRow[1]+" : "+totDebito);
        PreparedStatement stat= conn.prepareStatement("update "+TABLE_NAME_DEBITO+" set importo= "
                                                            +totDebito+"where nome=? and cognome=?");
        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.executeUpdate();
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

            stat.execute(CREATE_TRIGGER_STATEMENT_DEBITO);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
