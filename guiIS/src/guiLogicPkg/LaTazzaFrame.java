package guiLogicPkg;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import guiConfig.ResourcesClassLoader;
import guiConfig.structurePanelsPropertiesPkg.LaTazzaFrameProperties;
import guiLogicPkg.contentsPanelsPkg.*;
import guiLogicPkg.structurePanelsPkg.MenuPane;
import guiLogicPkg.structurePanelsPkg.TopBarPane;

public class LaTazzaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    private LaTazzaFrameProperties laTazzaFrameProperties=new LaTazzaFrameProperties();

    private ContentPane contentPane=new ContentPane(this);
    private MenuPane menuPane;
    private TopBarPane topBarPanePane=new TopBarPane(this);

    private List<Image> imgs= new ArrayList<>();

    public  enum JPanelsNames{
        STATOPANE,
        REGVENDITEPANE,
        REGPAGAMENTOPANE,
        REGRIFORNIMENTOPANE,
        GESTIONEPERSONALEPANE

    }

	private Map<JPanelsNames,AbstractPanel> jPanelsMap =new HashMap<>();

	/**
	 * Create the frame.
	 */
	public LaTazzaFrame() {

        if(System.getProperty("os.name").toLowerCase().contains("mac os x"))
            com.apple.eawt.Application.getApplication().setDockIconImage((ResourcesClassLoader.getIconTazzaBrown()).getImage());
        else {
            imgs.add(ResourcesClassLoader.getIconTazza16().getImage());
            imgs.add(ResourcesClassLoader.getIconTazza25().getImage());
            imgs.add(ResourcesClassLoader.getIconTazza32().getImage());
            imgs.add(ResourcesClassLoader.getIconTazza64().getImage());
            this.setIconImages(imgs);
        }
        
		this.setBounds(laTazzaFrameProperties.getX(), laTazzaFrameProperties.getY(), laTazzaFrameProperties.getWidth(), laTazzaFrameProperties.getHeight());
		this.setDefaultCloseOperation(laTazzaFrameProperties.getCloseOp());//todo checksetBounds(100, 100, 800, 500);
		this.setUndecorated(true);
        this.setContentPane(contentPane);
        this.setContentPane(contentPane);
        this.add(topBarPanePane);
        this.setTitle("LaTazza");

		//todo check return value
        //inizializza i pannelli e li aggiune alla jPanelsMap
		jPanelsMap.put(JPanelsNames.STATOPANE,new StatoPane());
		jPanelsMap.put(JPanelsNames.REGVENDITEPANE,new RegistraVendite());
		jPanelsMap.put(JPanelsNames.REGPAGAMENTOPANE,new RegistraPagamento());
		jPanelsMap.put(JPanelsNames.REGRIFORNIMENTOPANE,new RegistraRifornimento());
		jPanelsMap.put(JPanelsNames.GESTIONEPERSONALEPANE,new GestionePersonale());

        jPanelsMap.forEach((k,v)->this.add(v));//aggiunge tutti i pannelli al frame

        menuPane=new MenuPane(this);//va lasciato per ultimo perchè devono essere init prima i contentpanes
        this.add(menuPane);
	}


    /**
     * Questo metodo peremette di settare la visibilità del pannello con nome panelName.
     * @param panelName il nome del pannello da settare come visibile
     * @param panelVisibilityState lo stato (true/false) della visibilità del pannello
     */
	public void setJPanelVisibleState(JPanelsNames panelName,boolean panelVisibilityState){
	    jPanelsMap.get(panelName).setVisible(panelVisibilityState);
	    
    }

    public AbstractPanel getPanelByName(JPanelsNames name){
	    return jPanelsMap.get(name);
    }


    public void setLocationCenter() {

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - getWidth()) / 2;
        int iCoordY = (objDimension.height - getHeight()) / 2;
        setLocation(iCoordX, iCoordY);

    }

}
