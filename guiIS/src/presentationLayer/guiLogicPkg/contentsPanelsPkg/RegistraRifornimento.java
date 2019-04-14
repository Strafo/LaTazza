package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import javax.swing.*;

import backend.businessLogicLayer.ControllerCialde;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.RegRifornimentoProperties.*;
import static presentationLayer.guiLogicPkg.ObserverSubscriptionType.CIALDELIST;

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
        LaTazzaApplication.backEndInvoker.addObserver(CIALDELIST,this);

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
