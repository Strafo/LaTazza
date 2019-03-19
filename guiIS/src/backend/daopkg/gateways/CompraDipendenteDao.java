package backend.daopkg.gateways;
import backend.daopkg.rowdatapkg.CompraDipendenteEntry;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CompraDipendenteDao extends AbstractDao {
    public static final String TABLE_NAME="LATAZZASCHEMA.compra_dipendente";
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome,tipo_cialda,numero_cialde,data,contanti) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? , tipo_cialda = ? , numero_cialde = ? , data = ? , contanti = ? WHERE nome = ? AND cognome = ? AND tipo_cialda = ? AND numero_cialde = ? AND data = ? AND contanti  = ?";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ? AND data = ?";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;


    public CompraDipendenteDao(Connection dataBaseConnection) {
        super(TABLE_NAME);
        this.setDataBaseConnection(dataBaseConnection);
    }


    private static ThrowingFunction<Connection,List<CompraDipendenteEntry>> getAllLambda=(Connection conn)->{
        List<CompraDipendenteEntry> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =conn.createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new CompraDipendenteEntry(
                            rs.getDate("data"),
                            rs.getInt("numero_cialde"),
                            rs.getString("tipo_cialda"),
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getBoolean("contanti")
                    )
            );
        }
        return lista;
    };

    private static ThrowingBiPredicate<Connection,CompraDipendenteEntry> updateLambda=(Connection conn,CompraDipendenteEntry entry)->{
        return false;//TODO TOBE IMPLEMTED
    };



    private static ThrowingBiPredicate<Connection,CompraDipendenteEntry>  saveLambda=(Connection conn,CompraDipendenteEntry entry)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1,entry.getCognome());
        pst.setString(2,entry.getNome());
        pst.setString(3,entry.getTipoCialda());
        pst.setInt(4,entry.getNumeroCialde());
        pst.setDate(5,new java.sql.Date(entry.getData().getTime()));
        pst.setBoolean(6,entry.isContanti());
        pst.executeUpdate();
        return true;
    };

    private static ThrowingBiPredicate<Connection,CompraDipendenteEntry>  deleteLambda=(Connection conn,CompraDipendenteEntry entry)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1,entry.getCognome());
        pst.setString(2,entry.getNome());
        pst.setDate(3,new java.sql.Date(entry.getData().getTime()));
        pst.executeUpdate();
        return true;
    };


    @Override
    public ThrowingFunction<Connection, List<CompraDipendenteEntry>> getLambdaGetAll() {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection,CompraDipendenteEntry> getLambdaUpdate() {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection,CompraDipendenteEntry> getLambdaSave() {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection,CompraDipendenteEntry> getLambdaDelete() {
        return deleteLambda;
    }
}