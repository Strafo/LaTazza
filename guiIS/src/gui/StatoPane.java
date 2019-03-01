package gui;

import java.awt.Color;

import guiConfig.MyClassLoader;
import guiConfig.PanelsProperties;

import javax.swing.JLabel;
import java.awt.Font;

import static StatoPaneProperties.*;


public class StatoPane extends AbstractPanel {




	


	private JLabel labelStato;
	public StatoPane() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);


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
