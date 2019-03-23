package backend.database.config;
import org.h2.api.Trigger;

import java.sql.*;

public class Trigger1 implements Trigger {

    private Connection conn;
    private Statement stat;
    private String triggerName, table,schema;
    private boolean before;
    private int type; //1: Insert, 2: Update, 4: Delete, 8: select
    private ResultSet cV,cP,magazzino;

    @Override
    public void init(Connection connection, String s, String t1, String t2, boolean b, int i) throws SQLException {

        conn=connection; schema=s; triggerName =t1; table =t2; before=b; type=i;
        cV=stat.executeQuery("select sum(numero_cialde) "+
                " from LATAZZASCHEMA.COMPRA_VISITATORE T "+
                " where tipo_cialda = (select tipo_cialda from LATAZZASCHEMA.COMPRA_VISITATORE where T.TIPO_CIALDA=COMPRA_VISITATORE.TIPO_CIALDA); ");
        cP=stat.executeQuery("select sum(numero_cialde)\n"+
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n"+
                " where tipo_cialda = (select tipo_cialda from LATAZZASCHEMA.COMPRA_DIPENDENTE T where T.TIPO_CIALDA=COMPRA_DIPENDENTE.TIPO_CIALDA);");

        magazzino=stat.executeQuery("select sum(QTA*50)\n"+
                " from LATAZZASCHEMA.MAGAZZINO\n"+
                " where tipoCialda=(select TIPOCIALDA from LATAZZASCHEMA.MAGAZZINO T where T.TIPOCIALDA=MAGAZZINO.TIPOCIALDA);");

    }

    public Trigger1(Connection conn) throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stat=conn.createStatement();
        init(conn,"LATAZZASCHEMA","CHECKNUMCIALDE","LATAZZASCHEMA.COMPRA_VISITATORE",true,1);

    }

    @Override
    public void fire(Connection connection, Object[] oldRow, Object[] newRow) throws SQLException {
        cV.next(); cP.next(); magazzino.next();
        if((cV.getInt((Integer) newRow[0])+ cP.getInt((Integer) newRow[0])) > magazzino.getInt((Integer) newRow[0]))
            throw new SQLException();
    }

    @Override
    public void close() throws SQLException {
        cV.close();
        cP.close();
        magazzino.close();

    }

    @Override
    public void remove() throws SQLException {

    }

    public Connection getConn() {
        return conn;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public String getTable() {
        return table;
    }

    public String getSchema() {
        return schema;
    }

    public int getType() {
        return type;
    }
    public  Statement getStat(){return stat;}
}
