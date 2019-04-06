package backend.dataAccessLayer.gatewaysPkg.receiverPkg;
import utils.Euro;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CialdeDaoReceiver extends AbstractDaoReceiver<CialdeEntry> {

    public static final String TABLE_NAME="LATAZZASCHEMA.cialde";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (tipo,prezzo) VALUES (?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET tipo = ? , prezzo = ? WHERE tipo = ?  ";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE tipo = ? ";

    public CialdeDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }


    private  ThrowingFunction<Connection,List<CialdeEntry>> getAllLambda=(Connection conn)->{
        List<CialdeEntry> lista = new LinkedList<>();
        ResultSet rs;
        Statement st = conn.createStatement();
        rs = st.executeQuery(GET_ALL_STRING);
        while (rs.next()) {
            lista.add(
                    new CialdeEntry(
                            rs.getString("tipo"),
                            //rs.getDouble("prezzo")//TODO
                            new Euro(0,0)
                    )
            );
        }

        return lista;
    };

    private  ThrowingBiPredicate<Connection,CialdeEntry> updateLambda=(Connection conn,CialdeEntry cialda)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(UPDATE_STATEMENT_STRING);
        //new entry
        pst.setString(1,cialda.getTipo());
        //pst.setTimestamp(2,cialda.getPrezzo());
        pst.setDouble(2,3.3);//todo
        //old entry
        CialdeEntry oldEntry=(CialdeEntry)cialda.getMemento().getMementoState();
        pst.setString(3,oldEntry.getTipo());
        pst.executeUpdate();
        return true;
    };



    private  ThrowingBiPredicate<Connection,CialdeEntry>  saveLambda=(Connection conn,CialdeEntry cialda)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, cialda.getTipo());
        pst.setDouble(2, 3.3);//cialda.getPrezzo());//todo
        pst.executeUpdate();
        return true;
    };



    private  ThrowingBiPredicate<Connection,CialdeEntry>  deleteLambda=(Connection conn,CialdeEntry cialda)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, cialda.getTipo());
        pst.executeUpdate();
        return true;
    };


}
