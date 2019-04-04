package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import guiConfig.contentsPanelsPropertiesPkg.AbstractPanelProperties;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.RegistraRifornimento;
import java.awt.*;

public class RegRifornimentoProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Registra rifornimento";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.REGRIFORNIMENTOPANE;
    public static final String[] DEFAULT_LABELDESCRIPTION={"Registra rifornimento scatole","Tipo cialde","Quantità scatole","Conferma","Annulla"};

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static final int DEFAULTX_COLONNA1 = 235;

    public static final int DEFAULTY_RIGA1 = 140;
    public static final int DEFAULTY_RIGA2 = 165;

    public static final int DEFAULTX_BUTTON1 = 190;
    public static final int DEFAULTX_BUTTON2 = 350;


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
