package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import guiConfig.MyClassLoader;
import guiConfig.PanelsProperties;

public class RegistraPagamento extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final LaTazzaFrame.JPanelsNames panelName=LaTazzaFrame.JPanelsNames.REGPAGAMENTOPANE;

	
	private JLabel labelRegPagamento;	

	public RegistraPagamento() {
		
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
	
	public void setVisiblePagamento(boolean bool) {
		setVisible(bool);
	}
}
