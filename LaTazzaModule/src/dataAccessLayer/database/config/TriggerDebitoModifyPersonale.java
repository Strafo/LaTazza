package dataAccessLayer.database.config;

import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TriggerDebitoModifyPersonale extends ViewDebito implements Trigger {
    protected static final String TRIGGER_PATH="\"dataAccessLayer.database.config.TriggerDebitoModifyPersonale\"";

    private static final String TABLE_NAME_PERSONALE="LATAZZASCHEMA.PERSONALE";
    private static final String TRIGGER_NAME_INSERT_PERSONALE="Insert_View_Debito_personale";
    private static final String TRIGGER_NAME_DELETE_PERSONALE="Delete_View_Debito_personale";
    private static final String TRIGGER_NAME_UPDATE_PERSONALE="Update_View_Debito_personale";
    private static final String CREATE_TRIGGER_STATEMENT_INSERT_PERSONALE = "CREATE TRIGGER " + TRIGGER_NAME_INSERT_PERSONALE + " AFTER INSERT ON "+ TABLE_NAME_PERSONALE +" FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_DELETE_PERSONALE = "CREATE TRIGGER " + TRIGGER_NAME_DELETE_PERSONALE + " AFTER DELETE ON "+ TABLE_NAME_PERSONALE +" FOR EACH ROW CALL " + TRIGGER_PATH;
    private static final String CREATE_TRIGGER_STATEMENT_UPDATE_PERSONALE = "CREATE TRIGGER " + TRIGGER_NAME_UPDATE_PERSONALE + " AFTER UPDATE ON "+ TABLE_NAME_PERSONALE +" FOR EACH ROW CALL " + TRIGGER_PATH;


    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {
    }

    private static void deletePersonale(Connection conn,String nome, String cognome) throws  SQLException{

        stat =conn.prepareStatement("DELETE from "+ TABLE_NAME_DEBITO+" where nome='"+ nome+"' and cognome='"+cognome+"'");
        stat.executeUpdate();
        stat.close();
    }

    private static void insertPersonale(Connection conn,String nome, String cognome, boolean attivo) throws SQLException{

        stat =conn.prepareStatement("insert into "+TABLE_NAME_DEBITO+" VALUES('"+nome+"', '"+cognome+"',0, 0 , "+attivo+" )");
        stat.executeUpdate();
        stat.close();
    }

    private static void updatePersonale(Connection conn,Object[] oldRow, Object[] newRow) throws SQLException{

        stat =conn.prepareStatement("update "+TABLE_NAME_DEBITO+" set nome= '"
                + newRow[nome]+"', cognome='"+ newRow[cognome] +"', attivo="+ newRow[attivo]+"  where nome='"+ oldRow[nome] +"' and cognome='"+oldRow[cognome]+"'");
        stat.executeUpdate();
        stat.close();
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {


        if(oldRow != null)deletePersonale(conn,(String) oldRow[nome],(String) oldRow[cognome]);
        else
        if(newRow != null)insertPersonale(conn,(String) newRow[nome],(String) newRow[cognome],(boolean) newRow[attivo]);
        else
            updatePersonale(conn,oldRow, newRow);

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }

    public static void initTrigger(Connection conn)  {
        Statement stat= null;
        try {
            stat = conn.createStatement();
            stat.execute(CREATE_TRIGGER_STATEMENT_INSERT_PERSONALE);
            stat.execute(CREATE_TRIGGER_STATEMENT_DELETE_PERSONALE);
            stat.execute(CREATE_TRIGGER_STATEMENT_UPDATE_PERSONALE);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
