package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import javax.swing.*;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties.*;
import static presentationLayer.guiLogicPkg.BackEndInvoker.ObserverSubscriptionType.PERSONALELIST;


public class RegistraPagamento extends AbstractPanel {

    private JButton buttonConferma;
    private JButton buttonAnnulla;
    private JComboBox<String> nomePersonaleMenu;
    private JFormattedTextField textFieldAmmontare;
    private JLabel labelTitolo;
    private JLabel labelNomePersonale;
    private JLabel labelAmmontare;


	public RegistraPagamento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegPagamentoProperties.initRegistraPagamentoPanel(this);
		add(labelTitolo=RegPagamentoProperties.createAndInitLabelTitolo());
		add(labelNomePersonale=RegPagamentoProperties.createAndInitLabelNomePersonale());
		add(nomePersonaleMenu=RegPagamentoProperties.createAndInitJComboBoxNomePersonaleMenu());
		add(labelAmmontare=RegPagamentoProperties.createAndInitLabelAmmonatare());
		add(textFieldAmmontare=RegPagamentoProperties.createAndInitJFormattedTextFieldAmmontare());
		add(buttonConferma=RegPagamentoProperties.createAndInitJButtonConferma());
		add(buttonAnnulla=RegPagamentoProperties.createAndInitJButtonAnnulla());


		buttonAnnulla.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						annulla();
					}
				}
		);
		LaTazzaApplication.backEndInvoker.addObserver(PERSONALELIST,this);
	}

    public void setComboBoxNomePersonale(List<Personale> lista){
		nomePersonaleMenu.removeAllItems();

		for(Personale i:lista){
            this.nomePersonaleMenu.addItem(
                    i.getNome()+" "+i.getCognome()
            );
        }
    }


	private void annulla()
    {
        nomePersonaleMenu.setSelectedIndex(0);
        textFieldAmmontare.setValue(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        //		this.setComboBoxNomePersonale(LaTazzaApplication.controllerPersonale.getCopyList());
    }
}
