package businessLogicLayer.commandPkg;

import businessLogicLayer.BackEndInvoker;
import utils.Euro;

public class PagamentoDebitoCommand implements Command {

    private String nome,cognome;
    private Euro importo;
    private BackEndInvoker backEndInvoker;

    public PagamentoDebitoCommand(String nome, String cognome, Euro importo, BackEndInvoker backEndInvoker){
        this.nome=nome;
        this.cognome= cognome;
        this.importo=importo;
        this.backEndInvoker=backEndInvoker;

    }

    @Override
    public LaTazzaErrno execute(){
        LaTazzaErrno lte= backEndInvoker.getControllerDebito().registrarePagamentoDebito(importo,nome,cognome);
        backEndInvoker.getControllerContabilita().updateCassa();
        return lte;
    }
}
