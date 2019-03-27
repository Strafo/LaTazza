package guiLogicPkg.contentsPanelsPkg;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import guiConfig.MyJLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties;

import static guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties.*;

public class RegistraRifornimento extends AbstractPanel {

    private String[] tipoCialde = new String[]{"vuoto","Arabica","Decaffeinato","Espresso","The'","The' limone","Cioccolata","Camomilla"};

	private JLabel labelTitolo;
	private JLabel labelTipoCialde;
	private JLabel labelQuantita;

    private JComboBox<String> tipoCialdeMenu;

    private final NumberFormat formatQuantita;
    private final NumberFormatter formatterQuantita;
    private JFormattedTextField textFieldQuantita;

    private JButton buttonConferma;
    private JButton buttonAnnulla;
	
	public RegistraRifornimento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegRifornimentoProperties.initRegistraRifornimentoPanel(this);

		labelTitolo = new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconRifornimentoB32());
        add(labelTitolo);

        labelTipoCialde = new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
        add(labelTipoCialde);

        tipoCialdeMenu = new JComboBox<String>();
        addItems(tipoCialde,tipoCialdeMenu);
        tipoCialdeMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        add(tipoCialdeMenu);

        labelQuantita = new MyJLabel(DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1+DEFAULT_GAP_TXT,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
        add(labelQuantita);

        formatQuantita = NumberFormat.getInstance();
        formatterQuantita = new NumberFormatter(formatQuantita);
        formatterQuantita.setValueClass(Integer.class);
        formatterQuantita.setMinimum(0);
        formatterQuantita.setMaximum(Integer.MAX_VALUE);
        formatterQuantita.setAllowsInvalid(false);

        textFieldQuantita = new JFormattedTextField(formatterQuantita);
        textFieldQuantita.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2+DEFAULT_GAP_TXT,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        add(textFieldQuantita);
        textFieldQuantita.setColumns(10);

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
        tipoCialdeMenu.setSelectedIndex(0);
        textFieldQuantita.setValue(null);
    }
	
}
