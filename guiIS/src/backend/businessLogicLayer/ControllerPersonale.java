package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ControllerPersonale {
    private ArrayList<Personale> list;
  //  private DaoManager<> dao;
    private Connection connectionDB;

    public ControllerPersonale(Connection c){
        list= new ArrayList<Personale>();
       // dao= new Da(c);
        connectionDB=c;
        initList();
    }

    private void initList(){
        /*List<Personale> dipendenti=dao.getAll();
        for (Personale p: dipendenti) {
            Personale dip= new Personale();
        }*/
    }

    public List<Personale> getCopyList(){
        List<Personale> l= new ArrayList<>(list);
        return l;
    }

    public void aggiungiPersonale(String nome, String cognome){

        Personale p=new Personale(nome,cognome);
        if(list.contains(p)) return;
        list.add(p);
    }

    public void licenziaPersonale(Personale p){
        list.remove(p);
    }
}
