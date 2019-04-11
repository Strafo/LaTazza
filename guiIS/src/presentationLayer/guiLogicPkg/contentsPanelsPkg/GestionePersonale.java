package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import javax.swing.JLabel;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties;
import javax.swing.*;
import java.util.List;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.GestPersonaleProperties.*;

public class GestionePersonale extends AbstractPanel {


	private JLabel labelTitolo;
	private JLabel labelAggiungi;
	private JLabel labelRimuovi;
	private JTextField textFieldAggiungi= new JTextField();
	private JComboBox<String> comboBoxNomePersonale;
	private JButton buttonAggiugi;
	private JButton buttonRimuovi;


	/***TEMP DA RIMUOVERE**///todo da eliminare
    private String[] nomePersonale = new String[]{"vuoto","Gianna","Pippo","Pluto"};
    /**------------------**/

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
    }


    public void setComboBoxNomePersonale(List<Personale> lista){
        for(Personale i:lista){
            this.comboBoxNomePersonale.addItem(
                    i.getNome()+" "+i.getCognome()
            );
        }
    }

}
