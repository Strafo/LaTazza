package backend.dataAccessLayer.gatewaysPkg.receiverPkg;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import utils.Euro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DebitoDaoReceiver extends AbstractDaoReceiver<Personale> {
    public static final String TABLE_NAME="LATAZZASCHEMA.DEBITO";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;

    public DebitoDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }

    @Override
    public List<Personale> getAll() throws Exception {
        List<Personale> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =dataBaseConnection.createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new Personale(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getBoolean("attivo"),
                            new Euro(rs.getLong("euro"), rs.getInt("centesimi"))
                    )
            );
        }
        return lista;

    }

    @Override
    public boolean save(Personale personale) throws Exception {
        throw  new UnsupportedOperationException();
    }

    @Override
    public boolean update(Personale personale) throws Exception {
        throw  new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Personale personale) throws Exception {
        throw  new UnsupportedOperationException();
    }
}
