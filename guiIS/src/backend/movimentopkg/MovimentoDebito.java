package backend.movimentopkg;
import backend.Euro;
import backend.clientpkg.Cliente;
import backend.daopkg.gateways.MovimentoDebitoDao;
import backend.daopkg.rowdatapkg.Memento;
import java.sql.Timestamp;
import java.util.Objects;

public final class MovimentoDebito extends Movimento {
    private Euro importo;

    public MovimentoDebito(Timestamp data, Cliente cliente, Euro importo) {
        super(data, cliente);
        this.importo=Objects.requireNonNull(importo);
    }

    public MovimentoDebito(){}

    public Euro getImporto() {
        return importo;
    }

    public void setImporto(Euro importo) {
        setMementoIfNotDef();
        this.importo = importo;
    }

    public void aggiornaDebito(){
        //todo
    }

    @Override
    public Class<MovimentoDebitoDao> getCorrespondigDaoClass() {
        return MovimentoDebitoDao.class;
    }

    @Override
    public String toString() {
        return super.toString()+
                "  importo:"+importo.toString()+" (MovimentoDebito)";
    }

    @Override
    public Memento createMemento() {
        return new MementoMovimentoDebito();
    }


    private class MementoMovimentoDebito extends MementoMovimento implements Memento {

        private  Euro importo;

        @Override
        public <T> void setMementoState(T originator) {
            super.setMementoState(originator);
            this.importo=((MovimentoDebito)originator).importo;
        }

        @Override
        public  MovimentoDebito getMementoState(){
            return new MovimentoDebito(data,cliente,importo);
        }
    }
}
