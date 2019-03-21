package backend.daopkg.gateways;
import backend.daopkg.rowdatapkg.CompraVisitatoreEntry;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CompraVisitatoreDao extends AbstractDao<CompraVisitatoreEntry> {

    public static final String TABLE_NAME = "LATAZZASCHEMA.compra_visitatore";
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome,tipo_cialda,numero_cialde,data) VALUES (?,?,?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? , tipo_cialda = ? , numero_cialde = ? , data = ? WHERE nome = ? AND cognome = ? AND tipo_cialda = ? AND numero_cialde = ? AND data = ?";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ? AND data = ?";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;


    public CompraVisitatoreDao(Connection dataBaseConnection) {
        super(dataBaseConnection);
    }


    private static ThrowingFunction<Connection,List<CompraVisitatoreEntry>> getAllLambda=(Connection conn)->{
        List<CompraVisitatoreEntry> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =conn.createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new CompraVisitatoreEntry(
                            rs.getDate("data"),
                            rs.getInt("numero_cialde"),
                            rs.getString("tipo_cialda"),
                            rs.getString("nome"),
                            rs.getString("cognome")
                    )
            );
        }
        return lista;
    };

    private static ThrowingBiPredicate<Connection,CompraVisitatoreEntry> updateLambda=(Connection conn,CompraVisitatoreEntry entry)->{
        return false;//TODO TOBE IMPLEMTED
    };



    private static ThrowingBiPredicate<Connection,CompraVisitatoreEntry>  saveLambda=(Connection conn,CompraVisitatoreEntry entry)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1,entry.getNome());
        pst.setString(2,entry.getCognome());
        pst.setString(3,entry.getTipoCialda());
        pst.setInt(4,entry.getNumeroCialde());
        pst.setDate(5,new java.sql.Date(entry.getData().getTime()));
        pst.executeUpdate();
        return true;
    };

    private static ThrowingBiPredicate<Connection,CompraVisitatoreEntry>  deleteLambda=(Connection conn,CompraVisitatoreEntry entry)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1,entry.getNome());
        pst.setString(2,entry.getCognome());
        pst.setDate(3,new java.sql.Date(entry.getData().getTime()));
        pst.executeUpdate();
        return true;
    };

    @Override
    public ThrowingFunction<Connection, List<CompraVisitatoreEntry>> getLambdaGetAll()  {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, CompraVisitatoreEntry> getLambdaUpdate()  {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, CompraVisitatoreEntry> getLambdaSave()  {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, CompraVisitatoreEntry> getLambdaDelete()  {
        return deleteLambda;
    }

}