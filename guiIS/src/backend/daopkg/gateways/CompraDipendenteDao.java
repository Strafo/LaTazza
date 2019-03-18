package backend.daopkg.gateways;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;

import java.sql.Connection;
import java.util.List;

public class CompraDipendenteDao extends AbstractDao {
    public static final String TABLE_NAME="LATAZZASCHEMA.compra_dipendente";

    public CompraDipendenteDao(Connection dataBaseConnection){
        super(TABLE_NAME);
        this.setDataBaseConnection(dataBaseConnection);
    }


    @Override
    public ThrowingFunction<Connection, List> getLambdaGetAll() {
        return null;
    }

    @Override
    public ThrowingBiPredicate getLambdaUpdate() {
        return null;
    }

    @Override
    public ThrowingBiPredicate getLambdaSave() {
        return null;
    }

    @Override
    public ThrowingBiPredicate getLambdaDelete() {
        return null;
    }
}
