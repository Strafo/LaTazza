package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.RegistraVendite;

import java.awt.*;

public class RegVenditeProperties {
    public static final String DEFAULT_LINKDESCRIPTION="Registra vendita";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.REGVENDITEPANE;

    public static final int DEFAULTX = 190;
    public static final int DEFAULTY = 31;
    public static final int DEFAULT_WIDTH = 660;
    public static final int DEFAULT_HEIGHT = 500;

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static void initRegistraVenditePanel(RegistraVendite rVP)throws NullPointerException{
        if(rVP==null){
            throw new NullPointerException();
        }
        rVP.setLayout(DEFAULT_LAYOUTMANAGER);
        rVP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        rVP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        rVP.setVisible(DEFAULT_VISIBILITY);

    }


    private RegVenditeProperties(){}
}
