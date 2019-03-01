package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import guiConfig.LaTazzaFrameProperties;

public class LaTazzaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    private LaTazzaFrameProperties laTazzaFrameProperties=new LaTazzaFrameProperties();

    private ContentPane contentPane=new ContentPane(this);
    private MenuPane menuPane=new MenuPane(this);
    private TopBarPane topBarPanePane =new TopBarPane(this);

    public  enum JPanelsNames{
        STATOPANE,
        REGVENDITEPANE,
        REGPAGAMENTOPANE,
        REGRIFORNIMENTOPANE,
        GESTIONEPERSONALEPANE

    }



	private Map<JPanelsNames,JPanel> jPanelsMap =new HashMap<>();

	/**
	 * Create the frame.
	 */
	public LaTazzaFrame() {
		
		this.setBounds(laTazzaFrameProperties.getX(), laTazzaFrameProperties.getY(), laTazzaFrameProperties.getWidth(), laTazzaFrameProperties.getHeight());
		this.setDefaultCloseOperation(laTazzaFrameProperties.getCloseOp());//todo checksetBounds(100, 100, 800, 500);
		this.setUndecorated(true);
        this.setContentPane(contentPane);
        this.add(contentPane);
        this.add(menuPane);
        this.add(topBarPanePane);


		//todo check return value
        //inizializza i pannelli e li aggiune alla jPanelsMap
		jPanelsMap.put(JPanelsNames.STATOPANE,new StatoPane());
		jPanelsMap.put(JPanelsNames.REGVENDITEPANE,new RegistraVendite());
		jPanelsMap.put(JPanelsNames.REGPAGAMENTOPANE,new RegistraPagamento());
		jPanelsMap.put(JPanelsNames.REGRIFORNIMENTOPANE,new RegistraRifornimento());
		jPanelsMap.put(JPanelsNames.GESTIONEPERSONALEPANE,new GestionePersonale());



        jPanelsMap.forEach((k,v)->this.add(v));//aggiunge tutti i pannelli al frame


	}


    /**
     * Questo metodo peremette di settare la visibilità del pannello con nome panelName.
     * @param panelName il nome del pannello da settare come visibile
     * @param panelVisibilityState lo stato (true/false) della visibilità del pannello
     */
	public void setJPanelVisibleState(JPanelsNames panelName,boolean panelVisibilityState){
	    jPanelsMap.get(panelName).setVisible(panelVisibilityState);
    }

    public JPanel getJPanelByName(JPanelsNames name){
	    return jPanelsMap.get(name);
    }


    public void setLocationCenter() {

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - getWidth()) / 2;
        int iCoordY = (objDimension.height - getHeight()) / 2;
        setLocation(iCoordX, iCoordY);

    }

}
