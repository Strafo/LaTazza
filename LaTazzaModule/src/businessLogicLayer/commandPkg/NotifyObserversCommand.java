package businessLogicLayer.commandPkg;

import businessLogicLayer.BackEndInvoker;
import businessLogicLayer.ObserverSubscriptionType;
import java.util.List;

public class NotifyObserversCommand implements Command {

    private List<ObserverSubscriptionType> subscriptionTypes;
    private BackEndInvoker backEndInvoker;

    public NotifyObserversCommand(List<ObserverSubscriptionType> subTypes, BackEndInvoker backEndInvoker){
        subscriptionTypes=subTypes;
        this.backEndInvoker=backEndInvoker;
    }

    @Override
    public boolean execute() throws NullPointerException {
        for (ObserverSubscriptionType sub:subscriptionTypes) {
            backEndInvoker.getSubscriptions().get(sub).notifyObservers(sub);
        }
        return true;
    }
}
