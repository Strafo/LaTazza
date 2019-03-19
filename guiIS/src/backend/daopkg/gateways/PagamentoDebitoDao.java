package backend.daopkg.gateways;
import backend.Euro;
import backend.daopkg.rowdatapkg.PagamentoDebitoEntry;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.*;
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



    private static ThrowingFunction<Connection,List<PagamentoDebitoEntry>> getAllLambda=(Connection conn)->{
        List<PagamentoDebitoEntry> lista = new LinkedList<>();
        ResultSet rs;
        Statement st = conn.createStatement();
        rs = st.executeQuery(GET_ALL_STRING);
        while (rs.next()) {
            lista.add(
                    new PagamentoDebitoEntry(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getDate("data"),
                            //rs.getDouble("importo")//todo non va bene i double per importo
                            new Euro(0,0)
                    )
            );
        }

        return lista;
    };

    private static ThrowingBiPredicate<Connection,PagamentoDebitoEntry> updateLambda=(Connection conn,PagamentoDebitoEntry pde)->{
        return false;//TODO TOBE IMPLEMTED
    };



    private static ThrowingBiPredicate<Connection,PagamentoDebitoEntry>  saveLambda=(Connection conn,PagamentoDebitoEntry pde)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, pde.getNomePersonale());
        pst.setString(2, pde.getCognomePersonale());
        pst.setDate(3,new java.sql.Date(pde.getData().getTime()));
        pst.setDouble(4,0.0);//todo set importo
        pst.executeUpdate();
        return true;
    };



    private static ThrowingBiPredicate<Connection,PagamentoDebitoEntry>  deleteLambda=(Connection conn,PagamentoDebitoEntry pde)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, pde.getNomePersonale());
        pst.setString(2,pde.getCognomePersonale());
        pst.setDate(3,new java.sql.Date(pde.getData().getTime()));
        pst.executeUpdate();
        return true;
    };

    @Override
    public ThrowingFunction<Connection, List<PagamentoDebitoEntry>> getLambdaGetAll()  {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection,PagamentoDebitoEntry> getLambdaUpdate() {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection,PagamentoDebitoEntry>  getLambdaSave()  {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection,PagamentoDebitoEntry>  getLambdaDelete()  {
        return deleteLambda;
    }



}
