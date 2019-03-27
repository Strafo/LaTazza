package guiLogicPkg.contentsPanelsPkg;

import guiConfig.MyJLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties;
import utils.LaTazzaColors;

import javax.swing.*;
import java.awt.*;

import static guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties.*;


public class StatoPane extends AbstractPanel {

    private JLabel labelDebitiPersonale;
    private JLabel labelCassa;
    private JLabel labelTitolo;
    private JLabel labelMagazzino;

    private JPanel separator1= new JPanel();
    private JPanel separator2= new JPanel();
    private JPanel separator3= new JPanel();


	public StatoPane() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);

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

        separator1.setBounds(DEFAULTX_COLONNA1,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR,DEFAULT_HEIGHT_SEPARATOR);
        separator1.setBackground(Color.BLACK);
        add(separator1);

        separator2.setBounds(DEFAULTX_COLONNA2,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR-DEFAULT_GAP_SP2,DEFAULT_HEIGHT_SEPARATOR);
        separator2.setBackground(Color.BLACK);
        add(separator2);

        separator3.setBounds(DEFAULTX_COLONNA3+DEFAULT_GAP_SP3_COLONNA,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR+DEFAULT_GAP_SP3,DEFAULT_HEIGHT_SEPARATOR);
        separator3.setBackground(Color.BLACK);
        add(separator3);
	}

}
