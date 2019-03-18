package guiConfig.contentsPanelsPropertiesPkg;

import guiLogicPkg.LaTazzaFrame;
import guiLogicPkg.contentsPanelsPkg.RegistraPagamento;
import java.awt.*;

import static guiLogicPkg.LaTazzaFrame.JPanelsNames.REGPAGAMENTOPANE;

public  class RegPagamentoProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Registra pagamento";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=REGPAGAMENTOPANE;

    public static final int DEFAULTX = 190;
    public static final int DEFAULTY = 31;
    public static final int DEFAULT_WIDTH = 610;
    public static final int DEFAULT_HEIGHT = 500;

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static void initRegistraPagamentoPanel(RegistraPagamento rPP)throws NullPointerException{
        if(rPP==null){
            throw new NullPointerException();
        }
        rPP.setLayout(DEFAULT_LAYOUTMANAGER);
        rPP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        rPP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        rPP.setVisible(DEFAULT_VISIBILITY);

    }


    private RegPagamentoProperties(){}

}
