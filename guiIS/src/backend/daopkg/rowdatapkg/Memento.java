package backend.daopkg.rowdatapkg;

public interface Memento<E> {


    void setMementoState(E originator);

    E getMementoState();

}
