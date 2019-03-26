package backend.daopkg.rowdatapkg;
import backend.daopkg.gateways.RifornimentoDao;
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
    public Class<RifornimentoDao> getCorrespondigDaoClass() {
        return RifornimentoDao.class;
    }

    @Override
    public Memento createMemento() {
        return new MementoRifornimento();
    }

    private class MementoRifornimento extends AbstractMemento implements Memento {

        private Timestamp data;
        private String tipoCialda;
        private int qta;

        @Override
        public <T> void setMementoState(T originator) {
            this.data=((RifornimentoEntry)originator).data;
            this.tipoCialda=((RifornimentoEntry)originator).tipoCialda;
            this.qta=((RifornimentoEntry)originator).qta;
        }

        @Override
        public RifornimentoEntry getMementoState() {
            return new RifornimentoEntry(data,qta,tipoCialda);
        }
    }

}
