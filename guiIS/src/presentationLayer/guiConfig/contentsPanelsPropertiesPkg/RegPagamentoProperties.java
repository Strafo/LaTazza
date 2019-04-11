package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import guiConfig.contentsPanelsPropertiesPkg.AbstractPanelProperties;
import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.RegistraPagamento;
import utils.MyJLabel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

import static presentationLayer.guiLogicPkg.LaTazzaFrame.JPanelsNames.REGPAGAMENTOPANE;

public  class RegPagamentoProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Registra pagamento";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=REGPAGAMENTOPANE;
    private static final String[] DEFAULT_LABELDESCRIPTION={"Registra pagamento","Nome personale","Ammontare","Conferma","Annulla"};

    private static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    private static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    private static final boolean DEFAULT_VISIBILITY=false;

    public static final int DEFAULTX_COLONNA1 = 235;

    private static final int DEFAULTY_RIGA1 = 140;
    public static final int DEFAULTY_RIGA2 = 165;

    private static final int DEFAULTX_BUTTON1 = 190;
    private static final int DEFAULTX_BUTTON2 = 350;

    public static void initRegistraPagamentoPanel(RegistraPagamento rPP)throws NullPointerException{
        rPP.setLayout(DEFAULT_LAYOUTMANAGER);
        rPP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        rPP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        rPP.setVisible(DEFAULT_VISIBILITY);

    }


    public static MyJLabel createAndInitLabelNomePersonale(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
    }

    public static MyJLabel createAndInitLabelTitolo(){
        return  new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconPagamentoB32());

    }

    public static JButton createAndInitJButtonAnnulla(){
        JButton b=  new JButton(DEFAULT_LABELDESCRIPTION[4]);
        b.setBounds(DEFAULTX_BUTTON2,DEFAULTY_RIGA1+DEFAULT_GAP_BUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return b;
    }

    public static JButton createAndInitJButtonConferma(){
        JButton b= new JButton(DEFAULT_LABELDESCRIPTION[3]);
        b.setBounds(DEFAULTX_BUTTON1,DEFAULTY_RIGA1+DEFAULT_GAP_BUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return b;
    }

    public static MyJLabel createAndInitLabelAmmonatare(){
        return  new MyJLabel(DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1+DEFAULT_GAP_TXT,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
    }

    public static JFormattedTextField createAndInitJFormattedTextFieldAmmontare(){
        NumberFormat formatAmmontare = NumberFormat.getInstance();
        NumberFormatter formatterAmmontare = new NumberFormatter(formatAmmontare);
        formatterAmmontare.setValueClass(Integer.class);
        formatterAmmontare.setMinimum(0);
        formatterAmmontare.setMaximum(Integer.MAX_VALUE);
        formatterAmmontare.setAllowsInvalid(false);
        JFormattedTextField textFieldAmmontare = new JFormattedTextField(formatterAmmontare);
        textFieldAmmontare.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2+DEFAULT_GAP_TXT,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        textFieldAmmontare.setColumns(10);
        return textFieldAmmontare;
    }

    public static JComboBox<String> createAndInitJComboBoxNomePersonaleMenu(){
        JComboBox<String>nomePersonaleMenu = new JComboBox<>();
        nomePersonaleMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return nomePersonaleMenu;
    }

    private RegPagamentoProperties(){}

}
