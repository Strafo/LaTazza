package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoVendita;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.sql.Timestamp;
import java.util.Date;

public final class Vendita {


    private Vendita(){}

    /**
     *
     * @param data
     * @param cliente
     * @param tipoCialda
     * @param qta
     * @param modalita
     * @return
     * @throws NullPointerException
     */
    public static boolean aggiungiMovimentoVendita(Timestamp data, Cliente cliente, CialdeEntry tipoCialda, int qta, boolean modalita)
            throws NullPointerException{
        return LaTazzaApplication.backEndInvoker.getDao().save(new MovimentoVendita(data,cliente,qta,tipoCialda,modalita));
    }

    /**
     *
     * @param cliente
     * @param tipoCialda
     * @param qta
     * @param modalita
     * @return
     * @throws NullPointerException
     */
    public static boolean aggiungiMovimentoVendita(Cliente cliente, CialdeEntry tipoCialda, int qta, boolean modalita)
            throws NullPointerException{
        Date date=new Date();
        return aggiungiMovimentoVendita(new Timestamp(date.getTime()),cliente,tipoCialda,qta,modalita);
    }
}
