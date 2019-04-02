package backend.database.config;

import java.sql.Connection;

public class TriggerMagazzino {

    protected static Connection connection;
    protected static final String TABLE_NAME_MAGAZZINO="LATAZZASCHEMA.MAGAZZINO";

    public TriggerMagazzino(){}

    public static void initView(Connection conn)  {
        TriggerViewMagazzinoModifyCialde.initTrigger(conn);
        MaterializedViewMagazzino.initView(conn);

    }

}
