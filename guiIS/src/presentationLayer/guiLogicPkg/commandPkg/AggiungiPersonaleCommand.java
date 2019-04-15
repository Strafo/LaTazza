package presentationLayer.guiLogicPkg.commandPkg;

import presentationLayer.guiLogicPkg.BackEndInvoker;

public class AggiungiPersonaleCommand implements Command {

    private String nome, cognome;
    private BackEndInvoker backEndInvoker;

    public AggiungiPersonaleCommand(String nome, String cognome, BackEndInvoker backEndInvoker){
        this.nome=nome;
        this.cognome=cognome;
        this.backEndInvoker=backEndInvoker;
    }

    @Override
    public boolean execute() throws Exception {
        boolean result= backEndInvoker.getControllerPersonale().aggiungiPersonale(nome,cognome);
        return result;
    }
}
