package backend.dataAccessLayer.gatewaysPkg.receiverPkg;

import backend.businessLogicLayer.Magazzino;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.MagazzinoEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MagazzinoDaoReceiver extends AbstractDaoReceiver<MagazzinoEntry>{
    public static final String TABLE_NAME="LATAZZASCHEMA.MAGAZZINO";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;

    public MagazzinoDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }



    @Override
    public List<MagazzinoEntry> getAll() throws Exception {
        List<MagazzinoEntry> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =dataBaseConnection.createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add( new MagazzinoEntry(
                    rs.getString("tipo"),
                    rs.getInt("qta")
                    )

            );
        }
        return lista;
    }

    @Override
    public boolean save(MagazzinoEntry entry) throws Exception {
        throw  new UnsupportedOperationException();
    }

    @Override
    public boolean update(MagazzinoEntry entry) throws Exception {
        throw  new UnsupportedOperationException();
    }

    @Override
    public boolean delete(MagazzinoEntry entry) throws Exception {
        throw  new UnsupportedOperationException();
    }
}
