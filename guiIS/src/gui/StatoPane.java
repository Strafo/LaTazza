package gui;

import java.awt.Color;

import javax.swing.JPanel;

import guiConfig.MyClassLoader;
import guiConfig.PanelsProperties;

import javax.swing.JLabel;
import java.awt.Font;


public class StatoPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final LaTazzaFrame.JPanelsNames panelName=LaTazzaFrame.JPanelsNames.STATOPANE;



	
	private JLabel labelStato;
	
	private static final int DEFAULTX_LABELTITOLO = 27;
	private static final int DEFAULTY_LABELTITOLO = 27;
	private static final int DEFAULT_WIDTH_LABELTITOLO = 500;
	private static final int DEFAULT_HEIGHT_LABELTITOLO = 50;

	public StatoPane() {

		
		PanelsProperties pp = new PanelsProperties();
		setLayout(null);
		setBounds(pp.getX(),pp.getY(),pp.getWidth(),pp.getHeight());
		setBackground(Color.white);
		setVisible(true);
		
		labelStato = new JLabel("Stato magazzino, cassa e debiti personale");
		labelStato.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelStato.setBounds(DEFAULTX_LABELTITOLO, DEFAULTY_LABELTITOLO, DEFAULT_WIDTH_LABELTITOLO, DEFAULT_HEIGHT_LABELTITOLO);
		labelStato.setIcon(MyClassLoader.getIconStatoB32());
		add(labelStato);
	}





	
}
