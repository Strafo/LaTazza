package backend.database.config;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;

public class Trigger1 implements Trigger {


    @Override
    public void init(Connection connection, String s, String s1, String s2, boolean b, int i) throws SQLException {

    }

    @Override
    public void fire(Connection connection, Object[] objects, Object[] objects1) throws SQLException {

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void remove() throws SQLException {

    }
}
