package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import javax.swing.*;

import businessLogicLayer.ControllerCialde;
import dataAccessLayer.rowdatapkg.CialdeEntry;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties;
import presentationLayer.LaTazzaApplication;
import businessLogicLayer.commandPkg.RegistraRifornimentoCommand;

import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties.*;
import static businessLogicLayer.ObserverSubscriptionType.CIALDELIST;

public class RegistraRifornimento extends AbstractPanel {


	private JLabel labelTitolo;
	private JLabel labelTipoCialde;
	private JLabel labelQuantita;
    private JComboBox<String> tipoCialdeMenu;
    private JFormattedTextField textFieldQuantita;
    private JButton buttonConferma;
    private JButton buttonAnnulla;


    public RegistraRifornimento() {

		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		RegRifornimentoProperties.initRegistraRifornimentoPanel(this);
		add(labelTitolo=RegRifornimentoProperties.createAndInitJLabelTitolo());
        add(labelTipoCialde=RegRifornimentoProperties.createAndInitJLabelTipoCialde());
        add(tipoCialdeMenu=RegRifornimentoProperties.createAndInitTipoCialdeMenu());
        add(labelQuantita=RegRifornimentoProperties.createAndInitJLabelQuantita());
        add(textFieldQuantita=RegRifornimentoProperties.createAndInitJTextFieldQuantita());
        add(buttonConferma=RegRifornimentoProperties.createAndInitJButtonConferma());
        add(buttonAnnulla=RegRifornimentoProperties.createAndInitJButtonAnnulla());
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
        LaTazzaApplication.backEndInvoker.addObserver(CIALDELIST,this);

    }

    private void conferma(){
        String quantità= textFieldQuantita.getText().replaceAll("\\'","");
        String tipocialda=(String)(tipoCialdeMenu.getSelectedItem());

        if(quantità.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Inserire una quantità per il rifornimento", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(quantità.contains(".")){
            JOptionPane.showMessageDialog(null,
                    "Inserire una quantià intera", "alert", JOptionPane.ERROR_MESSAGE);
            textFieldQuantita.setValue(null);
            return;
        }

        if( tipocialda==null){
            System.err.println("Errore :nessuna cialda");
            JOptionPane.showMessageDialog(null,
                    "Cialda selezionata non valida", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        RegistraRifornimentoCommand command=new RegistraRifornimentoCommand(
                tipocialda,
                Integer.valueOf(quantità),
                LaTazzaApplication.backEndInvoker
        );
        if(!LaTazzaApplication.backEndInvoker.executeCommand(command)) {
            System.err.println("Impossibile registrare il rifornimento");
            JOptionPane.showMessageDialog(null,
                    "Impossibile registrare rifornimento", "alert", JOptionPane.ERROR_MESSAGE);
        }else
            System.out.println("Rifornimento avvenuto con successo");
        annulla();

    }


    private void annulla()
    {
        tipoCialdeMenu.setSelectedIndex(0);
        textFieldQuantita.setValue(null);
    }

    public void setTipoCialdeMenu(List<CialdeEntry> lista){
        tipoCialdeMenu.removeAllItems();
        for(CialdeEntry i:lista){
            this.tipoCialdeMenu.addItem(i.getTipo());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg ==CIALDELIST){
            setTipoCialdeMenu(((ControllerCialde)o).getCialdeEntryList());
        }
    }
}
