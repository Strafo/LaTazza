package backend.movimentopkg;
import backend.clientpkg.Cliente;
import backend.daopkg.gateways.AbstractDao;
import backend.daopkg.gateways.MovimentoVenditaDao;
import backend.daopkg.rowdatapkg.CialdeEntry;
import backend.memento.Memento;
import backend.memento.MementoMovimentoVendita;

import java.sql.Timestamp;
import java.util.Objects;

public final class MovimentoVendita extends Movimento {
    private  int quantita;
    private CialdeEntry tipo;
    private boolean contanti;

    public MovimentoVendita(Timestamp data, Cliente cliente, int quantita, CialdeEntry tipo, boolean contanti) throws IllegalArgumentException {
        super(data, cliente);

        this.tipo=Objects.requireNonNull(tipo);
        if(quantita<=0){
            throw new IllegalArgumentException("quantità negativa o uguale a zero");
        }
        this.quantita=quantita;
        this.contanti=contanti;
    }

    public MovimentoVendita(){}

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita){
        setMementoIfNotDef();
        this.quantita=quantita;
    }

    public void setTipo(CialdeEntry tipo) {
        setMementoIfNotDef();
        this.tipo = tipo;
    }

    public void setContanti(boolean contanti) {
        setMementoIfNotDef();
        this.contanti = contanti;
    }

    public CialdeEntry getTipo() {
        return tipo;
    }

    public boolean isContanti() { return contanti;}

    public boolean aggiungiVendita(){
        //todo
        return false;
    }

    @Override
    public Class<?extends AbstractDao> getCorrespondigDaoClass() {
            return MovimentoVenditaDao.class;
    }

    @Override
    public String toString() {
        return super.toString()+
                "  quantità:"+quantita+" tipo:"+tipo.getTipo()+" contanti:"+contanti+" (MovimentoVendita)";
    }

    @Override
    public Memento createMemento() {
        return new MementoMovimentoVendita();
    }


}
