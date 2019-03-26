package backend.daopkg.rowdatapkg;


public abstract class AbstractMemento<E> implements Memento<E>  {

    @Override
    public abstract void setMementoState(E originator);

    @Override
    public abstract E getMementoState();




}
