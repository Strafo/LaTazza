package gui;

import java.awt.Color;

import javax.swing.JPanel;

import guiConfig.KGradientPanel;
import guiConfig.MenuPaneProperties;
import guiConfig.MyClassLoader;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPane extends KGradientPanel {

	private static final long serialVersionUID = 1L;
	
	private static final Color CAPPUCCINO = new Color(155, 109, 80);
	private static final Color CAFFE = new Color(106, 59, 35);
	
	private JPanel panelSeparator;
	private JLabel labelTitolo;
	private JLabel labelIconaTazza;
	
	private RowPanelLink linkStato;
	private RowPanelLink linkRegVendite;
	private RowPanelLink linkRegPagamento;
	private RowPanelLink linkRegRifornimento;
	private RowPanelLink linkGestionePersonale;
	
	static private final int DEFAULTX_BUTTON = 50;
	static private final int DEFAULTX_ICON = 22;
	static private final int DEFAULTY= 133; 
	static private final int DEFAULT_GAP = 60;
	
	static private final int DEFAULTX_TITOLO = 69;
	static private final int DEFAULTY_TITOLO = 69;
	static private final int DEFAULT_WIDTH_TITOLO = 115;
	static private final int DEFAULT_HEIGHT_TITOLO = 26;
	
	static private final int DEFAULTX_ICONATITOLO = 14;
	static private final int DEFAULTY_ICONATITOLO = 45;
	static private final int DEFAULT_WIDTH_ICONATITOLO = 50;
	static private final int DEFAULT_HEIGHT_ICONATITOLO = 50;
	
	static private final int DEFAULTX_SEPARATOR = 20;
	static private final int DEFAULTY_SEPARATOR = 31;
	static private final int DEFAULT_WIDTH_SEPARATOR = 178;
	static private final int DEFAULT_HEIGHT_SEPARATOR = 2;

	//create the Menu Panel that contains five links
	public MenuPane(LaTazzaFrame frame) {
		
		MenuPaneProperties menuPaneProperties = new MenuPaneProperties();
		setBounds(menuPaneProperties.getX(), menuPaneProperties.getY(), menuPaneProperties.getWidth(), menuPaneProperties.getHeight());
		setLayout(null);
		setkEndColor(CAFFE);
		setkStartColor(CAPPUCCINO);
		//setkGradientFocus(menuPaneProperties.getGradient());
		
		panelSeparator = new JPanel();
		panelSeparator.setBounds(DEFAULTX_SEPARATOR, DEFAULTY_SEPARATOR, DEFAULT_WIDTH_SEPARATOR, DEFAULT_HEIGHT_SEPARATOR);
		panelSeparator.setBackground(Color.white);
		add(panelSeparator);
		
		labelTitolo = new JLabel("LaTazza");
		labelTitolo.setFont(new Font("Tahoma", Font.BOLD, 25));
		labelTitolo.setBounds(DEFAULTX_TITOLO, DEFAULTY_TITOLO, DEFAULT_WIDTH_TITOLO, DEFAULT_HEIGHT_TITOLO);
		add(labelTitolo);
		
		labelIconaTazza = new JLabel();
		labelIconaTazza.setBounds(DEFAULTX_ICONATITOLO, DEFAULTY_ICONATITOLO, DEFAULT_WIDTH_ICONATITOLO, DEFAULT_HEIGHT_ICONATITOLO);
		labelIconaTazza.setIcon(MyClassLoader.getIconTazza());
		add(labelIconaTazza);
		
		linkStato = new RowPanelLink("Stato", DEFAULTX_BUTTON, DEFAULTX_ICON,
											DEFAULTY,MyClassLoader.getIconStatoW25(), MyClassLoader.getIconStatoB25());
		add(linkStato.getButton());
		add(linkStato.getIcon());
		setLinesWhite(linkStato);
		
		linkRegVendite = new RowPanelLink("Registra vendita cialde", DEFAULTX_BUTTON, DEFAULTX_ICON
											,DEFAULTY+DEFAULT_GAP,MyClassLoader.getIconVenditeW25(), MyClassLoader.getIconVenditeB25());
		add(linkRegVendite.getButton());
		add(linkRegVendite.getIcon());
		setLinesBlack(linkRegVendite);
		
		linkRegPagamento = new RowPanelLink("Registra pagamento", DEFAULTX_BUTTON, DEFAULTX_ICON
											,DEFAULTY+(DEFAULT_GAP*2),MyClassLoader.getIconPagamentoW25(), MyClassLoader.getIconPagamentoB25());
		add(linkRegPagamento.getButton());
		add(linkRegPagamento.getIcon());
		setLinesBlack(linkRegPagamento);
		
		linkRegRifornimento = new RowPanelLink("Registra rifornimento", DEFAULTX_BUTTON, DEFAULTX_ICON
											,DEFAULTY+(DEFAULT_GAP*3) ,MyClassLoader.getIconRifornimentoW25(), MyClassLoader.getIconRifornimentoB25());
		add(linkRegRifornimento.getButton());
		add(linkRegRifornimento.getIcon());
		setLinesBlack(linkRegRifornimento);
		
		linkGestionePersonale = new RowPanelLink("Gestione personale", DEFAULTX_BUTTON, DEFAULTX_ICON
											,DEFAULTY+(DEFAULT_GAP*4) ,MyClassLoader.getIconGestionePW25(), MyClassLoader.getIconGestionePB25());
		add(linkGestionePersonale.getButton());
		add(linkGestionePersonale.getIcon());
		setLinesBlack(linkGestionePersonale);
		
		//set the event on state link
		linkStato.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkStato(frame);
			}
		});
		//set the event on sale register link
		linkRegVendite.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkRegVendite(frame);
			}
		});
		//set the event on payment register link
		linkRegPagamento.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkRegPagamento(frame);
			}
		});
		//set the event on provision register link
		linkRegRifornimento.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkRegRifornimento(frame);
			}
		});
		//set the event on personal management link
		linkGestionePersonale.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkGestionePersonale(frame);
			}
		});
	}
	//set to white lines of row panel link
	public void setLinesWhite(RowPanelLink rpl) {
		rpl.setLinesW();
	}
	//set to black lines of row panel link
	public void setLinesBlack(RowPanelLink rpl) {
		rpl.setLinesB();
	}
	//set the state page and it changes color of links
	public void setLinkStato(LaTazzaFrame frame) {
		linkStato.setLinesW();
		linkRegVendite.setLinesB();
		linkRegPagamento.setLinesB();
		linkRegRifornimento.setLinesB();
		linkGestionePersonale.setLinesB();
		frame.setVisibleStato(true);
		frame.setVisibleRegVendite(false);
		frame.setVisiblePagamento(false);
		frame.setVisibleRifornimento(false);
		frame.setVisibleGestione(false);
	}
	//set the sale register page and it changes color of links
	private void setLinkRegVendite(LaTazzaFrame frame) {
		linkStato.setLinesB();
		linkRegVendite.setLinesW();
		linkRegPagamento.setLinesB();
		linkRegRifornimento.setLinesB();
		linkGestionePersonale.setLinesB();
		frame.setVisibleStato(false);
		frame.setVisibleRegVendite(true);
		frame.setVisiblePagamento(false);
		frame.setVisibleRifornimento(false);
		frame.setVisibleGestione(false);
	}
	//set the payment register page and it changes color of links
	private void setLinkRegPagamento(LaTazzaFrame frame) {
		linkStato.setLinesB();
		linkRegVendite.setLinesB();
		linkRegPagamento.setLinesW();
		linkRegRifornimento.setLinesB();
		linkGestionePersonale.setLinesB();
		frame.setVisibleStato(false);
		frame.setVisibleRegVendite(false);
		frame.setVisiblePagamento(true);
		frame.setVisibleRifornimento(false);
		frame.setVisibleGestione(false);
	}
	//set the provision register page and it changes color of links
	private void setLinkRegRifornimento(LaTazzaFrame frame) {
		linkStato.setLinesB();
		linkRegVendite.setLinesB();
		linkRegPagamento.setLinesB();
		linkRegRifornimento.setLinesW();
		linkGestionePersonale.setLinesB();
		frame.setVisibleStato(false);
		frame.setVisibleRegVendite(false);
		frame.setVisiblePagamento(false);
		frame.setVisibleRifornimento(true);
		frame.setVisibleGestione(false);
	}
	//set the personal management page and it changes color of links
	private void setLinkGestionePersonale(LaTazzaFrame frame) {
		linkStato.setLinesB();
		linkRegVendite.setLinesB();
		linkRegPagamento.setLinesB();
		linkRegRifornimento.setLinesB();
		linkGestionePersonale.setLinesW();
		frame.setVisibleStato(false);
		frame.setVisibleRegVendite(false);
		frame.setVisiblePagamento(false);
		frame.setVisibleRifornimento(false);
		frame.setVisibleGestione(true);
	}
}
