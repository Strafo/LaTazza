package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import guiConfig.MyClassLoader;
import guiConfig.PanelsProperties;
import static guiConfig.PanelsPropertiesPkg.StatoPaneProperties.*;


public class RegistraPagamento extends AbstractPanel {


	
	private JLabel labelRegPagamento;	

	public RegistraPagamento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		
		PanelsProperties pp = new PanelsProperties();
		setLayout(null);
		setBounds(pp.getX(),pp.getY(),pp.getWidth(),pp.getHeight());
		setBackground(Color.white);
		setVisible(false);
		
		labelRegPagamento = new JLabel("Registra pagamento");
		labelRegPagamento.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegPagamento.setBounds(27, 27, 500, 50);
		labelRegPagamento.setIcon(MyClassLoader.getIconPagamentoB32());
		add(labelRegPagamento);
	}

}
