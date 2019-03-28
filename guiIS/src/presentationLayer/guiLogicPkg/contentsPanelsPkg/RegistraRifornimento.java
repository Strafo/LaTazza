package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import java.awt.Font;

import javax.swing.JLabel;

import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties;

import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties.*;

public class RegistraRifornimento extends AbstractPanel {


	private JLabel labelRegRifornimento;
	
	public RegistraRifornimento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegRifornimentoProperties.initRegistraRifornimentoPanel(this);
		
		labelRegRifornimento = new JLabel("Registra rifornimento scatole");
		labelRegRifornimento.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegRifornimento.setBounds(27, 27, 500, 50);
		labelRegRifornimento.setIcon(ResourcesClassLoader.getIconRifornimentoB32());
		add(labelRegRifornimento);
	}
	
}
