package backend;

import java.util.Date;
import java.util.Objects;

public final class MovimentoVendita extends Movimento {
    public int quantita;
    public TipoCialda tipo=TipoCialda.caffè;


    public MovimentoVendita(Date data, Cliente cliente,int quantita,TipoCialda tipo) throws IllegalArgumentException {
        super(data, cliente);
        this.tipo=Objects.requireNonNull(tipo);
        if(quantita<=0){
            throw new IllegalArgumentException("quantità negativa o uguale a zero");
        }
    }

    public int getQuantita() {
        return quantita;
    }

    public TipoCialda getTipo() {
        return tipo;
    }

    public boolean aggiungiVendita(){
        //todo
        return false;
    }


}
