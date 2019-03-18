package backend.daopkg.gateways;
import backend.clientpkg.Visitatore;
import backend.daopkg.gateways.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class VisitatoreDao extends AbstractDao<Visitatore> {
    private static final String TABLE_NAME="LATAZZASCHEMA.visitatore";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome) VALUES (?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? WHERE nome = ? AND cognome = ? ";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ?";

    public VisitatoreDao(Connection dataBaseConnection){
        super(TABLE_NAME);
        this.setDataBaseConnection(dataBaseConnection);
    }


    @Override
    public List<Visitatore> getAll() {
        List<Visitatore> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =getDataBaseConnection().createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new Visitatore(
                            rs.getString("nome"),
                            rs.getString("cognome")
                    )
            );
        }
        return lista;
    }

    @Override
    public boolean save(Visitatore visitatore) {

        PreparedStatement pst;
        pst=getDataBaseConnection().prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, visitatore.getNome());
        pst.setString(2, visitatore.getCognome());
        pst.executeUpdate();

    }

    @Override
    public boolean update(Visitatore visitatore) {

    }

    @Override
    public boolean delete(Visitatore visitatore) {
        PreparedStatement pst;
        pst=getDataBaseConnection().prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, visitatore.getNome());
        pst.setString(2, visitatore.getCognome());
        pst.executeUpdate();

    }


}
