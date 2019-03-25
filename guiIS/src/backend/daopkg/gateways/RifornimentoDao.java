package backend.daopkg.gateways;
import backend.daopkg.rowdatapkg.RifornimentoEntry;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class RifornimentoDao extends AbstractDao<RifornimentoEntry> {
    public static final String TABLE_NAME="LATAZZASCHEMA.rifornimento";
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (tipo_cialda,dataR,qta) VALUES (?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET tipo_cialda = ? , dataR = ? , qta = ? WHERE tipo_cialda = ? AND dataR = ? AND qta = ?";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE dataR = ? AND tipo_cialda = ?";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;


    public RifornimentoDao(Connection dataBaseConnection){
        super(dataBaseConnection);
    }





    private static ThrowingFunction<Connection,List<RifornimentoEntry>> getAllLambda=(Connection conn)->{
        List<RifornimentoEntry> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =conn.createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new RifornimentoEntry(
                            rs.getTimestamp("dataR"),
                            rs.getInt("qta"),
                            rs.getString("tipo_cialda")
                    )
            );
        }
        return lista;
    };

    private static ThrowingBiPredicate<Connection,RifornimentoEntry> updateLambda=(Connection conn,RifornimentoEntry entry)->{
        return false;//TODO TOBE IMPLEMTED
    };



    private static ThrowingBiPredicate<Connection,RifornimentoEntry>  saveLambda=(Connection conn,RifornimentoEntry entry)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, entry.getTipoCialda());
        pst.setTimestamp(2,entry.getData());
        pst.setInt(3,entry.getQta());
        pst.executeUpdate();
        return true;
    };



    private static ThrowingBiPredicate<Connection,RifornimentoEntry>  deleteLambda=(Connection conn,RifornimentoEntry entry)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setTimestamp(1,entry.getData());
        pst.setString(2, entry.getTipoCialda());
        pst.executeUpdate();
        return true;
    };

    @Override
    public ThrowingFunction<Connection, List<RifornimentoEntry>> getLambdaGetAll()  {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, RifornimentoEntry> getLambdaUpdate()  {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, RifornimentoEntry> getLambdaSave()  {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, RifornimentoEntry> getLambdaDelete()  {
        return deleteLambda;
    }

}
