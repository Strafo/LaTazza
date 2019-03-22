package guiLogicPkg.contentsPanelsPkg;

import java.awt.Font;
import javax.swing.*;

import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties;
import utils.LaTazzaColors;

import static guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties.*;

public class GestionePersonale extends AbstractPanel {

    private String[] nomePersonale = new String[]{"vuoto","Gianna","Pippo","Pluto"};

	private JLabel labelTitolo= new JLabel();
	private JLabel labelAggiungi= new JLabel();
	private JLabel labelRimuovi= new JLabel();

	private JTextField textFieldAggiungi= new JTextField();

	private JComboBox<String> comboBoxNomePersonale;

	private JButton buttonAggiugi= new JButton();
	private JButton buttonRimuovi= new JButton();


	public GestionePersonale() {
		
		//PanelsProperties pp = new PanelsProperties();
		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		GestPersonaleProperties.initGestionePersonalePanel(this);

		creaLabel(labelTitolo,DEFAULT_LINKDESCRIPTION,DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconGestioneB32());

        creaLabel(labelAggiungi,DEFAULT_LABELDESCRIPTION[0], DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconaAggPersonale());

        creaJTextField(textFieldAggiungi,DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);

        buttonAggiugi = new JButton(DEFAULT_LABELDESCRIPTION[1]);
        buttonAggiugi.setBounds(DEFAULTX_COLONNA1+GAP_BUTTON,DEFAULTY_RIGA3,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        add(buttonAggiugi);

        creaLabel(labelRimuovi, DEFAULT_LABELDESCRIPTION[2], DEFAULT_FONT_DESCRIZIONI, DEFAULTX_COLONNA2
                ,DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconaRimPersonale());

        comboBoxNomePersonale = new JComboBox<>();
        addItems(nomePersonale,comboBoxNomePersonale);
        comboBoxNomePersonale.setBounds(DEFAULTX_COLONNA2,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        add(comboBoxNomePersonale);

        buttonRimuovi = new JButton(DEFAULT_LABELDESCRIPTION[3]);
        buttonRimuovi.setBounds(DEFAULTX_COLONNA2+GAP_BUTTON,DEFAULTY_RIGA3,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        add(buttonRimuovi);
	}
	
}
