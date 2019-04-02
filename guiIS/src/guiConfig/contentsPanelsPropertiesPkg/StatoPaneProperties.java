package guiConfig.contentsPanelsPropertiesPkg;

import guiLogicPkg.LaTazzaFrame;
import guiLogicPkg.contentsPanelsPkg.StatoPane;

import java.awt.*;

import static guiLogicPkg.LaTazzaFrame.JPanelsNames.STATOPANE;

public class StatoPaneProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Stato";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=STATOPANE;
    public static final String[] DEFAULT_LABELDESCRIPTION={"Stato magazzino, cassa e debiti personale","Magazzino","Cassa","Debiti personale","Saldo:"};

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=true;

    public static final int DEFAULTX_COLONNA1 = 40;
    public static final int DEFAULTX_COLONNA2 = 230;
    public static final int DEFAULTX_COLONNA3 = 360;

    public static final int DEFAULTY_RIGA1 = 130;
    public static final int DEFAULTY_RIGA2 = 185;
    public static final int DEFAULTY_RIGA3 = 370;


    public static final int DEFAULT_RIGA_SEPARATOR = 153;
    public static final int DEFAULT_RIGA_SEPARATOR2 = 393;
    public static final int DEFAULT_WIDTH_SEPARATOR = 104;
    public static final int DEFAULT_HEIGHT_SEPARATOR = 1;

    public static final int DEFAULT_GAP_SP2 = 32;
    public static final int DEFAULT_GAP_SP3 = 20;
    public static final int DEFAULT_GAP_SP3_COLONNA = 22;

    public static final int DEFAULT_GAP_LABEL = 30;

    public static final int DEFAULT_HEIGHT_SCROLLPANE = 200;
    public static final int DEFAULT_WIDTH_SCROLLPANE = 220;


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
