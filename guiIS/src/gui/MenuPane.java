package gui;

import java.awt.Color;

import javax.swing.JPanel;

import guiConfig.KGradientPanel;
import guiConfig.MenuPaneProperties;
import guiConfig.MyClassLoader;
import utils.LaTazzaColors;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MenuPane extends KGradientPanel {

    private static final long serialVersionUID = 1L;


	private Map<LaTazzaFrame.JPanelsNames,RowPanelLink> linkSet=new HashMap<>();
    private final MenuPaneProperties menuPaneProperties=new MenuPaneProperties();
	private final LaTazzaFrame laTazzaFrame;
	
	private JPanel panelSeparator=new JPanel();
	private JLabel labelTitolo;
	private JLabel labelIconaTazza=new JLabel();
	





	//create the Menu Panel that contains five links
	public MenuPane(LaTazzaFrame laTazzaFrame) {

		this.laTazzaFrame = laTazzaFrame;

		this.setBounds(menuPaneProperties.getX(), menuPaneProperties.getY(), menuPaneProperties.getWidth(), menuPaneProperties.getHeight());
		this.setLayout(null);
		this.setkEndColor(LaTazzaColors.CAFFE);
		this.setkStartColor(LaTazzaColors.CAPPUCCINO);
		//setkGradientFocus(menuPaneProperties.getGradient());
		
		panelSeparator.setBounds(DEFAULTX_SEPARATOR, DEFAULTY_SEPARATOR, DEFAULT_WIDTH_SEPARATOR, DEFAULT_HEIGHT_SEPARATOR);
		panelSeparator.setBackground(Color.white);
		this.add(panelSeparator);
		
		labelTitolo = new JLabel("LaTazza");
		labelTitolo.setFont(new Font("Tahoma", Font.BOLD, 25));
		labelTitolo.setBounds(DEFAULTX_TITOLO, DEFAULTY_TITOLO, DEFAULT_WIDTH_TITOLO, DEFAULT_HEIGHT_TITOLO);
		this.add(labelTitolo);
		
		labelIconaTazza.setBounds(DEFAULTX_ICONATITOLO, DEFAULTY_ICONATITOLO, DEFAULT_WIDTH_ICONATITOLO, DEFAULT_HEIGHT_ICONATITOLO);
		labelIconaTazza.setIcon(MyClassLoader.getIconTazza());
		this.add(labelIconaTazza);



        for(LaTazzaFrame.JPanelsNames i:LaTazzaFrame.JPanelsNames.values()){
            linkSet.put(i,//todo check return value
                    new RowPanelLink("label", DEFAULTX_BUTTON, DEFAULTX_ICON,DEFAULTY,MyClassLoader.getIconStatoW25(), MyClassLoader.getIconStatoB25())

            );//todo trovare un modo per passare labels "Stato","Registra Vendita Cialde","Registra pagamento","Registra rifornimento","Getsione perosnale"

        }


        RowPanelLink linkTemp;

        for(LaTazzaFrame.JPanelsNames i:LaTazzaFrame.JPanelsNames.values()){
			linkTemp=linkSet.get(i);
            add(linkTemp.getButton());
            add(linkTemp.getIcon());
            if(i.equals(LaTazzaFrame.JPanelsNames.STATOPANE)){
				linkTemp.setLinesW();
            }else{
				linkTemp.setLinesB();
            }
        }



        for(LaTazzaFrame.JPanelsNames i:LaTazzaFrame.JPanelsNames.values()){
            linkSet.get(i).getButton().addMouseListener(
                    new MouseAdapter() {
                        private final LaTazzaFrame.JPanelsNames panelName=i;
                        @Override
                        public void mouseClicked(MouseEvent e){
                            setLink(panelName);
                        }
                    }

            );
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
                ((RowPanelLink)linkSet.get(i)).setLinesW();
                laTazzaFrame.setJPanelVisibleState(lName,true);
            }else{
                ((RowPanelLink)linkSet.get(i)).setLinesB();
                laTazzaFrame.setJPanelVisibleState(lName,false);
            }
        }

	}










    static private final int DEFAULTX_BUTTON = 50;
    static private final int DEFAULTX_ICON = 22;
    static private final int DEFAULTY= 133;
    static private final int DEFAULT_GAP = 60;

    //labelTitolo
    static private final int DEFAULTX_TITOLO = 69;
    static private final int DEFAULTY_TITOLO = 69;
    static private final int DEFAULT_WIDTH_TITOLO = 115;
    static private final int DEFAULT_HEIGHT_TITOLO = 26;

    //labelIconTazza
    static private final int DEFAULTX_ICONATITOLO = 14;
    static private final int DEFAULTY_ICONATITOLO = 45;
    static private final int DEFAULT_WIDTH_ICONATITOLO = 50;
    static private final int DEFAULT_HEIGHT_ICONATITOLO = 50;

    //panelSeparator
    static private final int DEFAULTX_SEPARATOR = 20;
    static private final int DEFAULTY_SEPARATOR = 31;
    static private final int DEFAULT_WIDTH_SEPARATOR = 178;
    static private final int DEFAULT_HEIGHT_SEPARATOR = 2;







}
