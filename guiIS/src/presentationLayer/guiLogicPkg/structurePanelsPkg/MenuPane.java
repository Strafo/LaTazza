package presentationLayer.guiLogicPkg.structurePanelsPkg;

import presentationLayer.guiConfig.KGradientPanel;
import presentationLayer.guiConfig.structurePanelsPropertiesPkg.RowPanelProperties;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import javax.swing.*;
import utils.MyJLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;


public class MenuPane extends KGradientPanel {

    private static final long serialVersionUID = 1L;
    private final LaTazzaFrame laTazzaFrame;
    private Map<LaTazzaFrame.JPanelsNames, RowPanelLink> linkMap;
    private JPanel panelSeparator=new JPanel();
    private MyJLabel labelTitolo;
    private MyJLabel labelTitolo2;
    private JLabel labelIconaTazza;


	public MenuPane(LaTazzaFrame laTazzaFrame) {
		this.laTazzaFrame = laTazzaFrame;

        linkMap=RowPanelProperties.getPanelsMap(laTazzaFrame);

        RowPanelLink linkTemp;

        for(LaTazzaFrame.JPanelsNames i:LaTazzaFrame.JPanelsNames.values()){

			linkTemp=linkMap.get(i);
            add(linkTemp.getButton());
            add(linkTemp.getIcon());
            linkTemp.getButton().addMouseListener(
                    new MouseAdapter() {
                        private final LaTazzaFrame.JPanelsNames panelName=i;
                        @Override
                        public void mouseClicked(MouseEvent e){
                            setLink(panelName);
                        }
                    }

            );

            if(i.equals(LaTazzaFrame.JPanelsNames.STATOPANE)){//il pannello iniziale è statoPane
                linkTemp.setLinesW();
            }else{
                linkTemp.setLinesB();
            }
        }

	}


    /**
     * Questo metodo permette di evidenziare di bianco il RowPanelLink con il nome corrsipondente ad lName e
     * colorare di nero tutti gli altri RowPanelLinks.
     * Rende inoltre visibile la "sottopagina" corrispondente al link.
     * @param lName il link da evidenziare di bianco
     */
	private void setLink(LaTazzaFrame.JPanelsNames lName){

	    for(LaTazzaFrame.JPanelsNames i:LaTazzaFrame.JPanelsNames.values()){

            if(i.equals(lName)){
                (linkMap.get(i)).setLinesW();
                laTazzaFrame.setJPanelVisibleState(i,true);
            }else{
                (linkMap.get(i)).setLinesB();
                laTazzaFrame.setJPanelVisibleState(i,false);
            }
        }

	}



    public JPanel getPanelSeparator() {
        return panelSeparator;
    }

    public MyJLabel getLabelTitolo() {
        return labelTitolo;
    }

    public void setLabelTitolo(MyJLabel labelTitolo){
	    this.labelTitolo=labelTitolo;
    }

    public MyJLabel getLabelTitolo2() {
        return labelTitolo2;
    }

    public void setLabelTitolo2(MyJLabel labelTitolo2) {
        this.labelTitolo2 = labelTitolo2;
    }

    public JLabel getLabelIconaTazza() {
        return labelIconaTazza;
    }

    public void setLabelIconaTazza(JLabel labelIconaTazza) {
        this.labelIconaTazza = labelIconaTazza;
    }


}
