package backend.daopkg.rowdatapkg;

import backend.daopkg.gateways.AbstractDao;
import backend.memento.Memento;

public abstract class AbstractEntryDB  {

    private Memento mementoState;

    public abstract <T extends AbstractDao>  Class<T> getCorrespondigDaoClass();

    public AbstractEntryDB(){}

    public void setMementoIfNotDef(){
        if(mementoState==null){
            mementoState=createMemento();
            mementoState.setMementoState(this);
        }
    }

    public abstract  Memento createMemento();

    public Memento getMemento(){
        return mementoState;
    }

    public void removeMemento(){
        mementoState=null;
    }

    public AbstractEntryDB undoChanges(){
        AbstractEntryDB ret=(AbstractEntryDB) this.mementoState.getMementoState();
        mementoState=null;
        return ret;
    }

}
