package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import guiConfig.contentsPanelsPropertiesPkg.AbstractPanelProperties;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.RegistraVendite;
import java.awt.*;

public class RegVenditeProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Registra vendita";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.REGVENDITEPANE;
    public static final String[] DEFAULT_LABELDESCRIPTION={"Registra vendita cialde","Nome personale","Tipo cialde",
                "Nome cliente","oppure","Quantità","Pagamento:","Contanti","A credito","Conferma","Annulla"};

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static final int DEFAULTX_COLONNA1 = 65;
    public static final int DEFAULTX_COLONNA2 = 279;
    public static final int DEFAULTX_COLONNA3 = 367;

    public static final int DEFAULTY_RIGA1 = 140;
    public static final int DEFAULTY_RIGA2 = 168;
    public static final int DEFAULTY_RIGA3 = 240;
    public static final int DEFAULTY_RIGA4 = 268;
    public static final int DEFAULTY_RIGA5 = 328;
    public static final int DEFAULT_RIGA6 = 393;

    public static final int DEFAULTX_BUTTON1 = 150;
    public static final int DEFAULTX_RADIO1 = 210;
    public static final int DEFAULTX_BUTTON2 = 320;

    public static final int DEFAULT_GAP_LABELOPPURE = 5;
    public static final int DEFAULT_GAP_LABELPAGAMENTO = 60;
    public static final int DEFAULT_GAP_RADIOBUTTON = 4;

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
