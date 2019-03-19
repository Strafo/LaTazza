package backend.clientpkg;
import backend.Debito;
import backend.daopkg.gateways.PersonaleDao;
import backend.daopkg.rowdatapkg.EntryDB;


public final class Personale extends Cliente implements EntryDB {


    private Debito debito;
    private boolean attivo;

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {//todo denvo essere consiste con il database per�
        this.attivo = attivo;
    }

    public Debito getDebito() {
        return debito;
    }

    public void setDebito(Debito debito) {
        this.debito = debito;
    }


    public Personale(String nome, String cognome,boolean attivo) {
        super(nome, cognome);
        this.attivo=attivo;
    }

    public Personale(String nome, String cognome) {
        super(nome, cognome);
        this.attivo=true;
    }

    public Personale(){}

    @Override
    public String toString(){
        String string ="nome:" +this.getNome()+" cognome:"+this.getCognome()+
                " attivo:"+String.valueOf(isAttivo());
        if (debito==null){
            string+=" debito:null";
        }else{
            string+=" debito:"+debito.toString();
        }
        return string;
    }

    @Override
    public Class<PersonaleDao> getCorrespondigDao() {
        return PersonaleDao.class;
    }


}
