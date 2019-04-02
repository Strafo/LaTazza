package guiLogicPkg.contentsPanelsPkg;

import javax.swing.*;

import utils.MyJLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties;

import static guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties.*;

public class GestionePersonale extends AbstractPanel {

    private String[] nomePersonale = new String[]{"vuoto","Gianna","Pippo","Pluto"};

	private JLabel labelTitolo;
	private JLabel labelAggiungi;
	private JLabel labelRimuovi;

	private JTextField textFieldAggiungi= new JTextField();

	private JComboBox<String> comboBoxNomePersonale;

	private JButton buttonAggiugi;
	private JButton buttonRimuovi;


	public GestionePersonale() {
		
		//PanelsProperties pp = new PanelsProperties();
		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		GestPersonaleProperties.initGestionePersonalePanel(this);

		labelTitolo = new MyJLabel(DEFAULT_LINKDESCRIPTION,DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconGestioneB32());
		add(labelTitolo);

        labelAggiungi = new MyJLabel(DEFAULT_LABELDESCRIPTION[0], DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconaAggPersonale());
        add(labelAggiungi);

        textFieldAggiungi.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        add(textFieldAggiungi);

        buttonAggiugi = new JButton(DEFAULT_LABELDESCRIPTION[1]);
        buttonAggiugi.setBounds(DEFAULTX_COLONNA1+GAP_BUTTON,DEFAULTY_RIGA3,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        add(buttonAggiugi);

        labelRimuovi = new MyJLabel(DEFAULT_LABELDESCRIPTION[2], DEFAULT_FONT_DESCRIZIONI, DEFAULTX_COLONNA2
                ,DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,ResourcesClassLoader.getIconaRimPersonale());
        add(labelRimuovi);

        comboBoxNomePersonale = new JComboBox<>();
        addItems(nomePersonale,comboBoxNomePersonale);
        comboBoxNomePersonale.setBounds(DEFAULTX_COLONNA2,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        add(comboBoxNomePersonale);

        buttonRimuovi = new JButton(DEFAULT_LABELDESCRIPTION[3]);
        buttonRimuovi.setBounds(DEFAULTX_COLONNA2+GAP_BUTTON,DEFAULTY_RIGA3,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
        add(buttonRimuovi);
	}
	
}
