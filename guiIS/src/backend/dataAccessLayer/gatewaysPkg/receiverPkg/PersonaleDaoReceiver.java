package backend.dataAccessLayer.gatewaysPkg.receiverPkg;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import utils.Euro;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonaleDaoReceiver extends AbstractDaoReceiver<Personale> {
    public static final String TABLE_NAME_DEBITO="LATAZZASCHEMA.DEBITO";
    public static final String TABLE_NAME_PERSONALE="LATAZZASCHEMA.personale";
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME_PERSONALE + " (nome,cognome,attivo) VALUES (?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME_PERSONALE + " SET nome = ? , cognome = ? , attivo = ? WHERE nome = ? AND cognome = ? AND attivo = ?";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME_PERSONALE + " WHERE nome = ? AND cognome = ?";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME_DEBITO;

    public PersonaleDaoReceiver(Connection dataBaseConnection){
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
        return lista;    }

    @Override
    public boolean save(Personale personale) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, personale.getNome());
        pst.setString(2, personale.getCognome());
        pst.setBoolean(3,personale.isAttivo());
        pst.executeUpdate();
        return true;    }

    @Override
    public boolean update(Personale personale) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(UPDATE_STATEMENT_STRING);
        //new entry
        pst.setString(1,personale.getNome());
        pst.setString(2,personale.getCognome());
        pst.setBoolean(3,personale.isAttivo());
        //old entry
        Personale old= (Personale) personale.getMemento().getMementoState();
        pst.setString(4,old.getNome());
        pst.setString(5,old.getCognome());
        pst.setBoolean(6,old.isAttivo());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(Personale personale) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, personale.getNome());
        pst.setString(2, personale.getCognome());
        pst.executeUpdate();
        return true;
    }



}
