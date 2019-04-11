package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;
import guiConfig.contentsPanelsPropertiesPkg.AbstractPanelProperties;
import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.RegistraVendite;
import utils.MyJLabel;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class RegVenditeProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Registra vendita";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.REGVENDITEPANE;
    private static final String[] DEFAULT_LABELDESCRIPTION={"Registra vendita cialde","Nome personale","Tipo cialde",
                "Nome cliente","oppure","Quantità","Pagamento:","Contanti","A credito","Conferma","Annulla"};

    private static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    private static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    private static final boolean DEFAULT_VISIBILITY=false;

    private static final int DEFAULTX_COLONNA1 = 65;
    private static final int DEFAULTX_COLONNA2 = 279;
    private static final int DEFAULTX_COLONNA3 = 367;

    private static final int DEFAULTY_RIGA1 = 140;
    private static final int DEFAULTY_RIGA2 = 168;
    private static final int DEFAULTY_RIGA3 = 240;
    private static final int DEFAULTY_RIGA4 = 268;
    private static final int DEFAULTY_RIGA5 = 328;
    private static final int DEFAULT_RIGA6 = 393;

    private static final int DEFAULTX_BUTTON1 = 150;
    private static final int DEFAULTX_RADIO1 = 210;
    private static final int DEFAULTX_BUTTON2 = 320;

    private static final int DEFAULT_GAP_LABELOPPURE = 5;
    private static final int DEFAULT_GAP_LABELPAGAMENTO = 60;
    private static final int DEFAULT_GAP_RADIOBUTTON = 4;

    public static void initRegistraVenditePanel(RegistraVendite rVP)throws NullPointerException{
        rVP.setLayout(DEFAULT_LAYOUTMANAGER);
        rVP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        rVP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        rVP.setVisible(DEFAULT_VISIBILITY);

    }

    public static MyJLabel createAndInitJLabelQuantita(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[5],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA3,
                DEFAULTY_RIGA3,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);

    }

    public static MyJLabel createAndInitJLabelPagamento(){
        return  new MyJLabel(DEFAULT_LABELDESCRIPTION[6],DEFAULT_FONT_DESCRIZIONI2,DEFAULTX_COLONNA1+DEFAULT_GAP_LABELPAGAMENTO,
                DEFAULTY_RIGA5, DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);

    }

    public static MyJLabel createAndInitJLabelOppure(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[4],DEFAULT_FONT_DESCRIZIONI2,DEFAULTX_COLONNA2,
                DEFAULTY_RIGA2+DEFAULT_GAP_LABELOPPURE,DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);

    }

    public static MyJLabel createAndInitJLabelPersonale(){
        return  new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);

    }

    public static MyJLabel createAndInitJLabelRegVendite(){
        return  new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
              DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconVenditaB32());

    }

    public static MyJLabel createAndInitJLabelTipoCialde(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[2], DEFAULT_FONT_DESCRIZIONI, DEFAULTX_COLONNA1,
                        DEFAULTY_RIGA3, DEFAULT_WIDTH_SOTTOTITOLO, DEFAULT_HEIGHT_SOTTOTITOTLO, null);

    }

    public static JComboBox<String> createAndInitJComboBoxNomePersonaleMenu(){
        JComboBox<String>nomePersonaleMenu = new JComboBox<>();
        nomePersonaleMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return nomePersonaleMenu;
    }

    public static JComboBox<String> createAndInitJComboBoxTipoCialdeMenu(){
        JComboBox<String> tipoCialdeMenu = new JComboBox<>();
        tipoCialdeMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA4,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return tipoCialdeMenu;
    }

    public static MyJLabel createAndInitJLabelNomeCliente(){
        return   new MyJLabel(DEFAULT_LABELDESCRIPTION[3],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA3,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);

    }


    public static JFormattedTextField createAndInitJTextFieldQuantita(){
        NumberFormat formatQuantita = NumberFormat.getInstance();
        NumberFormatter formatterQuantita = new NumberFormatter(formatQuantita);
        formatterQuantita.setValueClass(Integer.class);
        formatterQuantita.setMinimum(0);
        formatterQuantita.setMaximum(Integer.MAX_VALUE);
        formatterQuantita.setAllowsInvalid(false);
        JFormattedTextField textFieldQuantita = new JFormattedTextField(formatterQuantita);
        textFieldQuantita.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA4,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return textFieldQuantita;
    }


    public static JTextField  createAndInitJTextFieldNomeCliente(){
        JTextField textFieldNomeCliente = new JTextField();
        textFieldNomeCliente.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return textFieldNomeCliente;
    }

    public static JRadioButton createAndInitJRadioButtonContanti(){
        JRadioButton radioButtContanti = new JRadioButton(DEFAULT_LABELDESCRIPTION[7]);
        radioButtContanti.setBounds(DEFAULTX_RADIO1, DEFAULTY_RIGA5-DEFAULT_GAP_RADIOBUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return radioButtContanti;
    }


    public static JRadioButton createAndInitJRadioButtonACreadito(){
        JRadioButton radioButtACredito = new JRadioButton(DEFAULT_LABELDESCRIPTION[8]);
        radioButtACredito.setBounds(DEFAULTX_BUTTON2, DEFAULTY_RIGA5-DEFAULT_GAP_RADIOBUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return radioButtACredito;
    }


    public static JButton  createAndInitJButtonAnnulla(){
        JButton buttonAnnulla = new JButton(DEFAULT_LABELDESCRIPTION[10]);
        buttonAnnulla.setBounds(DEFAULTX_BUTTON2,DEFAULT_RIGA6,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return buttonAnnulla;
    }

    public static JButton  createAndInitJButtonConferma() {
        JButton buttonConferma = new JButton(DEFAULT_LABELDESCRIPTION[9]);
        buttonConferma.setBounds(DEFAULTX_BUTTON1, DEFAULT_RIGA6, DEFAULT_WIDTH_BUTTON, DEFAULT_HEIGHT_BUTTON);
        return buttonConferma;
    }
    private RegVenditeProperties(){}
}
