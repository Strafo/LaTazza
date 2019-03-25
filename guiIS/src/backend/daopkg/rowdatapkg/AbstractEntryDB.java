package backend.daopkg.rowdatapkg;

import backend.daopkg.gateways.AbstractDao;

public abstract class AbstractEntryDB  {

    protected Memento<?>mementoState;

    public  abstract <T extends AbstractDao>  Class<T> getCorrespondigDaoClass();

    public AbstractEntryDB(){}

    protected void removeMementoState(){mementoState=null;}

    protected void setMementoState(AbstractEntryDB originator){
        if(mementoState!=null){
            this.mementoState=createMemento();
            this.mementoState.setMementoState(originator);
        }
    }b

    public abstract Memento createMemento();

}
