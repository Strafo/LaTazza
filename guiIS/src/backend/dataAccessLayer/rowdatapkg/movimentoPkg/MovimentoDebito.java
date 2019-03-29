package backend.dataAccessLayer.rowdatapkg.movimentoPkg;
import backend.businessLogicLayer.Euro;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.gatewaysPkg.MovimentoDebitoDao;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.mementoPkg.MementoMovimentoDebito;
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

    @Override
    public void undoChanges(){
        super.undoChanges();
        MovimentoDebito oldState=(MovimentoDebito) this.getMemento().getMementoState();
        this.importo=oldState.getImporto();
        removeMemento();
    }

}
