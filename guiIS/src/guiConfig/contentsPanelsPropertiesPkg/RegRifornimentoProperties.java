package guiConfig.contentsPanelsPropertiesPkg;

import guiLogicPkg.LaTazzaFrame;
import guiLogicPkg.contentsPanelsPkg.RegistraRifornimento;
import java.awt.*;

public class RegRifornimentoProperties {
    public static final String DEFAULT_LINKDESCRIPTION="Registra rifornimento";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.REGRIFORNIMENTOPANE;

    public static final int DEFAULTX = 190;
    public static final int DEFAULTY = 31;
    public static final int DEFAULT_WIDTH = 610;
    public static final int DEFAULT_HEIGHT = 500;

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static void initRegistraRifornimentoPanel(RegistraRifornimento rRP)throws NullPointerException{
        if(rRP==null){
            throw new NullPointerException();
        }
        rRP.setLayout(DEFAULT_LAYOUTMANAGER);
        rRP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        rRP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        rRP.setVisible(DEFAULT_VISIBILITY);

    }


    private RegRifornimentoProperties(){}
}
