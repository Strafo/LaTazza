package businessLogicLayer.commandPkg;

import businessLogicLayer.BackEndInvoker;

public class PersonaleGiaPresenteCommand implements Command {

    private String nome, cognome;
    private BackEndInvoker backEndInvoker;

    public PersonaleGiaPresenteCommand(String nome, String cognome, BackEndInvoker backEndInvoker){
        this.nome=nome;
        this.cognome=cognome;
        this.backEndInvoker=backEndInvoker;
    }

    @Override
    public boolean execute() throws Exception {
        return backEndInvoker.getControllerPersonale().getPersonale(nome, cognome) != null;
    }
}
