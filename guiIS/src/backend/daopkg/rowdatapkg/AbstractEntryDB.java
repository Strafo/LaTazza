package backend.daopkg.rowdatapkg;

import backend.daopkg.gateways.AbstractDao;

public abstract class AbstractEntryDB implements EntryDB {

    @Override
    public  abstract  Class<? extends AbstractDao> getCorrespondigDaoClass();

    public AbstractEntryDB(){}
}
