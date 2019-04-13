package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import javax.swing.JLabel;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties.*;

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

        //LISTENER
        buttonAggiugi.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        aggiungiPersonale();
                    }
                }
        );

        buttonRimuovi.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        rimuoviPersonale();
                    }
                }
        );

        refreshContentPanel();
    }


    public void aggiungiPersonale()
    {

    }

    public void rimuoviPersonale()
    {

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
    public void refreshContentPanel() {
        this.setComboBoxNomePersonale(LaTazzaApplication.controllerPersonale.getCopyList());
    }
}
