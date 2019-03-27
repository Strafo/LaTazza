package guiLogicPkg.contentsPanelsPkg;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import guiConfig.MyJLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties;

import static guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties.*;


public class RegistraPagamento extends AbstractPanel {

    private JButton buttonConferma;
    private JButton buttonAnnulla;

    private JFormattedTextField textFieldAmmontare;
    private NumberFormatter formatterAmmontare;
    private NumberFormat formatAmmontare;

    private JLabel labelAmmontare= new JLabel();

    private String[] nomePersonale = new String[]{"vuoto","Gianna","Pippo","Pluto"};

	private final JComboBox<String> nomePersonaleMenu;

	private JLabel labelTitolo;
	private JLabel labelNomePersonale;

	public RegistraPagamento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegPagamentoProperties.initRegistraPagamentoPanel(this);

		labelTitolo = new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconPagamentoB32());
		add(labelTitolo);

		labelNomePersonale = new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
		add(labelNomePersonale);

		nomePersonaleMenu = new JComboBox<>();
		addItems(nomePersonale,nomePersonaleMenu);
		nomePersonaleMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
		add(nomePersonaleMenu);

		labelAmmontare = new MyJLabel(DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1+DEFAULT_GAP_TXT,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
		add(labelAmmontare);

		formatAmmontare = NumberFormat.getInstance();
		formatterAmmontare = new NumberFormatter(formatAmmontare);
		formatterAmmontare.setValueClass(Integer.class);
		formatterAmmontare.setMinimum(0);
		formatterAmmontare.setMaximum(Integer.MAX_VALUE);
		formatterAmmontare.setAllowsInvalid(false);

		textFieldAmmontare = new JFormattedTextField(formatterAmmontare);
		textFieldAmmontare.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2+DEFAULT_GAP_TXT,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
		add(textFieldAmmontare);
		textFieldAmmontare.setColumns(10);

		buttonConferma = new JButton(DEFAULT_LABELDESCRIPTION[3]);
		buttonConferma.setBounds(DEFAULTX_BUTTON1,DEFAULTY_RIGA1+DEFAULT_GAP_BUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
		add(buttonConferma);

		buttonAnnulla = new JButton(DEFAULT_LABELDESCRIPTION[4]);
		buttonAnnulla.setBounds(DEFAULTX_BUTTON2,DEFAULTY_RIGA1+DEFAULT_GAP_BUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
		add(buttonAnnulla);
		buttonAnnulla.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						annulla();
					}
				}
		);
	}

	public void annulla()
    {
        nomePersonaleMenu.setSelectedIndex(0);
        textFieldAmmontare.setValue(null);
    }

}
