package dataAccessLayer.rowdatapkg;

import dataAccessLayer.mementoPkg.Memento;

public abstract class AbstractEntryDB  {

    private Memento mementoState;


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

    public abstract void undoChanges();

}
