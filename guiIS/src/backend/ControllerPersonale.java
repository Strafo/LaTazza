package backend;

import java.util.ArrayList;
import java.util.List;

public class ControllerPersonale {
    private ArrayList<Personale> list;

    public ControllerPersonale(){
        list= new ArrayList<Personale>();
    }


    public List<Personale> getCopyList(){
        ArrayList<Personale> l= new ArrayList<>(list);
        return l;
    }
    public void aggiungiPersonale(String nome, String cognome){

        Personale p=new Personale(nome,cognome);
        if(list.contains(p)) return;
        list.add(p);
    }

    public void rimuoviPersonale(Personale p){
        list.remove(p);
    }
}
