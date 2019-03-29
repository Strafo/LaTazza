package backend.database.config;

import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MaterializedViewMagazzino implements Trigger {

    private static final String TRIGGER_PATH="\"backend.database.config.TriggerCheckNumCialdeDipendente\"";

    private static final String TABLE_NAME_VISITATORE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TABLE_NAME_DIPENDENTE="LATAZZASCHEMA.COMPRA_DIPENDENTE";
    private static final String TABLE_NAME_RIFORNIMENTO="LATAZZASCHEMA.RIFORNIMENTO";
    private static final String TRIGGER_NAME_DIPENDETE="Update_View_Mgazzino_dipendente";
    private static final String TRIGGER_NAME_VISITATORE="Update_View_Mgazzino_visitatore";
    private static final String TRIGGER_NAME_RIFORNIMENTO="Update_View_Mgazzino_rifornimento";
    private static final String CREATE_TRIGGER_STATEMENT_DIPENDENTE = "CREATE TRIGGER " + TRIGGER_NAME_DIPENDETE + " AFTER INSERT ON "+ TABLE_NAME_DIPENDENTE+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_VISITATORE = "CREATE TRIGGER " + TRIGGER_NAME_VISITATORE + " AFTER INSERT ON "+ TABLE_NAME_VISITATORE+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_RIFORNIMENTO = "CREATE TRIGGER " + TRIGGER_NAME_RIFORNIMENTO + " AFTER INSERT ON "+ TABLE_NAME_RIFORNIMENTO+" FOR EACH ROW CALL "+TRIGGER_PATH;
    private static final String QUERY_VIEW= "select sum(qta)\n" +
                                            "from  LATAZZASCHEMA.RIFORNIMENTO\n" +
                                            "where tipo_cialda=?"+
                                            "except\n"+
                                            "select tipo_cialda, sum(numero_cialde)\n" +
                                            "from LATAZZASCHEMA.COMPRA_VISITATORE\n" +
                                            "where tipo_cialda=?"+
                                            "except\n" +
                                            "select tipo_cialda, sum(numero_cialde)\n" +
                                            "from LATAZZASCHEMA.COMPRA_DIPENDENTE\n" +
                                            "where tipo_cialda=?";

    private static final String VIEW="create view  Magazzino(tipo_cialda, qta) as select tipo,0 from LATAZZASCHEMA.Cialde\n";





    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

    PreparedStatement prepUpdate = conn.prepareStatement("UPDATE LATAZZASCHEMA.MAGAZZINO " +
                                                                "SET "+"qta= "+"( "+QUERY_VIEW+")"+"where tipo_Cialda=newRow[0]");
    prepUpdate.setNString(1, (String) newRow[0]);
    prepUpdate.setNString(2, (String) newRow[0]);
    prepUpdate.setNString(3, (String) newRow[0]);
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
            stat.execute(VIEW);
            stat.execute(CREATE_TRIGGER_STATEMENT_DIPENDENTE);
            stat.execute(CREATE_TRIGGER_STATEMENT_RIFORNIMENTO);
            stat.execute(CREATE_TRIGGER_STATEMENT_VISITATORE);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
