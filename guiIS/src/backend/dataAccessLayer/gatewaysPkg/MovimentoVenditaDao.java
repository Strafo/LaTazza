package backend.dataAccessLayer.gatewaysPkg;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoVendita;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MovimentoVenditaDao extends AbstractDao {

    public static final String TABLE_NAME_PERSONALE="LATAZZASCHEMA.compra_dipendente";
    public static final String TABLE_NAME_VISITATORE="LATAZZASCHEMA.compra_visitatore";

    /**QUERY PER PERSONALE**/
    private static final String INSERT_STATEMENT_STRING_PERSONALE = "INSERT INTO " + TABLE_NAME_PERSONALE + " (nome,cognome,tipo_cialda,numero_cialde,data,contanti) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_STATEMENT_STRING_PERSONALE = "UPDATE  " + TABLE_NAME_PERSONALE + " SET nome = ? , cognome = ? , tipo_cialda = ? , numero_cialde = ? , data = ? , contanti = ? WHERE nome = ? AND cognome = ? AND tipo_cialda = ? AND numero_cialde = ? AND data = ? AND contanti  = ?";
    private static final String DELETE_STATEMENT_STRING_PERSONALE = "DELETE FROM " + TABLE_NAME_PERSONALE + " WHERE nome = ? AND cognome = ? AND data = ?";
    private static final String GET_ALL_STRING_PERSONALE="SELECT * FROM "+TABLE_NAME_PERSONALE;

    /**QUERY PER VISITATORE**/
    private static final String INSERT_STATEMENT_STRING_VISITATORE = "INSERT INTO " + TABLE_NAME_VISITATORE + " (nome,cognome,tipo_cialda,numero_cialde,data) VALUES (?,?,?,?,?)";
    private static final String UPDATE_STATEMENT_STRING_VISITATORE = "UPDATE  " + TABLE_NAME_VISITATORE + " SET nome = ? , cognome = ? , tipo_cialda = ? , numero_cialde = ? , data = ? WHERE nome = ? AND cognome = ? AND tipo_cialda = ? AND numero_cialde = ? AND data = ?";
    private static final String DELETE_STATEMENT_STRING_VISITATORE = "DELETE FROM " + TABLE_NAME_VISITATORE + " WHERE nome = ? AND cognome = ? AND data = ?";
    private static final String GET_ALL_STRING_VISITATORE="SELECT * FROM "+TABLE_NAME_VISITATORE;


    public MovimentoVenditaDao(Connection dataBaseConnection) {
        super(dataBaseConnection);
    }



    private static ThrowingFunction<Connection,List<MovimentoVendita>> getAllLambda=(Connection conn)->{
        List<MovimentoVendita> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =conn.createStatement();
        rs=st.executeQuery(GET_ALL_STRING_PERSONALE);
        while(rs.next()){
            lista.add(
                    new MovimentoVendita(
                            rs.getTimestamp("data"),
                            new Personale(rs.getString("nome"), rs.getString("cognome")),
                            rs.getInt("numero_cialde"),
                            new CialdeEntry(rs.getString("tipo_cialda")),
                            rs.getBoolean("contanti")
                    )
            );
        }
        rs=st.executeQuery(GET_ALL_STRING_VISITATORE);
        while(rs.next()){
            lista.add(
                    new MovimentoVendita(
                            rs.getTimestamp("data"),
                            new Visitatore(rs.getString("nome"), rs.getString("cognome")),
                            rs.getInt("numero_cialde"),
                            new CialdeEntry(rs.getString("tipo_cialda")),
                            true//todo non è detto
                            )
            );
        }
        return lista;
    };

    private static ThrowingBiPredicate<Connection,MovimentoVendita> updateLambda=(Connection conn,MovimentoVendita entry)->{
        PreparedStatement pst;
        int j=0;
        MovimentoVendita oldEntry=(MovimentoVendita) entry.getMemento().getMementoState();//old entry
        //new entry
        if(entry.getCliente() instanceof Personale){
            pst=conn.prepareStatement(UPDATE_STATEMENT_STRING_PERSONALE);
            pst.setBoolean(6,entry.isContanti());
            pst.setBoolean(12,oldEntry.isContanti());
        }else{
            if(entry.getCliente() instanceof Visitatore){
                pst=conn.prepareStatement(UPDATE_STATEMENT_STRING_VISITATORE);
                j=1;
            }else{
                return false;
            }
        }
        pst.setString(1,entry.getCliente().getNome());
        pst.setString(2,entry.getCliente().getCognome());
        pst.setString(3,entry.getTipo().getTipo());
        pst.setInt(4,entry.getQuantita());
        pst.setTimestamp(5,entry.getData());
        //old entry
        pst.setString(7-j,oldEntry.getCliente().getNome());
        pst.setString(8-j,oldEntry.getCliente().getCognome());
        pst.setString(9-j,oldEntry.getTipo().getTipo());
        pst.setInt(10-j,oldEntry.getQuantita());
        pst.setTimestamp(11-j,oldEntry.getData());
        pst.executeUpdate();

        return true;
    };



    private static ThrowingBiPredicate<Connection,MovimentoVendita>  saveLambda=(Connection conn,MovimentoVendita entry)->{
        PreparedStatement pst;
        if(entry.getCliente() instanceof Personale){
            pst=conn.prepareStatement(INSERT_STATEMENT_STRING_PERSONALE);
            pst.setBoolean(6,entry.isContanti());
        }else{
            if(entry.getCliente() instanceof Visitatore){
                pst=conn.prepareStatement(INSERT_STATEMENT_STRING_VISITATORE);
            }else{
                return false;
            }
        }
        pst.setString(1,entry.getCliente().getNome());
        pst.setString(2,entry.getCliente().getCognome());
        pst.setString(3,entry.getTipo().getTipo());
        pst.setInt(4,entry.getQuantita());
        pst.setTimestamp(5,entry.getData());
        pst.executeUpdate();
        return true;
    };

    private static ThrowingBiPredicate<Connection,MovimentoVendita>  deleteLambda=(Connection conn,MovimentoVendita entry)->{
        PreparedStatement pst;
        if(entry.getCliente() instanceof Personale){
            pst=conn.prepareStatement(DELETE_STATEMENT_STRING_PERSONALE);
        }else{
            if(entry.getCliente() instanceof Visitatore){
                pst=conn.prepareStatement(DELETE_STATEMENT_STRING_VISITATORE);
            }else{
                return false;
            }
        }
        pst.setString(1,entry.getCliente().getNome());
        pst.setString(2,entry.getCliente().getCognome());
        pst.setTimestamp(3,entry.getData());
        pst.executeUpdate();
        return true;
    };

    @Override
    public ThrowingFunction<Connection, List<MovimentoVendita>> getLambdaGetAll()  {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, MovimentoVendita> getLambdaUpdate()  {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, MovimentoVendita> getLambdaSave()  {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, MovimentoVendita> getLambdaDelete()  {
        return deleteLambda;
    }
}