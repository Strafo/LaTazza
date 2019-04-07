package backend.dataAccessLayer.gatewaysPkg.receiverPkg;
import utils.Euro;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MovimentoDebitoDaoReceiver extends AbstractDaoReceiver<MovimentoDebito> {

    public static final String TABLE_NAME="LATAZZASCHEMA.pagamento_debito";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome,data,euro,centesimi) VALUES (?,?,?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? ,data = ? ,euro = ?, centesimi = ? WHERE nome = ? AND cognome = ? AND data = ? ";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ? AND data = ?";

    public MovimentoDebitoDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }

    @Override
    public List<MovimentoDebito> getAll() throws SQLException {
        List<MovimentoDebito> lista = new LinkedList<>();
        ResultSet rs;
        Statement st = dataBaseConnection.createStatement();
        rs = st.executeQuery(GET_ALL_STRING);
        while (rs.next()) {
            lista.add(
                    new MovimentoDebito(
                            rs.getTimestamp("data"),
                            new Personale(rs.getString("nome"), rs.getString("cognome")),
                            new Euro(rs.getLong("euro"),rs.getInt("centesimi"))
                    )
            );
        }

        return lista;    }

    @Override
    public boolean save(MovimentoDebito movimentoDebito) throws SQLException {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, movimentoDebito.getCliente().getNome());
        pst.setString(2, movimentoDebito.getCliente().getCognome());
        pst.setTimestamp(3,movimentoDebito.getData());
        pst.setLong(4,movimentoDebito.getImporto().getEuro());
        pst.setInt(5,movimentoDebito.getImporto().getCentesimi());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean update(MovimentoDebito movimentoDebito) throws SQLException {
        PreparedStatement pst;
        MovimentoDebito oldEntry=(MovimentoDebito) movimentoDebito.getMemento().getMementoState();//old movimentoDebito
        //new movimentoDebito
        pst=dataBaseConnection.prepareStatement(UPDATE_STATEMENT_STRING);
        pst.setString(1,movimentoDebito.getCliente().getNome());
        pst.setString(2,movimentoDebito.getCliente().getCognome());
        pst.setTimestamp(3,movimentoDebito.getData());
        pst.setLong(4,movimentoDebito.getImporto().getEuro());
        pst.setInt(5,movimentoDebito.getImporto().getCentesimi());

        //old movimentoDebito
        pst.setString(6,oldEntry.getCliente().getNome());
        pst.setString(7,oldEntry.getCliente().getCognome());
        pst.setTimestamp(8,oldEntry.getData());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(MovimentoDebito movimentoDebito) throws SQLException {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, movimentoDebito.getCliente().getNome());
        pst.setString(2,movimentoDebito.getCliente().getCognome());
        pst.setTimestamp(3,movimentoDebito.getData());
        pst.executeUpdate();
        return true;
    }



}
