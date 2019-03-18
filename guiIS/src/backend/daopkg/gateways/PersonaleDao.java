package backend.daopkg.gateways;
import backend.clientpkg.Personale;
import backend.daopkg.gateways.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PersonaleDao extends AbstractDao<Personale> {
    private static final String TABLE_NAME="LATAZZASCHEMA.personale";
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome,attivo) VALUES (?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? , attivo = ? WHERE nome = ? AND cognome = ? AND attivo = ?";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ?";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;

    public PersonaleDao(Connection dataBaseConnection){
        super(TABLE_NAME);
        this.setDataBaseConnection(dataBaseConnection);
    }



    @Override
    public List<Personale> getAll() {
        List<Personale> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =getDataBaseConnection().createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new Personale(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getBoolean("attivo")
                    )
            );
        }
        return lista;
    }

    @Override
    public boolean save(Personale personale) {

        PreparedStatement pst;
        pst=getDataBaseConnection().prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, personale.getNome());
        pst.setString(2, personale.getCognome());
        pst.setBoolean(3,personale.isAttivo());
        pst.executeUpdate();

    }

    @Override
    public boolean update(Personale personale) {

    }

    @Override
    public boolean delete(Personale personale) {
        PreparedStatement pst;
        pst=getDataBaseConnection().prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, personale.getNome());
        pst.setString(2, personale.getCognome());
        pst.executeUpdate();
    }

}
