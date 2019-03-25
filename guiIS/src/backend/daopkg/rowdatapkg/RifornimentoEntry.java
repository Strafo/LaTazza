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
        this.data = data;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public String getTipoCialda() {
        return tipoCialda;
    }

    public void setTipoCialda(String tipoCialda) {
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
}
