package backend.dataAccessLayer.gatewaysPkg;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import java.util.List;

/**
 * Questa interfaccia viene implementata( ed è pensata in coppia con) la classe DaoManager.
 * Questa interfaccia si pone, in una architettura multi-tier, come il confine tra il DataLayer e il BusinessLogicLayer
 * e cerca di fare Information Hiding rispetto alla gestione del backend.database e le sue eccezioni.
 * Implementa quindi insieme all'interfaccie Memento e AbstractEntry il DataAccessLayer.
 * Cerca di rendere semplice la gestione della coerenza tra oggetti in Ram e oggetti salvati nel DB.
 * I metodi (getAll,save...) hanno due possibili risultati: true quindi tutto ok, o false se qualcosa non va a buon fine.
 * I metodi non restituiranno MAI un eccezione (neanche i nullP. exc).
 * Se un metodo di questa interfaccia fallisce viene aggiornato un file di log dove viene
 * riportata data-ora-causa dell'eccezione.
 * @apiNote
 * ESEMPIO:
 *  public static void main(String[] args) {
 *           Connection conn=.... in qualche modo definita;
 *           DaoInterface dao=new DaoManager(conn);
 *           List<Personale> listaPersonale=dao.getAll(Personale.class);//ottengo la lista del personale nel DB.
 *           Personale newPers=new Personale("George","Hotz",true);
 *           if(dao.save(newPers)){//se va tutto ok
 *               listaPersonale.add(newPers);
 *               //a questo punto abbiamo consistenza tra Db e oggetti in ram.
 *           }else{
 *               System.err.println("Impossibile aggiornare il backend.database!");
 *           }
 *           //ora modifico il personale appena inserito "George Hotz" con "andrea straforini"
 *           //lo rimuovo dalla lista così non ho 2 riferimenti...
 *           listaPersonale.remove(newPers);
 *           newPers.setNome("andrea");
 *           newPers.setCognome("straforini");
 *           if(dao.update(newPers)){
 *               listaPersonale.add(newPers);//lo riaggiungo alla lista
 *           }else{
 *               System.out.println("Update fallito ripristino stato originale");
 *               newPers.undoChanges();
 *           }
 *     }
 */
public interface DaoInterface {

    //Optional<T> get(Map keys); TODO TOBEIMPLEMENTED

    /**
     * Questo metodo permette di ottenere la lista di tutti gli oggetti di classe t.
     * @param t il tipo della classe. es. passare Personale.class se si vuole la lista del personale
     * @return la lista  se operazione andata a buon fine, null altrimenti.
     */
    <T extends AbstractEntryDB> List<T> getAll(Class<T> t);

    /**
     * Questo metodo permette di ripristinare la coerenza tra oggetti salvati nel backend.database e oggetti in Ram.
     * @param t l'oggetto da salvare nel backend.database.
     * @return true se operazione andata a buon fine, false altrimenti.
     */
    <T extends AbstractEntryDB> boolean save(T t);

    /**
     * Questo metodo permette di aggiornare l'oggetto passato. t infatti ( implementando AbstractEntry) implementa
     * il pattern mementoPkg.
     * Il DaoManager quindi utilizza quest'ultimo per risalire allo stato originale dell'oggetto.
     * @param t l'oggetto modificato da rendere coerente con la sua "versione" salvata nel DB.
     * @return true se operazione andata a buon fine, false altrimenti.
     * Nota:se l'update va a buon fine il mementoPkg viene eliminato e rimane solo lo stato attuale.
     */
    <T extends AbstractEntryDB> boolean update(T t);

    /**
     * Questo metodo permette di eliminare dal backend.database l'oggetto "salvato" corrispondente all'oggetto passato.
     * @param t l'oggetto da eliminare all'interno del DB.
     * @return true se operazione andata a buon fine, false altrimenti.
     */
    <T extends AbstractEntryDB> boolean delete(T t);



    void startTransaction();

    void endTransaction();


}

