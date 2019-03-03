package guiLogicPkg.contentsPanelsPkg;

import java.awt.Font;
import javax.swing.JLabel;
import guiConfig.ResourcesClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties;

import static guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties.*;

public class GestionePersonale extends AbstractPanel {



	private JLabel labelGestionePersonale;

	public GestionePersonale() {
		
		//PanelsProperties pp = new PanelsProperties();
		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		GestPersonaleProperties.initGestionePersonalePanel(this);

		labelGestionePersonale = new JLabel("Gestione personale");
		labelGestionePersonale.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelGestionePersonale.setBounds(27, 27, 500, 50);
		labelGestionePersonale.setIcon(ResourcesClassLoader.getIconGestioneB32());
		add(labelGestionePersonale);
	}
	
}
