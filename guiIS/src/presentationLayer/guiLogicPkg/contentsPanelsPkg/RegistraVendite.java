package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import javax.swing.*;

import backend.businessLogicLayer.ControllerCialde;
import backend.businessLogicLayer.ControllerPersonale;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import presentationLayer.guiLogicPkg.commandPkg.RegistraVenditaCommand;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegVenditeProperties.*;
import static presentationLayer.guiLogicPkg.ObserverSubscriptionType.CIALDELIST;
import static presentationLayer.guiLogicPkg.ObserverSubscriptionType.PERSONALELIST;


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

    private void conferma(){

        String[] nomeCognome;
        boolean isPersonale;
        if(!textFieldNomeCliente.getText().isEmpty()){
            nomeCognome=textFieldNomeCliente.getText().split(" ");
            isPersonale=false;
        }else{
            nomeCognome=((String)nomePersonaleMenu.getSelectedItem()).split(" ");//todo handle nullp
            isPersonale=true;
        }


        RegistraVenditaCommand command=new RegistraVenditaCommand(
                (String)(tipoCialdeMenu.getSelectedItem()),
                radioButtContanti.isSelected(),
                nomeCognome[0],
                nomeCognome[1],
                Integer.valueOf(textFieldQuantita.getText()),
                isPersonale,
                LaTazzaApplication.backEndInvoker
        );
        if(!LaTazzaApplication.backEndInvoker.executeCommand(command))//todo check return value
            System.err.println("Impossibile registrare vednita");
        else
            System.err.println("Vendita avvenuta con successo");

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
