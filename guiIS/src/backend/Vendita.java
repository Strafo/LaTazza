package backend;

import backend.clientpkg.Personale;
import backend.clientpkg.Visitatore;

import java.util.Date;

public class Vendita {


    public boolean aggiungiVenditaVisitatore(Date data, Visitatore visitatore, TipoCialda tipoCialda, int qta){

    MovimentoVendita mv= new MovimentoVendita(data,visitatore,qta,tipoCialda);
    return mv.aggiungiVendita();

    }

    public boolean aggiungiVenditaPersonale(Date data, Personale personale, TipoCialda tipoCialda, int qta, boolean modalita){

        MovimentoVendita mp= new MovimentoVendita(data,personale,qta,tipoCialda);
        return mp.aggiungiVendita();

    }
}
