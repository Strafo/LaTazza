package backend.daopkg.rowdatapkg;

public interface Memento<E> {


    void setMementoState(E originator);

    T getMementoState();


}
