package backend.daopkg;
import backend.clientpkg.Personale;


import java.sql.*;
import java.util.*;


public class PersonaleDao implements Dao<Personale> {

    private Database database;
    private static String tableName="LATAZZASCHEMA.personale";

    public PersonaleDao(Database database)throws NullPointerException {
        this.database=Objects.requireNonNull(database);
    }


    @Override
    public List<Personale> getAll() {
        List<Personale> listaPersonale=new LinkedList<>();
        Statement st;
        ResultSet rs;

        try{

            st = (database.createAndGetConnection()).createStatement();
            rs = st.executeQuery("SELECT * FROM "+tableName);
            while (rs.next()) {
                listaPersonale.add(
                        new Personale(
                                rs.getString("nome"),
                                rs.getString("cognome"),
                                rs.getBoolean("attivo")
                        )
                );
            }
        } catch (Database.DataBaseCreationException | SQLException sqlexc) {
                    //todo
        }
        return listaPersonale;
    }


    @Override
    public void save(Personale persona) {
        if(Objects.isNull(persona))return;

        try {
            PreparedStatement pst;
            pst = (database.createAndGetConnection()).prepareStatement(
                    "INSERT INTO " + tableName + " (nome,cognome,attivo) VALUES (?,?,?)");
            pst.setString(1, persona.getNome());
            pst.setString(2, persona.getCognome());
            pst.setBoolean(3,persona.isAttivo());
            pst.executeUpdate();

        }catch(Database.DataBaseCreationException | SQLException e) {
            //todo

        }
    }

    @Override
    public void update(Personale oldPersona,Personale newPersona) {
        if(Objects.isNull(oldPersona)||Objects.isNull(newPersona))return;

        try {
            PreparedStatement pst;
            pst = (database.createAndGetConnection()).prepareStatement(
                    "UPDATE  " + tableName + " SET nome = ? , cognome = ? , attivo = ? WHERE nome = ? AND cognome = ?");
            pst.setString(1, newPersona.getNome());
            pst.setString(2, newPersona.getCognome());
            pst.setBoolean(3,newPersona.isAttivo());
            pst.setString(4, oldPersona.getNome());
            pst.setString(5,oldPersona.getCognome());
            pst.executeUpdate();

        }catch(Database.DataBaseCreationException | SQLException e) {
            //todo

        }

    }

    @Override
    public void delete(Personale persona) {
        if(Objects.isNull(persona))return;

        try {
            PreparedStatement pst;

            pst = (database.createAndGetConnection()).prepareStatement("DELETE FROM " + tableName + " WHERE nome = ? AND cognome = ?");
            pst.setString(1, persona.getNome());
            pst.setString(2, persona.getCognome());
            pst.executeUpdate();
        }catch(Database.DataBaseCreationException | SQLException e) {
            //todo

        }
    }


/*
    @Override
    public Optional<Personale> get(Map keys) {
        if(Objects.isNull(keys))return Op;
        PreparedStatement pst;
        String query="SELECT * FROM " + tableName;

        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            i += pair.getKey() + pair.getValue();
        }




        try {

            pst = (database.createAndGetConnection()).prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE nome = ? AND cognom");



            pst.setString(1, persona.getNome());
            pst.setString(2, persona.getCognome());
            pst.setBoolean(3,persona.isAttivo());
            pst.executeUpdate();

        }catch(Database.DataBaseCreationException | SQLException e) {
            //todo

        }
    }
*/




}