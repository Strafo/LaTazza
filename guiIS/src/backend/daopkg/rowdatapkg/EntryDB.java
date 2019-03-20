package backend.daopkg.rowdatapkg;
import backend.daopkg.gateways.AbstractDao;

public interface EntryDB {
    Class<? extends AbstractDao>getCorrespondigDaoClass();
}
