package guiLogicPkg.contentsPanelsPkg;

import guiConfig.MyClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties;

import javax.swing.JLabel;
import java.awt.Font;

import static guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties.*;


public class StatoPane extends AbstractPanel {




	private JLabel labelStato;
	public StatoPane() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);

		StatoPaneProperties.initStatoPanel(this);

		labelStato = new JLabel("Stato magazzino, cassa e debiti personale");
		labelStato.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelStato.setBounds(DEFAULTX_LABELTITOLO, DEFAULTY_LABELTITOLO, DEFAULT_WIDTH_LABELTITOLO, DEFAULT_HEIGHT_LABELTITOLO);
		labelStato.setIcon(MyClassLoader.getIconStatoB32());
		add(labelStato);
	}





	
}
