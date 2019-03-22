package backend.movimentopkg;
import backend.Euro;
import backend.clientpkg.Cliente;
import backend.daopkg.gateways.MovimentoDebitoDao;
import java.util.Date;
import java.util.Objects;

public final class MovimentoDebito extends Movimento {
    private Euro importo;

    public MovimentoDebito(Date data, Cliente cliente, Euro importo) {
        super(data, cliente);
        this.importo=Objects.requireNonNull(importo);
    }

    public MovimentoDebito(){}

    public Euro getImporto() {
        return importo;
    }

    public void setImporto(Euro importo) {
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
}
