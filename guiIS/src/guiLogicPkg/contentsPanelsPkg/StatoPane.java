package guiLogicPkg.contentsPanelsPkg;

import utils.MyJLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties;

import javax.swing.*;
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



    public StatoPane() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);

        int i=0;

		StatoPaneProperties.initStatoPanel(this);

		labelTitolo = new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO, DEFAULT_WIDTH_LABELTITOLO, DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconStatoB32());
        add(labelTitolo);

		labelMagazzino = new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconMagazzino() );
        add(labelMagazzino);

        labelCassa = new MyJLabel(DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA2,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconCassa());
        add(labelCassa);

        labelDebitiPersonale = new MyJLabel(DEFAULT_LABELDESCRIPTION[3], DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA3,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconDebito());
        add(labelDebitiPersonale);

        labelSaldo = new MyJLabel(DEFAULT_LABELDESCRIPTION[4],DEFAULT_FONT_DESCRIZIONI2,DEFAULTX_COLONNA2,
                DEFAULTY_RIGA2,DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);
        add(labelSaldo);

        separator1.setBounds(DEFAULTX_COLONNA1,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR,DEFAULT_HEIGHT_SEPARATOR);
        separator1.setBackground(Color.BLACK);
        add(separator1);

        separator2.setBounds(DEFAULTX_COLONNA2,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR-DEFAULT_GAP_SP2,DEFAULT_HEIGHT_SEPARATOR);
        separator2.setBackground(Color.BLACK);
        add(separator2);

        separator3.setBounds(DEFAULTX_COLONNA3+DEFAULT_GAP_SP3_COLONNA,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR+DEFAULT_GAP_SP3,DEFAULT_HEIGHT_SEPARATOR);
        separator3.setBackground(Color.BLACK);
        add(separator3);

        for (String s : tipoCialde)
        {
            MyJLabel lb= new MyJLabel(s+": ",DEFAULT_FONT_DESCRIZIONI2,
                    DEFAULTX_COLONNA1, DEFAULTY_RIGA2+DEFAULT_GAP_LABEL*i++,DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);
            add(lb);
        }

        JTextArea debitiPersonale = new JTextArea();
        debitiPersonale.setEditable(false);
        debitiPersonale.setLineWrap(true);
        debitiPersonale.setFont(DEFAULT_FONT_DESCRIZIONI2);
        debitiPersonale.setWrapStyleWord(true);

        for (String s : tipoCialde)
            debitiPersonale.append("\n "+s+"\n");

        JScrollPane scrollPane = new JScrollPane(debitiPersonale);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA2,DEFAULT_WIDTH_SCROLLPANE,DEFAULT_HEIGHT_SCROLLPANE);
        add(scrollPane);

        separator4.setBounds(DEFAULTX_COLONNA2-40,DEFAULTY_RIGA2,DEFAULT_HEIGHT_SEPARATOR,DEFAULT_HEIGHT_SCROLLPANE);
        separator4.setBackground(Color.BLACK);
        add(separator4);

        separator5.setBounds(DEFAULTX_COLONNA2+120,DEFAULTY_RIGA2,DEFAULT_HEIGHT_SEPARATOR,DEFAULT_HEIGHT_SCROLLPANE);
        separator5.setBackground(Color.BLACK);
        add(separator5);
	}

}
