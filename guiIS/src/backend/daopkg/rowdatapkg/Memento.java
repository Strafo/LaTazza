package backend.daopkg.rowdatapkg;

public interface Memento {


     <T> void setMementoState(T originator);

     Object getMementoState();


}
