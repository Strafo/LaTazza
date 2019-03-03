package guiLogicPkg.contentsPanelsPkg;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import static guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties.*;




public class RegistraVendite extends AbstractPanel {



	
	private JComboBox<String> tipoCialdeMenu;
	private JComboBox<String> nomePersonaleMenu;
	
	private String[] tipoCialde = new String[]{"vuoto","Arabica","Decaffeinato","Espresso"};
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
	
	private JTextField textFieldQuantita;
	private JTextField textFieldNomeCliente;
	
	private JButton buttonConferma;
	private JButton buttonAnnulla;

	public RegistraVendite() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegVenditeProperties.initRegistraVenditePanel(this);
		
		labelRegVendite = new JLabel("Registra vendita cialde");
		labelRegVendite.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegVendite.setBounds(27, 27, 500, 50);
		labelRegVendite.setIcon(ResourcesClassLoader.getIconVenditaB32());
		add(labelRegVendite);
		
		tipoCialdeMenu = new JComboBox<String>();
		addItems(tipoCialde,tipoCialdeMenu);
		tipoCialdeMenu.setBounds(65, 268, 170, 27);
		add(tipoCialdeMenu);
		
		nomePersonaleMenu = new JComboBox<String>();
		nomePersonaleMenu.setBounds(65, 168, 170, 27);
		addItems(nomePersonale,nomePersonaleMenu);
		add(nomePersonaleMenu);
		
		labelNomePersonale = new JLabel("Nome Personale");
		labelNomePersonale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNomePersonale.setBounds(65, 140, 100, 16);
		add(labelNomePersonale);
		
		labelTipoCialde = new JLabel("Tipo cialde");
		labelTipoCialde.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTipoCialde.setBounds(65, 240, 100, 16);
		add(labelTipoCialde);
		
		textFieldQuantita = new JTextField();
		textFieldQuantita.setBounds(367, 267, 170, 26);
		add(textFieldQuantita);
		textFieldQuantita.setColumns(10);
		
		labelQuantita = new JLabel("Quantita'");
		labelQuantita.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelQuantita.setBounds(367, 240, 100, 16);
		add(labelQuantita);
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBounds(367, 167, 170, 26);
		add(textFieldNomeCliente);
		
		labelNomeCliente = new JLabel("Nome cliente");
		labelNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNomeCliente.setBounds(367, 140, 100, 16);
		add(labelNomeCliente);
		
		labelOppure = new JLabel("oppure");
		labelOppure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelOppure.setBounds(279, 172, 40, 16);
		add(labelOppure);
		
		radioButtContanti = new JRadioButton("Contanti");
		radioButtContanti.setBounds(211, 325, 100, 23);
		add(radioButtContanti);
		
		radioButtACredito = new JRadioButton("A credito");
		radioButtACredito.setBounds(320, 325, 100, 23);
		add(radioButtACredito);
		
		labelPagamento = new JLabel("Pagamento:");
		labelPagamento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelPagamento.setBounds(123, 329, 76, 16);
		add(labelPagamento);
		
		buttonConferma = new JButton("Conferma");
		buttonConferma.setBounds(149, 393, 117, 29);
		add(buttonConferma);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.setBounds(320, 393, 117, 29);
		add(buttonAnnulla);
	}
	

	public void addItems(String[] stringa, JComboBox<String> cb) {
		for(String s: stringa) {
			cb.addItem(s);
		}
	}
	
}
