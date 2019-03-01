package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import guiConfig.MyClassLoader;
import guiConfig.PanelsProperties;

public class RegistraRifornimento extends AbstractPanel {


	private JLabel labelRegRifornimento;
	
	public RegistraRifornimento() {

		super();
		
		PanelsProperties pp = new PanelsProperties();
		setLayout(null);
		setBounds(pp.getX(),pp.getY(),pp.getWidth(),pp.getHeight());
		setBackground(Color.white);
		setVisible(false);
		
		labelRegRifornimento = new JLabel("Registra rifornimento scatole");
		labelRegRifornimento.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegRifornimento.setBounds(27, 27, 500, 50);
		labelRegRifornimento.setIcon(MyClassLoader.getIconRifornimentoB32());
		add(labelRegRifornimento);
	}
	
	public void setVisibleRifornimento(boolean bool) {
		setVisible(bool);
	}
}
