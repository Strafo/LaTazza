package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import guiConfig.contentsPanelsPropertiesPkg.AbstractPanelProperties;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.GestionePersonale;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import java.awt.*;

public class GestPersonaleProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Gestione personale";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.GESTIONEPERSONALEPANE;
    public static final String[] DEFAULT_LABELDESCRIPTION={"Aggiungi personale","Conferma","Rimuovi personale","Conferma"};

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static final int DEFAULTX_COLONNA1 = 80;
    public static final int DEFAULTX_COLONNA2 = 380;

    public static final int DEFAULTY_RIGA1  = 185;
    public static final int DEFAULTY_RIGA2  = 220;
    public static final int DEFAULTY_RIGA3  = 290;

    public static final int GAP_BUTTON = 28;


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
