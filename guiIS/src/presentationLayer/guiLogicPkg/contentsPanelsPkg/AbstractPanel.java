package presentationLayer.guiLogicPkg.contentsPanelsPkg;


import presentationLayer.guiLogicPkg.LaTazzaFrame;
import javax.swing.*;
import java.util.Observer;


public abstract class AbstractPanel extends JPanel implements Observer {

    private final long serialVersionUID;
    private final String LINKDESCRIPTION;
    private final LaTazzaFrame.JPanelsNames PANELNAME;


    AbstractPanel(long serialVersionUID, String linkdescription, LaTazzaFrame.JPanelsNames panelname) {
        this.serialVersionUID = serialVersionUID;
        LINKDESCRIPTION = linkdescription;
        PANELNAME = panelname;
    }


    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLINKDESCRIPTION() {
        return LINKDESCRIPTION;
    }

    public LaTazzaFrame.JPanelsNames getPANELNAME() {
        return PANELNAME;
    }



}
