package guiConfig.contentsPanelsPropertiesPkg;

import guiLogicPkg.LaTazzaFrame;
import guiLogicPkg.contentsPanelsPkg.StatoPane;

import java.awt.*;

import static guiLogicPkg.LaTazzaFrame.JPanelsNames.STATOPANE;

public class StatoPaneProperties {

    public static final int DEFAULTX_LABELTITOLO = 27;
    public static final int DEFAULTY_LABELTITOLO = 27;
    public static final int DEFAULT_WIDTH_LABELTITOLO = 500;
    public static final int DEFAULT_HEIGHT_LABELTITOLO = 50;



    public static final String DEFAULT_LINKDESCRIPTION="Stato";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=STATOPANE;

    public static final int DEFAULTX = 190;
    public static final int DEFAULTY = 31;
    public static final int DEFAULT_WIDTH = 610;
    public static final int DEFAULT_HEIGHT = 500;

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=true;

    public static void initStatoPanel(StatoPane sP)throws NullPointerException{
        if(sP==null){
            throw new NullPointerException();
        }
        sP.setLayout(DEFAULT_LAYOUTMANAGER);
        sP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        sP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        sP.setVisible(DEFAULT_VISIBILITY);

    }

    private StatoPaneProperties(){}

}
