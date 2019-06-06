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
        String[] nomeCognome=textFieldAggiungi.getText().split(" ");
        AggiungiPersonaleCommand aggPersc;

        aggPersc= new AggiungiPersonaleCommand(nomeCognome[0],nomeCognome[1], LaTazzaApplication.backEndInvoker);

        switch (LaTazzaApplication.backEndInvoker.executeCommand(aggPersc)){
            case PERSONALEGIAESISTENTE:
                JOptionPane.showMessageDialog(null,
                        "Impossibile aggiungere personale già esistente", "alert", JOptionPane.WARNING_MESSAGE);
                break;
            case NOERROR:
                JOptionPane.showMessageDialog(null,
                        "Personale Aggiunto Correttamente.", "success", JOptionPane.INFORMATION_MESSAGE);
                break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Impossibile aggiungere personale", "alert", JOptionPane.ERROR_MESSAGE);
                    break;

        }
    }

    public  void confermaLicenziaPersonale() {
        String personale = (String) comboBoxNomePersonale.getSelectedItem();
        String[] nomeCognome;
        if( personale==null){
            System.err.println("Errore :nessun personale");
            JOptionPane.showMessageDialog(null,
                    "Personale selezionato non valido", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        nomeCognome= personale.split(" ");
        LicenziaPersonaleCommand command = new LicenziaPersonaleCommand(nomeCognome[0], nomeCognome[1], LaTazzaApplication.backEndInvoker);

        switch (LaTazzaApplication.backEndInvoker.executeCommand(command)){
            case NOERROR:
                JOptionPane.showMessageDialog(null,
                        "Personale licenziato correttamente.", "success", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Impossibile licenziare personale", "alert", JOptionPane.ERROR_MESSAGE);
                break;

        }

    }
}
