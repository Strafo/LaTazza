package businessLogicLayer.commandPkg;

/**
 * Questa interfaccia viene implementata( ed è pensata in coppia con) la classe BackEndInvoker.
 * Insieme implementano il command pattern.
 * BackEndInvoker e i vari commands si pongono, in una architettura multi-tier, come il confine tra il presentationLayer e il BusinessLogicLayer
 * e cerca di fare Information Hiding rispetto alla gestione del businessLogicLayer e le sue eccezioni.
 * Cerca di rendere semplice la gestione del backend tramite l'utilizzo del command pattern.
 * Il metodo executeCommand() di BackENdInvoker   ha come possibile risultato i vari codici
 * dell'enumeratore LaTazzaErrno.
 * Il metodo non restituirà MAI un eccezione (neanche i nullP. exc) ed inoltre i controllers
 * del businesslogiclayer si occuperanno del ripristino della consistenza dei dati.(se possibile)
 * Se un comando fallisce viene aggiornato un file di log dove viene
 * riportata data-ora-causa dell'eccezione.
 */
public interface Command {
    LaTazzaErrno execute() throws Exception;

    enum  LaTazzaErrno {
        NOERROR,
        ERROREGENERICO,
        CIALDEINSUFF,
        PERSONALEGIAESISTENTE,
        PERSONALEINESISTENTE,
        ERROREDATABASE,
        IMPORTOMAGGIOREDIDEBITO,
        FONDIINSUFFICIENTI,
        ERROREVISITATOREDEBITO
    }
}
