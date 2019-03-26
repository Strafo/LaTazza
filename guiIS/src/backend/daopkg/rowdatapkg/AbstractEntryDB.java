package backend.daopkg.rowdatapkg;

import backend.daopkg.gateways.AbstractDao;

public abstract class AbstractEntryDB  {

    private Memento<AbstractEntryDB>mementoState;

    public  abstract <T extends AbstractDao>  Class<T> getCorrespondigDaoClass();

    public AbstractEntryDB(){}

    public void setMementoIfNotDef(){
        if(mementoState!=null){
            mementoState=createMemento();
            mementoState.setMementoState(this);
        }
    }

    public abstract Memento<> createMemento();

}
