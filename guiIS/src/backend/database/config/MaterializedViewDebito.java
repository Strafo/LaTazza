package backend.database.config;

import org.h2.api.Trigger;

import java.sql.*;

public class MaterializedViewDebito implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.MaterializedViewDebito\"";
    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";

    private static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";
    private static final String TRIGGER_NAME="Update_Table_Debiti_Pagati";
    private static final String CREATE_TRIGGER_STATEMENT_DEBITO = "CREATE TRIGGER " + TRIGGER_NAME+ " AFTER INSERT ON "+ TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;





    private static double getDebito(Connection conn, Object[] newRow)  throws SQLException{

        double debito = 0.0;
        PreparedStatement stat= conn.prepareStatement("select sum(numero_cialde)*0.50 " +
                                                             "from " + TABLE_NAME_DIPENDENTE +" T "+
                                                                " join LATAZZASCHEMA.CIALDE on (tipo=tipo_cialda) where contanti=false and nome=? and cognome=?" );
        //stat.setNString(1, (String) newRow[2]);
        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);


        ResultSet rs= stat.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }



    private static boolean isInDebit(Connection conn, String nome, String cognome) throws SQLException {
        ResultSet rs;
        PreparedStatement stat= conn.prepareStatement("select * " +
                "from LATAZZASCHEMA.Debito where nome='"+ nome+"' and cognome='"+cognome+"'");
        rs= stat.executeQuery();
        return rs.next();
    }

    private static void insertIfNotExist(Connection conn, String nome, String cognome) throws SQLException {

        PreparedStatement stat;
        if(!isInDebit(conn, nome, cognome)){
            stat= conn.prepareStatement("insert into LATAZZASCHEMA.DEBITO VALUES('"+nome+"', '"+cognome+"',"+0+" );");
            stat.executeUpdate();
        }

    }

    private  static void updateDebito(Connection conn, Object[] newRow){

    }

    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        insertIfNotExist(conn,(String) newRow[0],(String) newRow[1]);
        double debito=  getDebito(conn, newRow);
        //per test
        //System.out.println(newRow[0]+" "+" "+newRow[1]+" : "+totDebito);
        PreparedStatement stat= conn.prepareStatement("update "+TABLE_NAME_DEBITO+" set importo= "
                                                            +debito+" where nome=? and cognome=? ");
        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.executeUpdate();


       ResultSet resultSet;
        PreparedStatement prep;
                prep=conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.DEBITO " );
        resultSet=prep.executeQuery();
        while(resultSet.next())
            System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2)+" : "+ resultSet.getDouble(3) );





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
