package presentationLayer.guiConfig.contentsPanelsPropertiesPkg;

import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.contentsPanelsPkg.StatoPane;
import utils.MyJLabel;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import static presentationLayer.guiLogicPkg.LaTazzaFrame.JPanelsNames.STATOPANE;

public class StatoPaneProperties extends AbstractPanelProperties {

    public static final String DEFAULT_LINKDESCRIPTION="Stato";
    public static final LaTazzaFrame.JPanelsNames DEFAULT_PANELNAME=STATOPANE;
    private static final String[] DEFAULT_LABELDESCRIPTION={"Stato magazzino, cassa e debiti personale","Magazzino","Saldo cassa:","Debiti personale","0.0"};

    private static final Color DEFAULT_BACKGROUNDCOLOR=Color.WHITE;
    private static final LayoutManager DEFAULT_LAYOUTMANAGER=null;
    private static final boolean DEFAULT_VISIBILITY=true;

    public static final int DEFAULTX_COLONNA1 = 50;
    private static final int DEFAULTX_COLONNA3 = 320;

    public static final int DEFAULTY_RIGA1 = 100;
    private static final int DEFAULTY_RIGA3 = 365;

    public static final int DEFAULT_GAP_LABEL = 30;
    private static final int DEFAULT_GAP_LABEL2 = 130;
    private static final int DEFAULT_GAP_LABEL3 = 8;

    private static final int DEFAULT_HEIGHT_PANEL = 215;
    private static final int DEFAULT_WIDTH_PANEL = 250;

    private static final int DEFAULT_WIDTH_PANEL1 = 110;

    private static final int DEFAULT_WIDTH_PANEL2 = 118;



    public static void initStatoPanel(StatoPane sP)throws NullPointerException{
        sP.setLayout(DEFAULT_LAYOUTMANAGER);
        sP.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        sP.setBackground(DEFAULT_BACKGROUNDCOLOR);
        sP.setVisible(DEFAULT_VISIBILITY);
        sP.setLabelTitolo(new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO, DEFAULT_WIDTH_LABELTITOLO, DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconStatoB32()));



    }

    public static JPanel createAndInitPanelMagazzino(){
        JPanel p=new JPanel();

        p.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA1,DEFAULT_WIDTH_PANEL1,DEFAULT_HEIGHT_SOTTOTITOTLO);
       // p.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        return p;
    }



    public static   JPanel createAndInitPanelCassa1(){
        javax.swing.JPanel panel=new JPanel();
        panel.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA3,DEFAULT_WIDTH_PANEL2,DEFAULT_HEIGHT_SOTTOTITOTLO);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));
        return panel;
    }

    public static JPanel createAndInitPanelDebiti1(){
        JPanel panelDebiti1 = new JPanel();
        panelDebiti1.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA1,DEFAULT_WIDTH_PANEL,DEFAULT_HEIGHT_SOTTOTITOTLO);
        panelDebiti1.setBackground(Color.WHITE);
        panelDebiti1.setLayout(null);
        panelDebiti1.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
        return panelDebiti1;
    }


    public static JTextArea createAndInitDebitiPersonaleTextArea(){
        JTextArea debitiPersonale = new JTextArea();
        debitiPersonale.setEditable(false);
        debitiPersonale.setLineWrap(true);
        debitiPersonale.setFont(DEFAULT_FONT_DESCRIZIONI2);
        debitiPersonale.setWrapStyleWord(true);
        return debitiPersonale;

    }

    //da provare
    public  static JTextArea createAndInitStatoMagazzinoTextArea(){
        JTextArea statoMagazzino= new JTextArea();
        statoMagazzino.setEditable(false);
        statoMagazzino.setLineWrap(true);
        statoMagazzino.setFont(DEFAULT_FONT_DESCRIZIONI2);
        statoMagazzino.setWrapStyleWord(true);
        return statoMagazzino;
    }

    public  static JScrollPane createAndInitScrollPaneStatoMagazzino(JTextArea textArea){
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA1+DEFAULT_HEIGHT_SOTTOTITOTLO,DEFAULT_WIDTH_PANEL,DEFAULT_HEIGHT_PANEL);
        return scrollPane;
    }

    public static JScrollPane createAndInitScrollPane(JTextArea textArea){
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA1+DEFAULT_HEIGHT_SOTTOTITOTLO,DEFAULT_WIDTH_PANEL,DEFAULT_HEIGHT_PANEL);
        return scrollPane;
    }

    public static MyJLabel createAndInitLabelMagazzino(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[1],
                DEFAULT_FONT_DESCRIZIONI,
                SwingConstants.LEFT,
                SwingConstants.CENTER,
                DEFAULT_WIDTH_SOTTOTITOLO,
                DEFAULT_HEIGHT_SOTTOTITOTLO,
                ResourcesClassLoader.getIconMagazzino());
    }



    public static MyJLabel createAndInitLabelSaldo(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[4],
                DEFAULT_FONT_DESCRIZIONI2,
                DEFAULTX_COLONNA1+DEFAULT_GAP_LABEL2,
                DEFAULTY_RIGA3+DEFAULT_GAP_LABEL3,
                DEFAULT_WIDTH_LABELDESCRIZIONE,
                DEFAULT_HEIGHT_LABELDESCRIZIONE,
                null);
    }

    public static MyJLabel createAndInitLabelCassa(){
        return new MyJLabel(DEFAULT_LABELDESCRIPTION[2],
                DEFAULT_FONT_DESCRIZIONI,
                SwingConstants.LEFT,
                SwingConstants.CENTER,
                DEFAULT_WIDTH_SOTTOTITOLO,
                DEFAULT_HEIGHT_SOTTOTITOTLO,
                ResourcesClassLoader.getIconCassa()
        );
    }

    public static MyJLabel createAndInitLabelDebitiPersonale(){
       return  new MyJLabel(DEFAULT_LABELDESCRIPTION[3]
               , DEFAULT_FONT_DESCRIZIONI
               ,SwingConstants.LEFT,
               SwingConstants.CENTER,
               DEFAULT_WIDTH_SOTTOTITOLO,
               DEFAULT_HEIGHT_SOTTOTITOTLO,
               ResourcesClassLoader.getIconDebito()
       );
    }

    public static MyJLabel createAndInitJLabelCialda(String nome,int i) {

        return new MyJLabel(nome,
                DEFAULT_FONT_DESCRIZIONI2,
                DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1+DEFAULT_HEIGHT_LABELTITOLO+DEFAULT_GAP_LABEL*i,
                DEFAULT_WIDTH_LABELDESCRIZIONE,
                DEFAULT_HEIGHT_LABELDESCRIZIONE,
                null);
    }



    private StatoPaneProperties(){}

}
