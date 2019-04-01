package backend.dataAccessLayer.mementoPkg;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import utils.Euro;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;

public class MementoMovimentoDebito extends MementoMovimento implements Memento {

    private Euro importo;

    @Override
    public <T> void setMementoState(T originator) {
        super.setMementoState(originator);
        this.importo=((MovimentoDebito)originator).getImporto();
    }

    @Override
    public  MovimentoDebito getMementoState(){
        return new MovimentoDebito(data,(Personale)cliente,importo);
    }
}
