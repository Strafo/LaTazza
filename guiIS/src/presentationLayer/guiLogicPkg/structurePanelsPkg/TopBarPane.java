package presentationLayer.guiLogicPkg.structurePanelsPkg;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import presentationLayer.guiConfig.KGradientPanel;
import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiConfig.structurePanelsPropertiesPkg.TopBarProperties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static utils.LaTazzaColors.*;

public class TopBarPane extends KGradientPanel {

	private static final long serialVersionUID = 1L;

	
	private JLabel labelClose;
	private JLabel labelMin;

	private static final int DEFAULTX_LABELCLOSE = 780;
	private static final int DEFAULTX_LABELMIN = 755;

	private static final int DEFAULTY_LABEL = 3;
	private static final int DEFAULT_WIDTH_LABEL = 25;
	private static final int DEFAULT_HEIGHT_LABEL = 25;
	
	//Constructor
	//create the topbar that contains buttons for shutdown and minimize window
	public TopBarPane(JFrame frame) {
		
		TopBarProperties toPBarProperties = new TopBarProperties();
		setBounds(toPBarProperties.getX(),toPBarProperties.getY(),toPBarProperties.getWidth(), toPBarProperties.getHeight());
        setLayout(null);
        setkEndColor(CAFFE);
        setkStartColor(CAPPUCCINO);
        
        labelClose = new MyJLabel(null,null,DEFAULTX_LABELCLOSE,
				DEFAULTY_LABEL, DEFAULT_WIDTH_LABEL, DEFAULT_HEIGHT_LABEL,ResourcesClassLoader.getIconCloseW());
        add(labelClose);
        
        labelClose.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.exit(0);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		labelClose.setIcon(ResourcesClassLoader.getIconCloseR());
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		labelClose.setIcon(ResourcesClassLoader.getIconCloseW());
        	}
        });
        
        labelMin = new MyJLabel(null,null,DEFAULTX_LABELMIN,
				DEFAULTY_LABEL, DEFAULT_WIDTH_LABEL, DEFAULT_HEIGHT_LABEL, ResourcesClassLoader.getIconMinW());
        add(labelMin);

        labelMin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setState(Frame.ICONIFIED);
        	}
        });
		
	}
}
