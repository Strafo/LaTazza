package backend;

public class Euro {

    private float valore;

    public Euro(float valore){
        valore=valore;
    }

    public float getValore(){
        return valore;
    }
    public boolean equals(Euro e){
        return (valore==e.getValore());
    }


}
