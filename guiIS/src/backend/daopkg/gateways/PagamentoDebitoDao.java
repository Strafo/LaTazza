package backend.daopkg.gateways;
import backend.daopkg.PagamentoDebitoEntry;
import backend.daopkg.gateways.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PagamentoDebitoDao extends AbstractDao<PagamentoDebitoEntry> {
    private static final String TABLE_NAME="LATAZZASCHEMA.pagamento_debito";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome,data,importo) VALUES (?,?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? ,data = ? ,importo = ? WHERE nome = ? AND cognome = ? AND data = ? ";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ? AND data = ?";

    public PagamentoDebitoDao(Connection dataBaseConnection){
        super(TABLE_NAME);
        this.setDataBaseConnection(dataBaseConnection);
    }


    @Override
    public List< PagamentoDebitoEntry> getAll() {
        List<PagamentoDebitoEntry> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =getDataBaseConnection().createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new PagamentoDebitoEntry(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getDate("data"),
                            rs.getDouble("importo")//todo non va bene i double per importo
                    )
            );
        }
        return lista;
    }

    @Override
    public boolean save(PagamentoDebitoEntry pagamentoDebitoEntry) {
        PreparedStatement pst;
        pst=getDataBaseConnection().prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, pagamentoDebitoEntry.getNomePersonale());
        pst.setString(2, pagamentoDebitoEntry.getCognomePersonale());
        pst.setDate(3,new java.sql.Date(pagamentoDebitoEntry.getData().getTime()));
        //todo set importo
        pst.executeUpdate();
    }

    @Override
    public boolean update(PagamentoDebitoEntry pagamentoDebitoEntry) {

    }

    @Override
    public boolean delete(PagamentoDebitoEntry pagamentoDebitoEntry) {
        PreparedStatement pst;
        pst=getDataBaseConnection().prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, pagamentoDebitoEntry.getNomePersonale());
        pst.setString(2,pagamentoDebitoEntry.getCognomePersonale());
        pst.setDate(3,new java.sql.Date(pagamentoDebitoEntry.getData().getTime()));
        pst.executeUpdate();

    }



}
