package backend.daopkg.rowdatapkg;

import backend.daopkg.gateways.AbstractDao;

public abstract class AbstractEntryDB  {

    public  abstract <T extends AbstractDao>  Class<T> getCorrespondigDaoClass();

    public AbstractEntryDB(){}
}
