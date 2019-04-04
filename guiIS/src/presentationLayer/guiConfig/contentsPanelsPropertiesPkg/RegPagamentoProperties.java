package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.RegistraPagamento;
import java.awt.*;

import static presentationLayer.guiLogicPkg.LaTazzaFrame.JPanelsNames.REGPAGAMENTOPANE;

public  class RegPagamentoProperties extends AbstractPanelProperties{

    public static final String DEFAULT_LINKDESCRIPTION="Registra pagamento";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=REGPAGAMENTOPANE;
    public static final String[] DEFAULT_LABELDESCRIPTION={"Registra pagamento","Nome personale","Ammontare","Conferma","Annulla"};

    public static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    public static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    public static final boolean DEFAULT_VISIBILITY=false;

    public static final int DEFAULTX_COLONNA1 = 235;

    public static final int DEFAULTY_RIGA1 = 140;
    public static final int DEFAULTY_RIGA2 = 165;

    public static final int DEFAULTX_BUTTON1 = 190;
    public static final int DEFAULTX_BUTTON2 = 350;

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
