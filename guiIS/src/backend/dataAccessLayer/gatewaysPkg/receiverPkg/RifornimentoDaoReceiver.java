package backend.dataAccessLayer.gatewaysPkg.receiverPkg;
import backend.dataAccessLayer.rowdatapkg.RifornimentoEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class RifornimentoDaoReceiver extends AbstractDaoReceiver<RifornimentoEntry> {

    public static final String TABLE_NAME="LATAZZASCHEMA.rifornimento";
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (tipo_cialda,dataR,qta) VALUES (?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET tipo_cialda = ? , dataR = ? , qta = ? WHERE tipo_cialda = ? AND dataR = ? AND qta = ?";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE dataR = ? AND tipo_cialda = ?";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;


    public RifornimentoDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }

    @Override
    public List<RifornimentoEntry> getAll() throws Exception {
        List<RifornimentoEntry> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =dataBaseConnection.createStatement();
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
        return lista;    }

    @Override
    public boolean save(RifornimentoEntry rifornimentoEntry) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, rifornimentoEntry.getTipoCialda());
        pst.setTimestamp(2,rifornimentoEntry.getData());
        pst.setInt(3,rifornimentoEntry.getQta());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean update(RifornimentoEntry rifornimentoEntry) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(UPDATE_STATEMENT_STRING);
        //new rifornimentoEntry
        pst.setString(1,rifornimentoEntry.getTipoCialda());
        pst.setTimestamp(2,rifornimentoEntry.getData());
        pst.setInt(3,rifornimentoEntry.getQta());
        //old rifornimentoEntry
        RifornimentoEntry oldEntry=(RifornimentoEntry)rifornimentoEntry.getMemento().getMementoState();
        pst.setString(4,oldEntry.getTipoCialda());
        pst.setTimestamp(5,oldEntry.getData());
        pst.setInt(6,oldEntry.getQta());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(RifornimentoEntry rifornimentoEntry) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setTimestamp(1,rifornimentoEntry.getData());
        pst.setString(2, rifornimentoEntry.getTipoCialda());
        pst.executeUpdate();
        return true;
    }


}
