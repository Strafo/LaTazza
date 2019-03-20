package backend;

public final class Personale extends Cliente {


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

    public void pagamentoDebito(Euro importo){
        debito.pagamentoDebito(this,importo);
    }

    public Euro getImportoDebito(){
        return debito.getImporto();
    }

    public Personale(String nome, String cognome,boolean attivo) {
        super(nome, cognome);
        this.attivo=attivo;
        debito= new Debito( new Euro(0));
    }

    public Personale(String nome, String cognome) {
        this(nome, cognome, true);
    }

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

}
