package dataAccessLayer.gatewaysPkg.receiverPkg;
import dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class VisitatoreDaoReceiver extends AbstractDaoReceiver<Visitatore> {

    public static final String TABLE_NAME="LATAZZASCHEMA.visitatore";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome) VALUES (?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? WHERE nome = ? AND cognome = ? ";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ?";

    public VisitatoreDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }

    @Override
    public List<Visitatore> getAll() throws Exception {
        List<Visitatore> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =dataBaseConnection.createStatement();
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
    }

    @Override
    public boolean save(Visitatore visitatore) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, visitatore.getNome());
        pst.setString(2, visitatore.getCognome());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Visitatore visitatore) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(UPDATE_STATEMENT_STRING);
        //new entry
        pst.setString(1,visitatore.getNome());
        pst.setString(2,visitatore.getCognome());
        //old entry
        Visitatore old= (Visitatore) visitatore.getMemento().getMementoState();
        pst.setString(3,old.getNome());
        pst.setString(4,old.getCognome());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(Visitatore visitatore) throws Exception {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, visitatore.getNome());
        pst.setString(2, visitatore.getCognome());
        pst.executeUpdate();
        return true;
    }

}
