package dataAccessLayer.gatewaysPkg.receiverPkg;
import utils.Euro;
import dataAccessLayer.rowdatapkg.CialdeEntry;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CialdeDaoReceiver extends AbstractDaoReceiver<CialdeEntry> {

    public static final String TABLE_NAME="LATAZZASCHEMA.cialde";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (tipo,PREZZO_EURO,PREZZO_CENTESIMI) VALUES (?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET tipo = ? , PREZZO_EURO = ?,PREZZO_CENTESIMI = ? WHERE tipo = ?  ";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE tipo = ? ";

    public CialdeDaoReceiver(Connection dataBaseConnection){
        super(dataBaseConnection);
    }

    @Override
    public List<CialdeEntry> getAll() throws SQLException {
        List<CialdeEntry> lista = new LinkedList<>();
        ResultSet rs;
        Statement st = dataBaseConnection.createStatement();
        rs = st.executeQuery(GET_ALL_STRING);
        while (rs.next()) {
            lista.add(
                    new CialdeEntry(
                            rs.getString("tipo"),
                            new Euro(rs.getInt("prezzo_euro"),rs.getInt("prezzo_centesimi"))
                    )
            );
        }

        return lista;
    }

    @Override
    public boolean save(CialdeEntry cialdeEntry) throws SQLException {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, cialdeEntry.getTipo());
        pst.setInt(2,Math.toIntExact(cialdeEntry.getPrezzo().getEuro()));
        pst.setInt(3,cialdeEntry.getPrezzo().getCentesimi());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean update(CialdeEntry cialdeEntry) throws SQLException {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(UPDATE_STATEMENT_STRING);
        //new entry
        pst.setString(1,cialdeEntry.getTipo());
        pst.setInt(2,Math.toIntExact(cialdeEntry.getPrezzo().getEuro()));
        pst.setInt(3,cialdeEntry.getPrezzo().getCentesimi());
        //old entry
        CialdeEntry oldEntry=(CialdeEntry)cialdeEntry.getMemento().getMementoState();
        pst.setString(4,oldEntry.getTipo());
        pst.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(CialdeEntry cialdeEntry) throws SQLException {
        PreparedStatement pst;
        pst=dataBaseConnection.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, cialdeEntry.getTipo());
        pst.executeUpdate();
        return true;
    }

}
