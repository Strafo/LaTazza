package backend.database.config;

import org.h2.api.Trigger;

import java.sql.*;

public class MaterializedViewMagazzino implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.MaterializedViewMagazzino\"";

    private static final String TABLE_NAME_VISITATORE="LATAZZASCHEMA.COMPRA_VISITATORE";
    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TABLE_NAME_RIFORNIMENTO="LATAZZASCHEMA.RIFORNIMENTO";
    private static final String TRIGGER_NAME_DIPENDETE="Update_View_Magazzino_dipendente";
    private static final String TRIGGER_NAME_VISITATORE="Update_View_Magazzino_visitatore";
    private static final String TRIGGER_NAME_RIFORNIMENTO="Update_View_Magazzino_rifornimento";
    private static final String CREATE_TRIGGER_STATEMENT_DIPENDENTE = "CREATE TRIGGER " + TRIGGER_NAME_DIPENDETE + " AFTER INSERT ON "+ TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_VISITATORE = "CREATE TRIGGER " + TRIGGER_NAME_VISITATORE + " AFTER INSERT ON "+ TABLE_NAME_VISITATORE+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_RIFORNIMENTO = "CREATE TRIGGER " + TRIGGER_NAME_RIFORNIMENTO + " AFTER INSERT ON "+ TABLE_NAME_RIFORNIMENTO+" FOR EACH ROW CALL "+TRIGGER_PATH;






    private static int getNumCialde(Connection conn, String table, Object[] newRow)  throws SQLException{

        PreparedStatement stat;
        ResultSet resultSet;
        int numCialde;
        stat =conn.prepareStatement("select sum(numero_cialde) " +
                "from "+ table +
                " where tipo_cialda=? " );
        stat.setNString(1, (String) newRow[2]);
        resultSet=stat.executeQuery();
        resultSet.next();
        numCialde=resultSet.getInt(1);
        return numCialde;
    }

    private static int statoCialdaMagazzino(Connection conn, Object[] newRow)throws SQLException {

        int rifornimenti, venditaD, venditaV;
        rifornimenti=getNumCialde(conn,TABLE_NAME_RIFORNIMENTO,newRow);
        venditaD=getNumCialde(conn,TABLE_NAME_DIPENDENTE, newRow);
        venditaV=getNumCialde(conn,TABLE_NAME_VISITATORE, newRow);
        return rifornimenti - (venditaD + venditaV);
    }

        @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {


        int numCialde =statoCialdaMagazzino(conn, newRow);
        PreparedStatement prepUpdate = conn.prepareStatement("UPDATE  LATAZZASCHEMA.MAGAZZINO " +
                                                                "SET "+"qta= "+ numCialde +" where tipo=? ");
        prepUpdate.setNString(1, (String) newRow[2]);
        prepUpdate.executeUpdate();

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
            stat.execute(CREATE_TRIGGER_STATEMENT_DIPENDENTE);
            stat.execute(CREATE_TRIGGER_STATEMENT_RIFORNIMENTO);
            stat.execute(CREATE_TRIGGER_STATEMENT_VISITATORE);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
