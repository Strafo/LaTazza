package backend.dataAccessLayer.gatewaysPkg.receiverPkg;

import backend.businessLogicLayer.Cassa;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import utils.Euro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class CassaDaoReceiver extends AbstractDaoReceiver<Cassa> {

    private static final String TABLE_NAME="LATAZZASCHEMA.CASSA";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private ResultSet rs;
    private PreparedStatement stat;

    public CassaDaoReceiver(Connection dataBaseConnection) {
        super(dataBaseConnection);
    }

    @Override
    public List<Cassa> getAll() throws Exception {
        List<Cassa> list= new LinkedList<>();
        stat= dataBaseConnection.prepareStatement(GET_ALL_STRING);
        rs= stat.executeQuery();
        while (rs.next())
            list.add( new Cassa(new Euro(rs.getLong(1),rs.getInt(2))));
        return list;
    }

    @Override
    public boolean save(Cassa cassa) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Cassa cassa) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Cassa cassa) throws Exception {
        throw new UnsupportedOperationException();
    }

}
