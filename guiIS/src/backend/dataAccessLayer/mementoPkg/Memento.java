package backend.dataAccessLayer.mementoPkg;

public interface Memento {


     <T> void setMementoState(T originator);

     Object getMementoState();


}
