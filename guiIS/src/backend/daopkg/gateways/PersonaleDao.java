package backend.daopkg.gateways;
import backend.clientpkg.Personale;
import utils.ThrowingBiPredicate;
import utils.ThrowingFunction;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonaleDao extends AbstractDao<Personale> {
    private static final String TABLE_NAME="LATAZZASCHEMA.personale";
    private static final String INSERT_STATEMENT_STRING = "INSERT INTO " + TABLE_NAME + " (nome,cognome,attivo) VALUES (?,?,?)";
    private static final String UPDATE_STATEMENT_STRING = "UPDATE  " + TABLE_NAME + " SET nome = ? , cognome = ? , attivo = ? WHERE nome = ? AND cognome = ? AND attivo = ?";
    private static final String DELETE_STATEMENT_STRING = "DELETE FROM " + TABLE_NAME + " WHERE nome = ? AND cognome = ?";
    private static final String GET_ALL_STRING="SELECT * FROM "+TABLE_NAME;

    public PersonaleDao(Connection dataBaseConnection){
        super(TABLE_NAME);
        this.setDataBaseConnection(dataBaseConnection);
    }




    private static ThrowingFunction<Connection,List<Personale>> getAllLambda=(Connection conn)->{
        List<Personale> lista=new LinkedList<>();
        ResultSet rs;
        Statement st =conn.createStatement();
        rs=st.executeQuery(GET_ALL_STRING);
        while(rs.next()){
            lista.add(
                    new Personale(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getBoolean("attivo")
                    )
            );
        }
        return lista;
    };

    private static ThrowingBiPredicate<Connection,Personale> updateLambda=(Connection conn,Personale pers)->{
        return false;//TODO TOBE IMPLEMTED
    };



    private static ThrowingBiPredicate<Connection,Personale>  saveLambda=(Connection conn,Personale pers)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(INSERT_STATEMENT_STRING);
        pst.setString(1, pers.getNome());
        pst.setString(2, pers.getCognome());
        pst.setBoolean(3,pers.isAttivo());
        pst.executeUpdate();
        return true;
    };



    private static ThrowingBiPredicate<Connection,Personale>  deleteLambda=(Connection conn,Personale pers)->{
        PreparedStatement pst;
        pst=conn.prepareStatement(DELETE_STATEMENT_STRING);
        pst.setString(1, pers.getNome());
        pst.setString(2, pers.getCognome());
        pst.executeUpdate();
        return true;
    };


    @Override
    public ThrowingFunction<Connection, List<Personale>> getLambdaGetAll()  {
        return getAllLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, Personale> getLambdaUpdate()  {
        return updateLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, Personale> getLambdaSave()  {
        return saveLambda;
    }

    @Override
    public ThrowingBiPredicate<Connection, Personale> getLambdaDelete()  {
        return deleteLambda;
    }

}
