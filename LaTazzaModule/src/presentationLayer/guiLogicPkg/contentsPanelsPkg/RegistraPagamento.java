package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import javax.swing.*;

import businessLogicLayer.ControllerPersonale;
import businessLogicLayer.commandPkg.PagamentoDebitoCommand;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties;
import presentationLayer.LaTazzaApplication;
import utils.Euro;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegPagamentoProperties.*;
import static businessLogicLayer.ObserverSubscriptionType.PERSONALELIST;


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

		buttonConferma.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						conferma();
					}
				}
		);

		LaTazzaApplication.backEndInvoker.addObserver(PERSONALELIST,this);

	}


    private void setComboBoxNomePersonale(List<Personale> lista){
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

    private void conferma(){
		String personale= (String) nomePersonaleMenu.getSelectedItem();
		String[] nomeCognome= personale.split(" ");
		String[] euroCent=  textFieldAmmontare.getText().split("\\.");
		long euro= Long.valueOf(euroCent[0]);
		int  cent= Integer.valueOf(euroCent[1]);
		Euro importo=new Euro(euro, cent);
		PagamentoDebitoCommand command= new PagamentoDebitoCommand(nomeCognome[0],nomeCognome[1],importo,LaTazzaApplication.backEndInvoker);
		if(!LaTazzaApplication.backEndInvoker.executeCommand(command))
			System.err.println("Errore nel pagamento del Debito");
		else
			System.err.println("Debito Pagato Correttamente");

	}

    @Override
    public void update(Observable o, Object arg) {
        if(arg ==PERSONALELIST){
            setComboBoxNomePersonale(((ControllerPersonale)o).getCopyList());
        }
    }
}
