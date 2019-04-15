package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import javax.swing.JLabel;

import businessLogicLayer.ControllerPersonale;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties;
import presentationLayer.LaTazzaApplication;
import businessLogicLayer.commandPkg.AggiungiPersonaleCommand;
import businessLogicLayer.commandPkg.LicenziaPersonaleCommand;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties.*;
import static businessLogicLayer.ObserverSubscriptionType.PERSONALELIST;

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

        buttonAggiugi.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        confermaAggiungiPersonale();
                    }
                }
        );

        buttonRimuovi.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        confermaLicenziaPersonale();;
                    }
                }
        );

        LaTazzaApplication.backEndInvoker.addObserver(PERSONALELIST,this);
    }


    private void setComboBoxNomePersonale(List<Personale> lista){
        comboBoxNomePersonale.removeAllItems();
        for(Personale i:lista){
            this.comboBoxNomePersonale.addItem(
                    i.getNome()+" "+i.getCognome()
            );
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg==PERSONALELIST){
            setComboBoxNomePersonale(((ControllerPersonale)o).getCopyList());
        }
    }

    public void confermaAggiungiPersonale() {
        String[] nomeCognome= textFieldAggiungi.getText().split(" ");
        AggiungiPersonaleCommand command= new AggiungiPersonaleCommand(nomeCognome[0],nomeCognome[1], LaTazzaApplication.backEndInvoker);
        if(!LaTazzaApplication.backEndInvoker.executeCommand(command))
            System.err.println("Errore nell'aggiunta del personale");
            else
        System.err.println("Personale aggiunto con successo");
    }

    public  void confermaLicenziaPersonale(){
        String personale= (String) comboBoxNomePersonale.getSelectedItem();
        String[] nomeCognome= personale.split(" ");
        LicenziaPersonaleCommand command= new LicenziaPersonaleCommand(nomeCognome[0],nomeCognome[1], LaTazzaApplication.backEndInvoker);
        if(!LaTazzaApplication.backEndInvoker.executeCommand(command))
            System.err.println("Errore nel Licenziamento del Personale");
        else
        System.err.println("Personale Licenziato");

    }
}
