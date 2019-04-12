package presentationLayer.guiLogicPkg.contentsPanelsPkg;

import backend.businessLogicLayer.ControllerCialde;
import backend.businessLogicLayer.ControllerDebito;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;
import utils.MyJLabel;
import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static presentationLayer.guiConfig.contentsPanelsPropertiesPkg.StatoPaneProperties.*;


public class StatoPane extends AbstractPanel {

    private MyJLabel labelDebitiPersonale;
    private MyJLabel labelCassa;
    private MyJLabel labelTitolo;
    private MyJLabel labelMagazzino;
    private MyJLabel labelSaldo;
    private JPanel panelMagazzino;
    private JPanel panelCassa1;
    private JPanel panelDebiti1;
    private JTextArea debitiPersonaleTextArea;
    private JScrollPane scrollPane;

    public StatoPane() {

        //inizializza tutti i campi necessari
		super(1L,DEFAULT_LINKDESCRIPTION,DEFAULT_PANELNAME);
		StatoPaneProperties.initStatoPanel(this);
        add(labelTitolo);
        add(panelMagazzino=StatoPaneProperties.createAndInitPanelMagazzino());
        panelMagazzino.add(labelMagazzino=StatoPaneProperties.createAndInitLabelMagazzino());
		add(panelCassa1=StatoPaneProperties.createAndInitPanelCassa1());
        panelCassa1.add(labelCassa=StatoPaneProperties.createAndInitLabelCassa());
        add(labelSaldo=StatoPaneProperties.createAndInitLabelSaldo());
        add(panelDebiti1=StatoPaneProperties.createAndInitPanelDebiti1());
        panelDebiti1.add(labelDebitiPersonale=StatoPaneProperties.createAndInitLabelDebitiPersonale());
        debitiPersonaleTextArea=StatoPaneProperties.createAndInitDebitiPersonaleTextArea();
        add(scrollPane=createAndInitScrollPane(debitiPersonaleTextArea));

        refreshContentPanel();
	}


    public void setCialdeList(Map<CialdeEntry,Integer> listaCialde){

        int i=0;
        for (CialdeEntry s : listaCialde.keySet())
        {
            add(StatoPaneProperties.createAndInitJLabelCialda(s.getTipo()+" "+ listaCialde.get(s),i++));
        }
    }

    public void setDebitiPersonaleTextArea(List<Personale> listaPersonale){
        debitiPersonaleTextArea.setText(null);
        for(Personale i:listaPersonale){
            debitiPersonaleTextArea.append(
                    i.getNome()+
                            " "+
                            i.getCognome()+
                            " "+
                            i.getImportoDebito().getEuro()+
                                    ","+
                            i.getImportoDebito().getCentesimi()+"\n"
            );
        }
    }


    public void setLabelTitolo(MyJLabel labelTitolo) {
        this.labelTitolo = labelTitolo;
    }


    @Override
    public void refreshContentPanel() {
        HashMap<Personale, Euro> map=ControllerDebito.esaminareDebitiPersonale();
        this.setDebitiPersonaleTextArea(new LinkedList<>(map.keySet()));
        this.setCialdeList(LaTazzaApplication.controllerContabilita.statoMagazzino());
    }
}
