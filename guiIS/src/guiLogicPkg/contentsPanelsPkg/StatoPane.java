package guiLogicPkg.contentsPanelsPkg;

import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties;
import utils.LaTazzaColors;

import javax.swing.*;
import java.awt.*;

import static guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties.*;


public class StatoPane extends AbstractPanel {

    private JLabel labelDebitiPersonale= new JLabel();
    private JLabel labelCassa= new JLabel();
    private JLabel labelTitolo= new JLabel();
    private JLabel labelMagazzino= new JLabel();

    private JPanel separator1= new JPanel();
    private JPanel separator2= new JPanel();
    private JPanel separator3= new JPanel();


	public StatoPane() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);

		StatoPaneProperties.initStatoPanel(this);

		creaLabel(labelTitolo,DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO, DEFAULT_WIDTH_LABELTITOLO, DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconStatoB32());

		creaLabel(labelMagazzino,DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconMagazzino() );

        creaLabel(labelCassa,DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA2,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconCassa());

        creaLabel(labelDebitiPersonale,DEFAULT_LABELDESCRIPTION[3], DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA3,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconDebito());

        creaPanel(separator1,DEFAULTX_COLONNA1,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR,DEFAULT_HEIGHT_SEPARATOR,Color.BLACK);

        creaPanel(separator2,DEFAULTX_COLONNA2,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR-DEFAULT_GAP_SP2,DEFAULT_HEIGHT_SEPARATOR,Color.BLACK);

        creaPanel(separator3,DEFAULTX_COLONNA3+22,DEFAULT_RIGA_SEPARATOR,DEFAULT_WIDTH_SEPARATOR+DEFAULT_GAP_SP3,DEFAULT_HEIGHT_SEPARATOR,Color.BLACK);
	}

}
