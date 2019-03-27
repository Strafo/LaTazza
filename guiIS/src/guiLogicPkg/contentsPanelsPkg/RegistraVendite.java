package guiLogicPkg.contentsPanelsPkg;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.*;

import guiConfig.MyJLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties;

import javax.swing.text.NumberFormatter;

import static guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties.*;

public class RegistraVendite extends AbstractPanel {

    private JComboBox<String> tipoCialdeMenu;
	private JComboBox<String> nomePersonaleMenu;

    private String[] tipoCialde = new String[]{"vuoto","Arabica","Decaffeinato","Espresso","The'","The' limone","Cioccolata","Camomilla"};
    private String[] nomePersonale = new String[]{"vuoto","Gianna","Pippo","Pluto"};
	
	private JLabel labelRegVendite;
	private JLabel labelNomePersonale;
	private JLabel labelTipoCialde;
	private JLabel labelQuantita;
	private JLabel labelNomeCliente;
	private JLabel labelOppure;
	private JLabel labelPagamento;
	
	private JRadioButton radioButtContanti;
	private JRadioButton radioButtACredito;

    private final NumberFormat formatQuantita;
    private final NumberFormatter formatterQuantita;
	private JFormattedTextField textFieldQuantita;

	private JTextField textFieldNomeCliente;
	
	private JButton buttonConferma;
	private JButton buttonAnnulla;


	public RegistraVendite() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegVenditeProperties.initRegistraVenditePanel(this);

		labelRegVendite = new MyJLabel(DEFAULT_LABELDESCRIPTION[0],DEFAULT_FONT_TITOLO,DEFAULTX_LABELTITOLO,
                DEFAULTY_LABELTITOLO,DEFAULT_WIDTH_LABELTITOLO,DEFAULT_HEIGHT_LABELTITOLO,ResourcesClassLoader.getIconVenditaB32());
		add(labelRegVendite);

        labelNomePersonale = new MyJLabel(DEFAULT_LABELDESCRIPTION[1],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
        add(labelNomePersonale);

        nomePersonaleMenu = new JComboBox<String>();
        nomePersonaleMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        addItems(nomePersonale,nomePersonaleMenu);
        add(nomePersonaleMenu);

        labelTipoCialde = new MyJLabel(DEFAULT_LABELDESCRIPTION[2],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA1,
                DEFAULTY_RIGA3,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
        add(labelTipoCialde);

		tipoCialdeMenu = new JComboBox<String>();
		addItems(tipoCialde,tipoCialdeMenu);
		tipoCialdeMenu.setBounds(DEFAULTX_COLONNA1,DEFAULTY_RIGA4,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
		add(tipoCialdeMenu);

        labelNomeCliente = new MyJLabel(DEFAULT_LABELDESCRIPTION[3],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA3,
                DEFAULTY_RIGA1,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
        add(labelNomeCliente);

        textFieldNomeCliente = new JTextField();
        textFieldNomeCliente.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA2,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
        add(textFieldNomeCliente);
        textFieldNomeCliente.addMouseListener(
                new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        nomePersonaleMenu.setSelectedIndex(0);
                    }
                }
        );

        labelOppure = new MyJLabel(DEFAULT_LABELDESCRIPTION[4],DEFAULT_FONT_DESCRIZIONI2,DEFAULTX_COLONNA2,
                DEFAULTY_RIGA2+DEFAULT_GAP_LABELOPPURE,DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);
        add(labelOppure);

        labelQuantita = new MyJLabel(DEFAULT_LABELDESCRIPTION[5],DEFAULT_FONT_DESCRIZIONI,DEFAULTX_COLONNA3,
                DEFAULTY_RIGA3,DEFAULT_WIDTH_SOTTOTITOLO,DEFAULT_HEIGHT_SOTTOTITOTLO,null);
        add(labelQuantita);

        formatQuantita = NumberFormat.getInstance();
        formatterQuantita = new NumberFormatter(formatQuantita);
        formatterQuantita.setValueClass(Integer.class);
        formatterQuantita.setMinimum(0);
        formatterQuantita.setMaximum(Integer.MAX_VALUE);
        formatterQuantita.setAllowsInvalid(false);

        textFieldQuantita = new JFormattedTextField(formatterQuantita);
		textFieldQuantita.setBounds(DEFAULTX_COLONNA3,DEFAULTY_RIGA4,DEFAULT_WIDTH_FIELD,DEFAULT_HEIGHT_FIELD);
		add(textFieldQuantita);

        labelPagamento = new MyJLabel(DEFAULT_LABELDESCRIPTION[6],DEFAULT_FONT_DESCRIZIONI2,DEFAULTX_COLONNA1+DEFAULT_GAP_LABELPAGAMENTO,
                DEFAULTY_RIGA5, DEFAULT_WIDTH_LABELDESCRIZIONE,DEFAULT_HEIGHT_LABELDESCRIZIONE,null);
        add(labelPagamento);

		radioButtContanti = new JRadioButton(DEFAULT_LABELDESCRIPTION[7]);
		radioButtContanti.setBounds(DEFAULTX_RADIO1, DEFAULTY_RIGA5-DEFAULT_GAP_RADIOBUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
		add(radioButtContanti);
		radioButtContanti.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e){
                            radioButtACredito.setSelected(false);
                    }
                }
        );
		
		radioButtACredito = new JRadioButton(DEFAULT_LABELDESCRIPTION[8]);
		radioButtACredito.setBounds(DEFAULTX_BUTTON2, DEFAULTY_RIGA5-DEFAULT_GAP_RADIOBUTTON,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
		add(radioButtACredito);
        radioButtACredito.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e){
                        radioButtContanti.setSelected(false);
                    }
                }
        );
		
		buttonConferma = new JButton(DEFAULT_LABELDESCRIPTION[9]);
		buttonConferma.setBounds(DEFAULTX_BUTTON1,DEFAULT_RIGA6,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
		add(buttonConferma);
		
		buttonAnnulla = new JButton(DEFAULT_LABELDESCRIPTION[10]);
		buttonAnnulla.setBounds(DEFAULTX_BUTTON2,DEFAULT_RIGA6,DEFAULT_WIDTH_BUTTON,DEFAULT_HEIGHT_BUTTON);
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
        tipoCialdeMenu.setSelectedIndex(0);
        textFieldNomeCliente.setText(null);
        textFieldQuantita.setValue(null);
        radioButtContanti.setSelected(false);
        radioButtACredito.setSelected(false);
    }
}
