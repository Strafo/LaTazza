package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import guiConfig.MyClassLoader;
import guiConfig.PanelsProperties;

public class GestionePersonale extends JPanel {


	private static final long serialVersionUID = 1L;
	public static final LaTazzaFrame.JPanelsNames panelName=LaTazzaFrame.JPanelsNames.GESTIONEPERSONALEPANE;

	private JLabel labelGestionePersonale;

	public GestionePersonale() {
		
		PanelsProperties pp = new PanelsProperties();
		setLayout(null);
		setBounds(pp.getX(),pp.getY(),pp.getWidth(),pp.getHeight());
		setBackground(Color.white);
		setVisible(false);
		
		labelGestionePersonale = new JLabel("Gestione personale");
		labelGestionePersonale.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelGestionePersonale.setBounds(27, 27, 500, 50);
		labelGestionePersonale.setIcon(MyClassLoader.getIconGestioneB32());
		add(labelGestionePersonale);
	}
	
	public void setVisibleGestione(boolean bool) {
		setVisible(bool);
	}
}
