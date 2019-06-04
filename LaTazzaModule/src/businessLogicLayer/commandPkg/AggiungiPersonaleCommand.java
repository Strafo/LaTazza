package businessLogicLayer.commandPkg;

import businessLogicLayer.BackEndInvoker;

public class AggiungiPersonaleCommand implements Command {

    private String nome, cognome;
    private BackEndInvoker backEndInvoker;

    public AggiungiPersonaleCommand(String nome, String cognome, BackEndInvoker backEndInvoker){
        this.nome=nome;
        this.cognome=cognome;
        this.backEndInvoker=backEndInvoker;
    }

    @Override
    public LaTazzaErrno execute() {
        return backEndInvoker.getControllerPersonale().aggiungiPersonale(nome,cognome);
    }
}
