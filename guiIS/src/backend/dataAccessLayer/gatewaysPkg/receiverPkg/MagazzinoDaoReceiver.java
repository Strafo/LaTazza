package backend.dataAccessLayer.gatewaysPkg.receiverPkg;

import backend.businessLogicLayer.Magazzino;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;

import java.sql.Connection;
import java.util.List;

public class MagazzinoDaoReceiver extends AbstractDaoReceiver<Personale>{

    public MagazzinoDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }



    @Override
    public List<Personale> getAll() throws Exception {
        return null;
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
