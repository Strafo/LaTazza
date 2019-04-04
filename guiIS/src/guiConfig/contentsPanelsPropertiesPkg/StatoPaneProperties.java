package guiConfig.contentsPanelsPropertiesPkg;

import guiLogicPkg.LaTazzaFrame;
import guiLogicPkg.contentsPanelsPkg.StatoPane;

import java.awt.*;

import static guiLogicPkg.LaTazzaFrame.JPanelsNames.STATOPANE;

public class StatoPaneProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Stato";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=STATOPANE;
    public static final String[] DEFAULT_LABELDESCRIPTION={"Stato magazzino, cassa e debiti personale","Magazzino","Saldo cassa:","Debiti personale","140'000"};

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=true;

    public static final int DEFAULTX_COLONNA1 = 50;
    public static final int DEFAULTX_COLONNA3 = 320;

    public static final int DEFAULTY_RIGA1 = 100;
    public static final int DEFAULTY_RIGA3 = 365;

    public static final int DEFAULT_GAP_LABEL = 30;
    public static final int DEFAULT_GAP_LABEL2 = 130;
    public static final int DEFAULT_GAP_LABEL3 = 8;

    public static final int DEFAULT_HEIGHT_PANEL = 215;
    public static final int DEFAULT_WIDTH_PANEL = 250;

    public static final int DEFAULT_WIDTH_PANEL1 = 110;

    public static final int DEFAULT_WIDTH_PANEL2 = 118;



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
