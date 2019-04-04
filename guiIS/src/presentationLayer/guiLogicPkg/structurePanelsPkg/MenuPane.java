package presentationLayer.guiLogicPkg.structurePanelsPkg;

import java.awt.Color;
import javax.swing.*;

import presentationLayer.guiConfig.KGradientPanel;
import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiConfig.structurePanelsPropertiesPkg.MenuPaneProperties;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import utils.LaTazzaColors;
import utils.MyJLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import static presentationLayer.guiConfig.structurePanelsPropertiesPkg.MenuPaneProperties.*;
import static presentationLayer.guiConfig.structurePanelsPropertiesPkg.RowPanelProperties.*;


public class MenuPane extends KGradientPanel {

    private static final long serialVersionUID = 1L;
    private final LaTazzaFrame laTazzaFrame;
    private Map<LaTazzaFrame.JPanelsNames, RowPanelLink> linkMap=new HashMap<>();


    private JPanel panelSeparator=new JPanel();
    private MyJLabel labelTitolo;
    private MyJLabel labelTitolo2;
    private JLabel labelIconaTazza;

    public String[] nomeIconaLink={"stato","vendita","pagamento","rifornimento","gestione"};
    public String titolo2 = "LA";
    public String titolo = "TAZZA";


    //create the Menu Panel that contains five links
	public MenuPane(LaTazzaFrame laTazzaFrame) {
        int j=0,img=0;
		this.laTazzaFrame = laTazzaFrame;

		setBounds(MenuPaneProperties.DEFAULTX,MenuPaneProperties.DEFAULTY,MenuPaneProperties.DEFAULT_WIDTH,MenuPaneProperties.DEFAULT_HEIGHT);
		setLayout(null);
		setkEndColor(Color.BLACK);
		setkStartColor(LaTazzaColors.CAPPUCCINO);
		//setkGradientFocus(menuPaneProperties.getGradient());
		
        panelSeparator.setBounds(DEFAULTX_SEPARATOR, DEFAULTY_SEPARATOR, DEFAULT_WIDTH_SEPARATOR, DEFAULT_HEIGHT_SEPARATOR);
		panelSeparator.setBackground(Color.WHITE);

		labelTitolo = new MyJLabel(titolo,DEFAULT_FONT_TITOLO,DEFAULTX_TITOLO,DEFAULTY_TITOLO,DEFAULT_WIDTH_TITOLO,DEFAULT_HEIGHT_TITOLO,null);
		labelTitolo.setForeground(Color.WHITE);

        labelTitolo2 = new MyJLabel(titolo2,DEFAULT_FONT_TITOLO2,DEFAULTX_TITOLO2,DEFAULTY_TITOLO2,DEFAULT_WIDTH_TITOLO2,DEFAULT_HEIGHT_TITOLO,null);
        labelTitolo2.setForeground(Color.WHITE);

		labelIconaTazza = new MyJLabel(null,null,DEFAULTX_ICONATITOLO,
                DEFAULTY_ICONATITOLO, DEFAULT_WIDTH_ICONATITOLO, DEFAULT_HEIGHT_ICONATITOLO,ResourcesClassLoader.getIconTazza());

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));//+" Class path:"+System.getProperty("java.class.path"));

        this.add(panelSeparator);
        this.add(labelTitolo);
        this.add(labelIconaTazza);
        this.add(labelTitolo2);

        for(LaTazzaFrame.JPanelsNames i:LaTazzaFrame.JPanelsNames.values()){
            linkMap.put(i,//todo check return value
                    new RowPanelLink(
                            laTazzaFrame.getPanelByName(i).getLINKDESCRIPTION(),
                            ROWPANEL_DEFAULTX_BUTTON, ROWPANEL_DEFAULTX_ICON, ROWPANEL_DEFAULTY + ROWPANEL_DEFAULT_GAP * j++, ResourcesClassLoader.getIconW(nomeIconaLink[img++]))
            );
        }


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
     * @param lName il link da evidenziare
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


}
