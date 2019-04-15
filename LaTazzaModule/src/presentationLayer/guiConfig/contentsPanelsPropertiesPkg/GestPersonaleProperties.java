package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.GestionePersonale;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import utils.MyJLabel;

import javax.swing.*;
import java.awt.*;

public class GestPersonaleProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Gestione personale";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=LaTazzaFrame.JPanelsNames.GESTIONEPERSONALEPANE;
    private static final String[] DEFAULT_LABELDESCRIPTION={"Aggiungi personale","Conferma","Rimuovi personale","Conferma"};

    private static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    private static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    private static final boolean DEFAULT_VISIBILITY=false;

    private static final int DEFAULTX_COLONNA1 = 80;
    private static final int DEFAULTX_COLONNA2 = 380;

    private static final int DEFAULTY_RIGA1  = 185;
    private static final int DEFAULTY_RIGA2  = 220;
    private static final int DEFAULTY_RIGA3  = 290;

    private static final int GAP_BUTTON = 28;


    public static void initGestionePersonalePanel(GestionePersonale gestPersPanel)throws NullPointerException{
        gestPersPanel.setLayout(DEFAULT_LAYOUTMANAGER);
        gestPersPanel.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        gestPersPanel.setBackground(DEFAULT_BACKGROUNDCOLOR);
        gestPersPanel.setVisible(DEFAULT_VISIBILITY);
    }

    public static JLabel createAndInitLabelTitolo(){
        return new MyJLabel(DEFAULT_LINKDESCRIPTION,DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconGestioneB32());
    }
    public static JLabel createAndInitLabelAggiungi(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[0], DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconaAggPersonale());

    }
    public static JTextField createAndInitTextField(){
        JTextField t=new JTextField();
        t.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return t;
    }
    public static JLabel createAndInitLabelRimuovi(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[2], DEFAULT_FONT_DESCRIZIONI, DEFAULTX_COLONNA2
                ,DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconaRimPersonale());
    }

    public static JButton createAndInitButtonAggiungi(){
        JButton b= new JButton(DEFAULT_LABELDESCRIPTION[1]);
        b.setBounds(DEFAULTX_COLONNA1+GAP_BUTTON,DEFAULTY_RIGA3,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return b;
    }

    public static JButton createAndInitButtonRimuovi(){
        JButton b = new JButton(DEFAULT_LABELDESCRIPTION[3]);
        b.setBounds(DEFAULTX_COLONNA2+GAP_BUTTON,DEFAULTY_RIGA3,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        return b;
    }

    public static JComboBox<String> createAndInitJComboBoxNomePersonale(){
        JComboBox<String>comboBoxNomePersonale = new JComboBox<>();
        comboBoxNomePersonale.setBounds(DEFAULTX_COLONNA2,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        return comboBoxNomePersonale;
    }


    private GestPersonaleProperties(){}



}
