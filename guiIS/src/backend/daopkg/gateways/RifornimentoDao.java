package backend.daopkg.gateways;
import backend.daopkg.gateways.AbstractDao;

import java.sql.Connection;
import java.util.List;

public class RifornimentoDao extends AbstractDao {
    public static final String TABLE_NAME="LATAZZASCHEMA.rifornimento";

    public RifornimentoDao(Connection dataBaseConnection){
        super(TABLE_NAME);
        this.setDataBaseConnection(dataBaseConnection);
    }


    @Override
    public List getAll() {
        return null;
    }

    @Override
    public boolean save(Object o) {

    }

    @Override
    public boolean update(Object o) {

    }

    @Override
    public boolean delete(Object o) {

    }
}
