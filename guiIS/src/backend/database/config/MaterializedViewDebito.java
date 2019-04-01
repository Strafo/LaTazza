package backend.database.config;

import org.h2.api.Trigger;

import java.sql.*;

public class MaterializedViewDebito implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.MaterializedViewDebito\"";
    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";

    private static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";
    private static final String TABLE_NAME_CIALDE="LATAZZASCHEMA.CIALDE";
    private static final String TRIGGER_NAME="Update_Table_Debiti_Pagati";
    private static final String CREATE_TRIGGER_STATEMENT_DEBITO = "CREATE TRIGGER " + TRIGGER_NAME+ " AFTER INSERT ON "+TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;



    private static double getPrezzo(Connection conn, Object[] newRow) throws SQLException {

        double num=0.0;
        ResultSet rs;
        PreparedStatement stat= conn.prepareStatement("select prezzo " +
                "from " + TABLE_NAME_CIALDE+" where tipo=?" );

        stat.setNString(1, (String) newRow[2]);
        rs= stat.executeQuery();
        if(rs.next()) num=rs.getDouble(1);
        System.out.println("Prezzo: "+num);
        return num;
    }

    private static double getCurrentDebito(Connection conn, Object[] newRow)  throws SQLException{

        double num=0.0;
        ResultSet rs;
        PreparedStatement stat= conn.prepareStatement("select importo " +
                "from " + TABLE_NAME_DEBITO+
                " where nome=? and cognome=?");
        System.out.println(newRow[0]+", "+newRow[1]);

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        rs=stat.executeQuery();
        if(rs.next()) num=rs.getDouble(1);
        System.out.println("CurrentDebito: "+num);
        System.out.println(newRow[2]);
        return num;
    }


    private static double getDebito(Connection conn, Object[] newRow)  throws SQLException{

        PreparedStatement stat= conn.prepareStatement("select numero_cialde " +
                                                             "from " + TABLE_NAME_DIPENDENTE +
                                                                " where contanti=false and nome=? and cognome=? and data=?" );

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.setTimestamp(3, (Timestamp) newRow[5]);
        ResultSet rs= stat.executeQuery();
        rs.next();
        return getCurrentDebito(conn, newRow)-(rs.getInt(1)*getPrezzo(conn,newRow));
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
            stat= conn.prepareStatement("insert into "+TABLE_NAME_DEBITO+" VALUES('"+nome+"', '"+cognome+"',"+0+" );");
            stat.executeUpdate();
        }

    }

    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }


    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

        insertIfNotExist(conn,(String) newRow[0],(String) newRow[1]);
        double debito= getDebito(conn, newRow);
        //per test
        System.out.println("nwwwwww: "+newRow[2]);
        PreparedStatement stat= conn.prepareStatement("update "+TABLE_NAME_DEBITO+" set importo= "
                                                            +debito+" where nome=? and cognome=? ");

        stat.setNString(1, (String) newRow[0]);
        stat.setNString(2, (String) newRow[1]);
        stat.executeUpdate();


        ResultSet rs;
        PreparedStatement prep;
        prep=conn.prepareStatement("select *" +
                "from LATAZZASCHEMA.DEBITO " );
        rs=prep.executeQuery();
        while(rs.next())
            System.out.println("1 : \n"+rs.getString(1) + ", " + rs.getString(2)+" : "+ rs.getDouble(3) );





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
