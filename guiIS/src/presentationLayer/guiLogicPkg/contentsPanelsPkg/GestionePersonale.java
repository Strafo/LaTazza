package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import javax.swing.JLabel;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties;
import presentationLayer.guiLogicPkg.BackEndInvoker;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import javax.swing.*;
import java.util.List;
import java.util.Observable;

import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties.*;
import static presentationLayer.guiLogicPkg.BackEndInvoker.ObserverSubscriptionType.PERSONALELIST;

public class GestionePersonale extends AbstractPanel {


	private JLabel labelTitolo;
	private JLabel labelAggiungi;
	private JLabel labelRimuovi;
	private JTextField textFieldAggiungi;
	private JComboBox<String> comboBoxNomePersonale;
	private JButton buttonAggiugi;
	private JButton buttonRimuovi;


    public GestionePersonale() {
		
		//inizializza tutti i campi necessari
		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		GestPersonaleProperties.initGestionePersonalePanel(this);
		add(labelTitolo=GestPersonaleProperties.createAndInitLabelTitolo());
		add(labelAggiungi=GestPersonaleProperties.createAndInitLabelAggiungi());
        add(textFieldAggiungi=GestPersonaleProperties.createAndInitTextField());
        add(buttonAggiugi=GestPersonaleProperties.createAndInitButtonAggiungi());
        add(labelRimuovi=GestPersonaleProperties.createAndInitLabelRimuovi());
        add(comboBoxNomePersonale=GestPersonaleProperties.createAndInitJComboBoxNomePersonale());
        add(buttonRimuovi=GestPersonaleProperties.createAndInitButtonRimuovi());

        LaTazzaApplication.backEndInvoker.addObserver(PERSONALELIST,this);
    }


    public void setComboBoxNomePersonale(List<Personale> lista){
        comboBoxNomePersonale.removeAllItems();
        for(Personale i:lista){
            this.comboBoxNomePersonale.addItem(
                    i.getNome()+" "+i.getCognome()
            );
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //this.setComboBoxNomePersonale(LaTazzaApplication.controllerPersonale.getCopyList());
    }
}
