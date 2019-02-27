package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import guiConfig.LaTazzaFrameProperties;

public class LaTazzaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ContentPane contentPane;
	private MenuPane menuPane;
	private TopBar topBar;
	private Stato statoPane;
	private RegistraVendite regVenditePane;
	private RegistraPagamento regPagamento;
	private RegistraRifornimento regRifornimento;
	private GestionePersonale gestionePersonale;

	/**
	 * Create the frame.
	 */
	public LaTazzaFrame() {
		
		LaTazzaFrameProperties laTazzaFrameProperties = new LaTazzaFrameProperties();
		setBounds(laTazzaFrameProperties.getX(), laTazzaFrameProperties.getY(), laTazzaFrameProperties.getWidth(), laTazzaFrameProperties.getHeight());
		setDefaultCloseOperation(laTazzaFrameProperties.getCloseOp());//todo checksetBounds(100, 100, 800, 500);
		setUndecorated(true);
		contentPane = new ContentPane(this);
		setContentPane(contentPane);
		
		topBar = new TopBar(this);
		add(topBar);
		
		menuPane = new MenuPane(this);
		add(menuPane);
		
		statoPane = new Stato();
		add(statoPane);
		
		regVenditePane = new RegistraVendite();
		add(regVenditePane);
		
		regPagamento = new RegistraPagamento();
		add(regPagamento);
		
		regRifornimento = new RegistraRifornimento();
		add(regRifornimento);
		
		gestionePersonale = new GestionePersonale();
		add(gestionePersonale);
		

	}
	
	public void setLocationCenter() {
		
		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - getWidth()) / 2;
        int iCoordY = (objDimension.height - getHeight()) / 2;
        setLocation(iCoordX, iCoordY); 
		
	}
	
	public void setVisibleStato(boolean bool) {
		statoPane.setVisible(bool);
	}
	
	public void setVisibleRegVendite(boolean bool) {
		regVenditePane.setVisible(bool);
	}
	
	public void setVisiblePagamento(boolean bool) {
		regPagamento.setVisible(bool);
	}
	
	public void setVisibleRifornimento(boolean bool) {
		regRifornimento.setVisible(bool);
	}
	
	public void setVisibleGestione(boolean bool) {
		gestionePersonale.setVisible(bool);
	}
}
