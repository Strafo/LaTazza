package backend.memento;

import backend.daopkg.rowdatapkg.RifornimentoEntry;

import java.sql.Timestamp;

public class MementoRifornimento extends AbstractMemento implements Memento {
    private Timestamp data;
    private String tipoCialda;
    private int qta;

    @Override
    public <T> void setMementoState(T originator) {
        this.data=((RifornimentoEntry)originator).getData();
        this.tipoCialda=((RifornimentoEntry)originator).getTipoCialda();
        this.qta=((RifornimentoEntry)originator).getQta();
    }

    @Override
    public RifornimentoEntry getMementoState() {
        return new RifornimentoEntry(data,qta,tipoCialda);
    }

}
