package backend.memento;

public abstract class  AbstractMemento implements Memento {

    @Override
    public  abstract <T> void setMementoState(T originator);

    @Override
    public abstract  Object getMementoState();

}
