package dataAccessLayer.database.config;

import java.sql.Connection;

public class ViewMagazzino {

    protected static Connection connection;
    protected static final String TABLE_NAME_MAGAZZINO="LATAZZASCHEMA.MAGAZZINO";

    public ViewMagazzino(){}

    public static void initView(Connection conn)  {
        TriggerMagazzinoModifyCialde.initTrigger(conn);
        TriggerMagazzinoUpdate.initView(conn);

    }

}
