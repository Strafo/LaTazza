package guiLogicPkg.contentsPanelsPkg;

import java.awt.Font;

import javax.swing.JLabel;

import guiConfig.MyClassLoader;
import guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties;

import static guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties.*;

public class RegistraRifornimento extends AbstractPanel {


	private JLabel labelRegRifornimento;
	
	public RegistraRifornimento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegRifornimentoProperties.initRegistraRifornimentoPanel(this);
		
		labelRegRifornimento = new JLabel("Registra rifornimento scatole");
		labelRegRifornimento.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegRifornimento.setBounds(27, 27, 500, 50);
		labelRegRifornimento.setIcon(MyClassLoader.getIconRifornimentoB32());
		add(labelRegRifornimento);
	}
	
}
