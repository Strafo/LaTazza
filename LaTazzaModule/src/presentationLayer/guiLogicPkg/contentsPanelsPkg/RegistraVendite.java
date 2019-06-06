package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import javax.swing.*;

import businessLogicLayer.ControllerCialde;
import businessLogicLayer.ControllerPersonale;
import dataAccessLayer.rowdatapkg.CialdeEntry;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties;
import presentationLayer.LaTazzaApplication;
import businessLogicLayer.commandPkg.RegistraVenditaCommand;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties.*;
import static businessLogicLayer.ObserverSubscriptionType.CIALDELIST;
import static businessLogicLayer.ObserverSubscriptionType.PERSONALELIST;


public class RegistraVendite extends AbstractPanel {

    private JComboBox<String> tipoCialdeMenu;
	private JComboBox<String> nomePersonaleMenu;
	private JLabel labelRegVendite;
	private JLabel labelNomePersonale;
	private JLabel labelTipoCialde;
	private JLabel labelQuantita;
	private JLabel labelNomeCliente;
	private JLabel labelOppure;
	private JLabel labelPagamento;
	private JRadioButton radioButtContanti;
	private JRadioButton radioButtACredito;
	private JFormattedTextField textFieldQuantita;
	private JTextField textFieldNomeCliente;
	private JButton buttonConferma;
	private JButton buttonAnnulla;


	private Personale personale;

    public RegistraVendite() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegVenditeProperties.initRegistraVenditePanel(this);
		add(labelRegVendite=RegVenditeProperties.createAndInitJLabelRegVendite());
        add(labelNomePersonale=RegVenditeProperties.createAndInitJLabelPersonale());
        add(nomePersonaleMenu=RegVenditeProperties.createAndInitJComboBoxNomePersonaleMenu());
        add(labelTipoCialde=RegVenditeProperties.createAndInitJLabelTipoCialde());
		add(tipoCialdeMenu=RegVenditeProperties.createAndInitJComboBoxTipoCialdeMenu());
        add(labelNomeCliente=RegVenditeProperties.createAndInitJLabelNomeCliente());
        add(textFieldNomeCliente=RegVenditeProperties.createAndInitJTextFieldNomeCliente());
        add(labelOppure=RegVenditeProperties.createAndInitJLabelOppure());
        add(labelQuantita=RegVenditeProperties.createAndInitJLabelQuantita());
		add(textFieldQuantita=RegVenditeProperties.createAndInitJTextFieldQuantita());
        add(labelPagamento=RegVenditeProperties.createAndInitJLabelPagamento());
		add(radioButtContanti=RegVenditeProperties.createAndInitJRadioButtonContanti());
		add(radioButtACredito=RegVenditeProperties.createAndInitJRadioButtonACreadito());
		add(buttonConferma=RegVenditeProperties.createAndInitJButtonConferma());
		add(buttonAnnulla=RegVenditeProperties.createAndInitJButtonAnnulla());

        //LISTENER
		buttonAnnulla.addMouseListener(

                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        annulla();
                    }
                }
        );

        textFieldNomeCliente.addMouseListener(
                new MouseAdapter(){
                    @Override
                    public void mouseExited(MouseEvent e)
                    {
                        if(!textFieldNomeCliente.getText().isEmpty())
                        {
                            radioButtACredito.setEnabled(false);
                            radioButtACredito.setSelected(false);
                            radioButtContanti.setSelected(true);
                            if(nomePersonaleMenu.getItemCount()!=0) {
                                nomePersonaleMenu.setSelectedIndex(0);
                            }
                        }
                        else{
                            radioButtACredito.setEnabled(true);
                        }
                    }
                }
        );


        radioButtContanti.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e){
                        radioButtACredito.setSelected(false);
                    }
                }
        );

        radioButtACredito.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e){
                        radioButtContanti.setSelected(false);
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

        LaTazzaApplication.backEndInvoker.addObserver(CIALDELIST,this);
        LaTazzaApplication.backEndInvoker.addObserver(PERSONALELIST,this);

    }


    public void setComboBoxNomePersonaleMenu(List<Personale> lista){
        nomePersonaleMenu.removeAllItems();

        for(Personale i:lista){
            this.nomePersonaleMenu.addItem(
                    i.getNome()+" "+i.getCognome()
            );
        }
    }
    public void setComboBoxTipoCialdeMenu(List<CialdeEntry> lista){
        tipoCialdeMenu.removeAllItems();
        for(CialdeEntry i:lista){
            this.tipoCialdeMenu.addItem(
                    i.getTipo()
            );
        }
    }

    private void conferma() {
        String[] nomeCognome;
        boolean isPersonale;
        String qta=textFieldQuantita.getText().replaceAll("\\'","");
        String input,tipocialda=(String) (tipoCialdeMenu.getSelectedItem());

        //almeno uno dei duebottoni deve essere selezionato
        if (!radioButtContanti.isSelected() && !radioButtACredito.isSelected()){
            JOptionPane.showMessageDialog(null,
                    "Selezionare la modalità di pagamento", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if (!textFieldNomeCliente.getText().isEmpty()) {
            input=textFieldNomeCliente.getText();
            if(!input.matches("([a-zA-Z])* ([a-zA-Z])*")){
                System.err.println("Errore formattazione cliente");
                JOptionPane.showMessageDialog(null,
                        "Inserire nome e cognome del cliente separato da spazio", "warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            nomeCognome = input.split(" ");
            isPersonale = false;
        } else {
            if( nomePersonaleMenu.getSelectedItem()==null){
                System.err.println("Errore :nessun personale");
                JOptionPane.showMessageDialog(null,
                        "Personale selezionato non valido", "warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            nomeCognome = ((String) nomePersonaleMenu.getSelectedItem()).split(" ");
            isPersonale = true;
        }


        if(qta.contains(".")){
            JOptionPane.showMessageDialog(null,
                    "Inserire una quantità intera di cialde", "warning", JOptionPane.WARNING_MESSAGE);
            textFieldQuantita.setValue(null);
            return;
        }

        if( tipocialda==null){
            System.err.println("Errore :nessuna cialda");
            JOptionPane.showMessageDialog(null,
                    "Cialda selezionata non valida", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }


        RegistraVenditaCommand command = new RegistraVenditaCommand(
                tipocialda,
                radioButtContanti.isSelected(),
                nomeCognome[0],
                nomeCognome[1],
                Integer.valueOf(qta),
                isPersonale,
                LaTazzaApplication.backEndInvoker
        );

        switch (LaTazzaApplication.backEndInvoker.executeCommand(command)){
            case CIALDEINSUFF:
                JOptionPane.showMessageDialog(null,
                        "Impossibile registrare vendita, cialde insufficienti.", "alert", JOptionPane.WARNING_MESSAGE);
                break;
            case NOERROR:
                JOptionPane.showMessageDialog(null,
                        "Vendita registrata correttamente.", "success", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Impossibile registrare vendita", "alert", JOptionPane.ERROR_MESSAGE);
                break;

        }
        annulla();//refresha il campo contanti e il campo nomeutente
    }


	private void annulla()
    {
        tipoCialdeMenu.setSelectedIndex(0);
        textFieldNomeCliente.setText(null);
        textFieldQuantita.setValue(null);
        radioButtContanti.setSelected(false);
        radioButtACredito.setSelected(false);
        personale=null;
    }



    @Override
    public void update(Observable o, Object arg) {

        if(arg ==CIALDELIST){
            setComboBoxTipoCialdeMenu(((ControllerCialde)o).getCialdeEntryList());
        }else if(arg==PERSONALELIST){
            setComboBoxNomePersonaleMenu(((ControllerPersonale)o).getCopyList());
        }
    }
}
