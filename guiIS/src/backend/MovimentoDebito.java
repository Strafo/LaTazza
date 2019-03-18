package backend;

import java.util.Date;
import java.util.Objects;

public final class MovimentoDebito extends Movimento {
    private Euro importo;

    public MovimentoDebito(Date data, Cliente cliente,Euro importo) {
        super(data, cliente);
        this.importo=Objects.requireNonNull(importo);
    }

    public Euro getImporto() {
        return importo;
    }

    public void aggiornaDebito(){
        //todo
    }
}
