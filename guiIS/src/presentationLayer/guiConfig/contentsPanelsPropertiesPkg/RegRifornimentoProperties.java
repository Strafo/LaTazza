package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import guiConfig.contentsPanelsPropertiesPkg.AbstractPanelProperties;
import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.RegistraRifornimento;
import utils.MyJLabel;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class RegRifornimentoProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Registra rifornimento";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.REGRIFORNIMENTOPANE;
    private static final String[] DEFAULT_LABELDESCRIPTION={"Registra rifornimento scatole","Tipo cialde","Quantità scatole","Conferma","Annulla"};

    private static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    private static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    private static final boolean DEFAULT_VISIBILITY=false;

    private static final int DEFAULTX_COLONNA1 = 235;

    private static final int DEFAULTY_RIGA1 = 140;
    private static final int DEFAULTY_RIGA2 = 165;

    private static final int DEFAULTX_BUTTON1 = 190;
    private static final int DEFAULTX_BUTTON2 = 350;


    public static void initRegistraRifornimentoPanel(RegistraRifornimento rRP)throws NullPointerException{
        rRP.setLayout(DEFAULT_LAYOUTMANAGER);
        rRP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        rRP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        rRP.setVisible(DEFAULT_VISIBILITY);

    }

    public static MyJLabel createAndInitJLabelTipoCialde(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);

    }

    public static MyJLabel createAndInitJLabelQuantita(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1+DEFAULT_GAP_TXT,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);

    }

    public static MyJLabel createAndInitJLabelTitolo(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconRifornimentoB32());

    }


    public static JButton createAndInitJButtonConferma(){
        JButton buttonConferma = new JButton(DEFAULT_LABELDESCRIPTION[3]);
        buttonConferma.setBounds(DEFAULTX_BUTTON1,DEFAULTY_RIGA1+DEFAULT_GAP_BUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return buttonConferma;
    }

    public static JButton createAndInitJButtonAnnulla(){
        JButton buttonAnnulla = new JButton(DEFAULT_LABELDESCRIPTION[4]);
        buttonAnnulla.setBounds(DEFAULTX_BUTTON2,DEFAULTY_RIGA1+DEFAULT_GAP_BUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return buttonAnnulla;
    }


    public static JFormattedTextField createAndInitJTextFieldQuantita(){

        NumberFormat formatQuantita = NumberFormat.getInstance();
        NumberFormatter formatterQuantita = new NumberFormatter(formatQuantita);
        formatterQuantita.setValueClass(Integer.class);
        formatterQuantita.setMinimum(0);
        formatterQuantita.setMaximum(Integer.MAX_VALUE);
        formatterQuantita.setAllowsInvalid(false);

        JFormattedTextField textFieldQuantita = new JFormattedTextField(formatterQuantita);
        textFieldQuantita.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2+DEFAULT_GAP_TXT,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        textFieldQuantita.setColumns(10);

        return textFieldQuantita;
    }

    public static JComboBox<String> createAndInitTipoCialdeMenu(){
        JComboBox<String> tipoCialdeMenu = new JComboBox<>();
        tipoCialdeMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return tipoCialdeMenu;
    }

    private RegRifornimentoProperties(){}
}
