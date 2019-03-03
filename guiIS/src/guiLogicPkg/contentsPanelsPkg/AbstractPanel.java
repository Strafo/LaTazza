package guiLogicPkg.contentsPanelsPkg;


import guiLogicPkg.LaTazzaFrame;
import javax.swing.*;



public abstract class AbstractPanel extends JPanel {

    private final long serialVersionUID;
    private final String LINKDESCRIPTION;
    private final LaTazzaFrame.JPanelsNames PANELNAME;


    protected AbstractPanel(long serialVersionUID, String linkdescription, LaTazzaFrame.JPanelsNames panelname) {
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
