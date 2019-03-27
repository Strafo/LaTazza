package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import presentationLayer.guiLogicPkg.contentsPanelsPkg.GestionePersonale;
import presentationLayer.guiLogicPkg.LaTazzaFrame;

import java.awt.*;

public class GestPersonaleProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Gestione personale";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.GESTIONEPERSONALEPANE;

    public static final int DEFAULTX = 190;
    public static final int DEFAULTY = 31;
    public static final int DEFAULT_WIDTH = 660;
    public static final int DEFAULT_HEIGHT = 500;

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static void initGestionePersonalePanel(GestionePersonale gestPersPanel)throws NullPointerException{
        if(gestPersPanel==null){
            throw new NullPointerException();
        }
        gestPersPanel.setLayout(DEFAULT_LAYOUTMANAGER);
        gestPersPanel.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        gestPersPanel.setBackground(DEFAULT_BACKGROUNDCOLOR);
        gestPersPanel.setVisible(DEFAULT_VISIBILITY);

    }

    private GestPersonaleProperties(){}

}
