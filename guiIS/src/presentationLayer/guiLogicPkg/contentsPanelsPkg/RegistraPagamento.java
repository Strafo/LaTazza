package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import java.awt.Font;
import javax.swing.JLabel;
import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties;

import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties.*;


public class RegistraPagamento extends AbstractPanel {


	
	private JLabel labelRegPagamento;	

	public RegistraPagamento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegPagamentoProperties.initRegistraPagamentoPanel(this);
		
		labelRegPagamento = new JLabel("Registra pagamento");
		labelRegPagamento.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelRegPagamento.setBounds(27, 27, 500, 50);
		labelRegPagamento.setIcon(ResourcesClassLoader.getIconPagamentoB32());
		add(labelRegPagamento);
	}

}
