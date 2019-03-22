package database.config;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        cV=stat.executeQuery("select sum(numero_cialde)\n"+
                                " from LATAZZASCHEMA.COMPRA_VISITATORE\n"+
                                " where tipo_cialda = new.tipo_cialda;");
        cP=stat.executeQuery("select sum(numero_cialde)\n"+
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n"+
                " where tipo_cialda = new.tipo_cialda;");
        magazzino=stat.executeQuery("select sum(QTA*50)\n"+
                " from LATAZZASCHEMA.MAGAZZINO\n"+
                " where tipoCialda=new.TipoCialda;");


    }

    public Trigger1(String URL){

    }

    @Override
    public void fire(Connection connection, Object[] oldRow, Object[] newRow) throws SQLException {

        if(newRow != null)
            if()
        cV=stat.executeQuery("select sum(numero_cialde)\n"+
                " from LATAZZASCHEMA.COMPRA_VISITATORE\n"+
                " where tipo_cialda = new.tipo_cialda;");
        cP=stat.executeQuery("select sum(numero_cialde)\n"+
                " from LATAZZASCHEMA.COMPRA_DIPENDENTE\n"+
                " where tipo_cialda = new.tipo_cialda;");
        magazzino=stat.executeQuery("select sum(QTA*50)\n"+
                " from LATAZZASCHEMA.MAGAZZINO\n"+
                " where tipoCialda=new.TipoCialda;");

    }

    @Override
    public void close() throws SQLException {

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
}
