package backend;

public class Euro {

    private long euro;
    private int centesimi;// si potrebbe usare byte

    private static final int MODULONUM=100;//cento centesimi valgono 1 euro.

    /**
     * Crea la classe euro. Se i centesimi passati sono >=MODULONUM vengono eseguite delle semplici operazioni per
     * mantenere la quantità di centesimi nell'intervallo [0,MODULNUM).
     * Il restante dei centesimi che superano la soglia vengono convertiti in euro.
     * @param euro la quantità di euro da instanziare
     * @param centesimi la quantità di centesimi
     * @throws IllegalArgumentException se i parametri hanno valore negativo.
     * @throws OverflowEuroException se la quantità passata provoca un overflow sul tdd primitivo long.
     * Se infatti viene passato ad esempio una quantità di euro=LONG.MAXVALUE e MODULONUM+1 centesimi questo provocherà
     * un riporto di un euro e quindi un overflow.
     */
    public Euro(long euro,int centesimi) throws IllegalArgumentException, OverflowEuroException {
        if (euro < 0 || centesimi < 0)
            throw new IllegalArgumentException("impossibile istanziare quantità negativa");
        try {
            this.euro = Math.addExact(Math.floorDiv(centesimi, MODULONUM), euro);
            this.centesimi = centesimi % MODULONUM;
        }catch(ArithmeticException exc){
            throw new OverflowEuroException("Impossibile creare l'importo. (overflow)",exc.getCause());
        }
    }

    /**
     * Crea la classe euro e la istanzia con 0 centesimi e param:euro euro.
     * @param euro
     * @throws IllegalArgumentException se i euro ha valore negativo.
     */
    public Euro(long euro) throws IllegalArgumentException {
        if (euro < 0)
            throw new IllegalArgumentException("impossibile istanziare quantità negativa");
        this.euro=euro;
        this.centesimi=0;
    }

    public Euro(int centesimi)throws IllegalArgumentException{
        if(centesimi<0)throw  new IllegalArgumentException("impossibile istanziare quantità negativa");
        this.euro=Math.floorDiv(centesimi,MODULONUM);
        this.centesimi=centesimi%MODULONUM;
    }

    /**Confronta le somme contenute nelle due classi Euro.
     * @param e la somma da confrontare.
     * @return true se la somma corrisponde sia per i centesimi che per gli euro,false altrimenti.
     * @throws NullPointerException se param:e è un null ref.
     */
    public boolean equals(Euro e)throws NullPointerException{
        return (this.euro==e.euro&&this.centesimi==e.centesimi);
    }

    /**
     * Compara due quantità di Euro e determina se somma1 è maggiore;minore;uguale a somma2
     * @param somma1 il primo importo dda confrontare
     * @param somma2 il secondo importo da confrontare
     * @return 1 se l'importo somma 1 è maggiore di somma2; 0 se gli importi sono uguali; -1 se somma 1 è minore di somma 2
     * @throws NullPointerException se somma1 o somma2 sono due null ref.
     */
    public static int compare(Euro somma1,Euro somma2)throws NullPointerException{
        if(somma1.euro>somma2.euro)
            return 1;
        else
        if(somma1.euro==somma2.euro){
            if(somma1.centesimi>somma2.centesimi){
                return 1;
            }else{
                return somma1.centesimi==somma2.centesimi ? 0:-1;
            }
        }
        return -1;
    }

    /**
     * Aggiunge l'importo passato.
     * @param
     * @return il risultato della somma
     * @throws OverflowEuroException se la somma della quantità passata provoca un overflow sul tdd primitivo long.
     * @throws NullPointerException se euroDaAggiungere è un null ref.
     */
    public Euro aggiungiImporto(Euro euroDaAggiungere) throws OverflowEuroException,NullPointerException {
        try{
            int newCent=this.centesimi+euroDaAggiungere.centesimi;
            long riportoCent=Math.floorDiv(newCent,MODULONUM);
            newCent=newCent%MODULONUM;

            this.euro= Math.addExact(Math.addExact(this.euro,euroDaAggiungere.euro),riportoCent);
            this.centesimi=newCent;
            return this.clone();
        }catch(ArithmeticException exc){
            throw new OverflowEuroException("Impossibile aggiungere euro:"+euro+" centesimi:"+centesimi+" (overflow)",exc.getCause());
        }
    }



    /**
     * Sottrae l'importo passato dal istanza che chiama il metodo se e solo se quest'ultima è >= di euroDaSottarre.
     * @param euroDaSottrarre
     * @return il resto della sottrazione
     * @throws InsufficientFundsException se euroDaSottare < this.
     * @throws NullPointerException se euroDaSottrarre è un null ref.
     */
    public Euro sottraiImporto(Euro euroDaSottrarre) throws NullPointerException, InsufficientFundsException {
        if(compare(this,euroDaSottrarre)<0)
            throw new InsufficientFundsException("Impossibile sottrarre euroDaSottrare;fondi insufficenti");
        this.euro-=euroDaSottrarre.euro;
        if(this.centesimi>=euroDaSottrarre.centesimi){
            this.centesimi-=euroDaSottrarre.centesimi;
        }else{
            this.euro--;
            this.centesimi=this.centesimi+MODULONUM-euroDaSottrarre.centesimi;
        }
        return this.clone();
    }



    public long getEuro() {
        return euro;
    }

    public int getCentesimi() {
        return centesimi;
    }

    @Override
    public Euro clone(){ return new Euro(this.euro,this.centesimi); }

    @Override
    public String toString() {
        return "Euro:"+euro+"."+centesimi;
    }

    /******************************************
     * CUSTOM EXCEPTIONS
     *****************************************/

    public class InsufficientFundsException extends RuntimeException{

        public InsufficientFundsException(String message, Throwable cause) {
            super(message, cause);
        }

        public InsufficientFundsException(Throwable cause) {
            super(cause);
        }

        public InsufficientFundsException(String message) {
            super(message);
        }
    }



    public class OverflowEuroException extends RuntimeException{

        public OverflowEuroException(Throwable cause){
            super(cause);
        }
        public OverflowEuroException(String message,Throwable cause){
            super(message,cause);
        }

        public OverflowEuroException(String message) {
            super(message);
        }
    }
}
