package guiLogicPkg.contentsPanelsPkg;

import guiConfig.KGradientPanel;
import utils.LaTazzaColors;
import utils.MyJLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties.*;


public class StatoPane extends AbstractPanel {

    private MyJLabel labelDebitiPersonale;
    private MyJLabel labelCassa;
    private MyJLabel labelTitolo;
    private MyJLabel labelMagazzino;
    private MyJLabel labelSaldo;

    private JPanel separator1= new JPanel();
    private JPanel separator2= new JPanel();
    private JPanel separator3= new JPanel();
    private JPanel separator4= new JPanel();
    private JPanel separator5= new JPanel();

    private String[] tipoCialde = new String[]{"Arabica","Decaffeinato","Espresso","Thè","Thè limone","Cioccolata","Camomilla"};
    private String[] debitiPersonaleS = new String[]{"Gabriele Armanino 40","Jacopo Dapueto 80","Simone Campisi 30","Andrea Straforini 50"};


    public StatoPane() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);

        int i=0;

		StatoPaneProperties.initStatoPanel(this);

		labelTitolo = new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO, DEFAULT_WIDTH_LABELTITOLO, DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconStatoB32());
        add(labelTitolo);

        JPanel panelMagazzino1 = new JPanel();
        panelMagazzino1.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA1,DEFAULT_WIDTH_PANEL,DEFAULT_HEIGHT_SOTTOTITOTLO);
        panelMagazzino1.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));
        panelMagazzino1.setBackground(Color.WHITE);
        panelMagazzino1.setLayout(null);
        add(panelMagazzino1);

		labelMagazzino = new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,SwingConstants.LEFT,
                SwingConstants.CENTER,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconMagazzino() );
        panelMagazzino1.add(labelMagazzino);

        KGradientPanel panelMagazzino2 = new KGradientPanel();
        panelMagazzino2.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA1+DEFAULT_HEIGHT_SOTTOTITOTLO,DEFAULT_WIDTH_PANEL,DEFAULT_HEIGHT_PANEL);
        panelMagazzino2.setkStartColor(Color.WHITE);
        panelMagazzino2.setkEndColor(LaTazzaColors.CAPPUCCINO);
        panelMagazzino2.setkGradientFocus(1000);
        panelMagazzino2.setLayout(null);
        add(panelMagazzino2);

        JPanel panelCassa1 = new JPanel();
        panelCassa1.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA3,DEFAULT_WIDTH_PANEL2,DEFAULT_HEIGHT_SOTTOTITOTLO);
        panelCassa1.setBackground(Color.WHITE);
        panelCassa1.setLayout(null);
        panelCassa1.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));
        add(panelCassa1);

        labelCassa = new MyJLabel(DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,SwingConstants.LEFT,
                SwingConstants.CENTER,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconCassa());
        panelCassa1.add(labelCassa);

        labelSaldo = new MyJLabel(DEFAULT_LABELDESCRIPTION[4],DEFAULT_FONT_DESCRIZIONI2,DEFAULTX_COLONNA1+DEFAULT_GAP_LABEL2,
                DEFAULTY_RIGA3+DEFAULT_GAP_LABEL3,DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);
        add(labelSaldo);

        JPanel panelDebiti1 = new JPanel();
        panelDebiti1.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA1,DEFAULT_WIDTH_PANEL,DEFAULT_HEIGHT_SOTTOTITOTLO);
        panelDebiti1.setBackground(Color.WHITE);
        panelDebiti1.setLayout(null);
        panelDebiti1.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
        add(panelDebiti1);

        labelDebitiPersonale = new MyJLabel(DEFAULT_LABELDESCRIPTION[3], DEFAULT_FONT_DESCRIZIONI,SwingConstants.LEFT,
                SwingConstants.CENTER,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconDebito());
        panelDebiti1.add(labelDebitiPersonale);

        for (String s : tipoCialde)
        {
            MyJLabel lb= new MyJLabel(s+": ",DEFAULT_FONT_DESCRIZIONI2,
                    SwingConstants.SOUTH, SwingConstants.PREVIOUS+DEFAULT_GAP_LABEL*i++,DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);
            panelMagazzino2.add(lb);
        }

        JTextArea debitiPersonale = new JTextArea();
        debitiPersonale.setEditable(false);
        debitiPersonale.setLineWrap(true);
        debitiPersonale.setFont(DEFAULT_FONT_DESCRIZIONI2);
        debitiPersonale.setWrapStyleWord(true);

        for (String s : debitiPersonaleS)
            debitiPersonale.append("\n "+s+"\n");

        JScrollPane scrollPane = new JScrollPane(debitiPersonale);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA1+DEFAULT_HEIGHT_SOTTOTITOTLO,DEFAULT_WIDTH_PANEL,DEFAULT_HEIGHT_PANEL);
        add(scrollPane);

	}


}
