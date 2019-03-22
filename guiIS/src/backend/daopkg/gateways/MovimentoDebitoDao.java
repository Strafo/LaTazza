package backend.daopkg.gateways;
import backend.Euro;
import backend.clientpkg.Personale;
import backend.movimentopkg.MovimentoDebito;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MovimentoDebitoDao extends AbstractDao<MovimentoDebito> {

    public static final String TABLE_NAME="LATAZZASCHEMA.pagamento_debito";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome,data,importo) VALUES (?,?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? ,data = ? ,importo = ? WHERE nome = ? AND cognome = ? AND data = ? ";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ? AND data = ?";

    public MovimentoDebitoDao(Connection dataBaseConnection){
        super(dataBaseConnection);
    }



    private static ThrowingFunction<Connection,List<MovimentoDebito>> getAllLambda=(Connection conn)->{
        List<MovimentoDebito> lista = new LinkedList<>();
        ResultSet rs;
        Statement st = conn.createStatement();
        rs = st.executeQuery(GET_ALL_STRING);
        while (rs.next()) {
            lista.add(
                    new MovimentoDebito(
                            rs.getDate("data"),
                            new Personale(rs.getString("nome"), rs.getString("cognome")),
                            //rs.getDouble("importo")//todo non va bene i double per importo
                            new Euro(1,0)
                    )
            );
        }

        return lista;
    };

    private static ThrowingBiPredicate<Connection,MovimentoDebito> updateLambda=(Connection conn,MovimentoDebito pde)->{
        return false;//TODO TOBE IMPLEMTED
    };



    private static ThrowingBiPredicate<Connection,MovimentoDebito>  saveLambda=(Connection conn,MovimentoDebito pde)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, pde.getCliente().getNome());
        pst.setString(2, pde.getCliente().getCognome());
        pst.setDate(3,new java.sql.Date(pde.getData().getTime()));
        pst.setDouble(4,1.0);//todo set importo
        pst.executeUpdate();
        return true;
    };



    private static ThrowingBiPredicate<Connection,MovimentoDebito>  deleteLambda=(Connection conn,MovimentoDebito pde)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, pde.getCliente().getNome());
        pst.setString(2,pde.getCliente().getCognome());
        pst.setDate(3,new java.sql.Date(pde.getData().getTime()));
        pst.executeUpdate();
        return true;
    };

    @Override
    public ThrowingFunction<Connection, List<MovimentoDebito>> getLambdaGetAll()  {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, MovimentoDebito> getLambdaUpdate()  {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, MovimentoDebito> getLambdaSave()  {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, MovimentoDebito> getLambdaDelete()  {
        return deleteLambda;
    }

}
