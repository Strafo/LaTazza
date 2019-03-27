package backend.daopkg.gateways;
import backend.daopkg.rowdatapkg.AbstractEntryDB;
import java.util.List;

/**
 * Questa interfaccia viene implementata( ed è pensata in coppia con) la classe DaoManager.
 * Questa interfaccia si pone, in una architettura multi-tier, come il confine tra il DataTier e il LogicTier e
 * cerca di fare Information Hiding rispetto alla gestione del database e le sue eccezioni.
 * Cerca inoltre di rendere semmplice la gestione della coerenza tra oggetti in Ram e oggetti salvati nel DB.
 * I metodi (getAll,save...) hanno due possibili risultati: true quindi tutto ok, o false se qualcosa non va a buon fine.
 * I metodi non restituiranno MAI un eccezione (neanche i nullP. exc).
 * Se un metodo di questa interfaccia fallisce viene aggiornato un file di log dove viene
 * riportata data-ora-causa dell'eccezione.
 * @apiNote
 * ESEMPIO:
 *  public static void main(String[] args) {
 *         Connection conn=.... in qualche modo definita;
 *         DaoInterface dao=new DaoManager(conn);
 *         List<Personale>listaPersonale=dao.getAll(Personale.class);//ottengo la lista del personale nel DB.
 *         Personale newPers=new Personale("George","Hotz",true);
 *         if(dao.save(newPers)){//se va tutto ok
 *             listaPersonale.add(newPers);
 *             //a questo punto abbiamo consistenza tra Db e oggetti in ram.
 *         }else{
 *             System.err.println("Impossibile aggiornare il database!");
 *         }
 *     }
 */
public interface DaoInterface {

    //Optional<T> get(Map keys); TODO TOBEIMPLEMENTED

    /**
     * Questo metodo permette di ottenere la lista di tutti gli oggetti di classe t.
     * @param t il tipo della classe. es. passare Personale.class se si vuole la lista del personale
     * @return true se operazione andata a buon fine, false altrimenti.
     */
    <T extends AbstractEntryDB> List<T> getAll(Class<T> t);

    /**
     * Questo metodo permette di ripristinare la coerenza tra oggetti salvati nel database e oggetti in Ram.
     * @param t l'oggetto da salvare nel database.
     * @return true se operazione andata a buon fine, false altrimenti.
     */
    <T extends AbstractEntryDB> boolean save(T t);

    /**
     * Questo metodo permette di aggiornare l'oggetto passato. t infatti ( implementando AbstractEntry) implementa
     * il pattern memento.
     * Il DaoManager quindi utilizza quest'ultimo per risalire allo stato originale dell'oggetto.
     * @param t l'oggetto modificato da rendere coerente con la sua "versione" salvata nel DB.
     * @return true se operazione andata a buon fine, false altrimenti.
     */
    <T extends AbstractEntryDB> boolean update(T t);//todo

    /**
     * Questo metodo permette di eliminare dal database l'oggetto "salvato" corrispondente all'oggetto passato.
     * @param t l'oggetto da eliminare all'interno del DB.
     * @return true se operazione andata a buon fine, false altrimenti.
     */
    <T extends AbstractEntryDB> boolean delete(T t);

}

