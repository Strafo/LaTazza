package dataAccessLayer.rowdatapkg;
import dataAccessLayer.mementoPkg.Memento;
import dataAccessLayer.mementoPkg.MementoRifornimento;

import java.sql.Timestamp;

public class RifornimentoEntry extends AbstractEntryDB  {
    private Timestamp data;
    private String tipoCialda;
    private int qta;

    public RifornimentoEntry(Timestamp data, int qta, String tipoCialda) {
        this.data = data;
        this.qta = qta;
        this.tipoCialda = tipoCialda;
    }

    public RifornimentoEntry(){}

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        setMementoIfNotDef();
        this.data = data;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        setMementoIfNotDef();
        this.qta = qta;
    }

    public String getTipoCialda() {
        return tipoCialda;
    }

    public void setTipoCialda(String tipoCialda) {
        setMementoIfNotDef();
        this.tipoCialda = tipoCialda;
    }

    @Override
    public String toString() {
        return "RifornimentoEntry: data:"+data.toString()+" tipoCialda:"+tipoCialda+" qta:"+qta;
    }

    @Override
    public Memento createMemento() {
        return new MementoRifornimento();
    }

    @Override
    public void undoChanges(){
        RifornimentoEntry oldState=(RifornimentoEntry)this.getMemento().getMementoState();
        this.data=oldState.getData();
        this.qta=oldState.getQta();
        this.qta=oldState.getQta();
        removeMemento();
    }


}
