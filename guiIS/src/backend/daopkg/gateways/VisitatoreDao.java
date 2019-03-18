package backend.daopkg.gateways;
import backend.clientpkg.Visitatore;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
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


    private static ThrowingFunction<Connection,List<Visitatore>> getAllLambda=(Connection conn)->{
        List<Visitatore> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =conn.createStatement();
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
    };

    private static ThrowingBiPredicate<Connection,Visitatore> updateLambda=(Connection conn,Visitatore pers)->{
        return false;//TODO TOBE IMPLEMTED
    };



    private static ThrowingBiPredicate<Connection,Visitatore>  saveLambda=(Connection conn,Visitatore pers)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, pers.getNome());
        pst.setString(2, pers.getCognome());
        pst.executeUpdate();
        return true;
    };



    private static ThrowingBiPredicate<Connection,Visitatore>  deleteLambda=(Connection conn,Visitatore pers)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, pers.getNome());
        pst.setString(2, pers.getCognome());
        pst.executeUpdate();
        return true;
    };


    @Override
    public ThrowingFunction<Connection, List<Visitatore>> getLambdaGetAll()  {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, Visitatore> getLambdaUpdate()  {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, Visitatore> getLambdaSave()  {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, Visitatore> getLambdaDelete()  {
        return deleteLambda;
    }
}
