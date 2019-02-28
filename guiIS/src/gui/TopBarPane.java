package gui;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import guiConfig.KGradientPanel;
import guiConfig.MyClassLoader;
import guiConfig.TopBarProperties;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopBarPane extends KGradientPanel {

	private static final long serialVersionUID = 1L;
	
	private static final Color CAPPUCCINO = new Color(155, 109, 80);
	private static final Color CAFFE = new Color(106, 59, 35);
	
	private JLabel labelClose;
	private JLabel labelMin;
	
	private static final int DEFAULTX_LABELCLOSE = 770;
	private static final int DEFAULTX_LABELMIN = 745;
	
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
        
        labelClose = new JLabel();
        labelClose.setIcon(MyClassLoader.getIconCloseB());
        labelClose.setBounds(DEFAULTX_LABELCLOSE, DEFAULTY_LABEL, DEFAULT_WIDTH_LABEL, DEFAULT_HEIGHT_LABEL);
        add(labelClose);
        
        labelClose.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.exit(0);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		labelClose.setIcon(MyClassLoader.getIconCloseR());
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		labelClose.setIcon(MyClassLoader.getIconCloseB());
        	}
        });
        
        labelMin = new JLabel();
        labelMin.setIcon(MyClassLoader.getIconMinB());
        labelMin.setBounds(DEFAULTX_LABELMIN, DEFAULTY_LABEL, DEFAULT_WIDTH_LABEL, DEFAULT_HEIGHT_LABEL);
        add(labelMin);
        
        labelMin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setState(Frame.ICONIFIED);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		labelMin.setIcon(MyClassLoader.getIconMinW());
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		labelMin.setIcon(MyClassLoader.getIconMinB());
        	}
        });
		
	}
}
