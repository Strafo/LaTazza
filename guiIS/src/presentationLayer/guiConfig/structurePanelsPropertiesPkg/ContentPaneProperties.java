package presentationLayer.guiConfig.structurePanelsPropertiesPkg;

import presentationLayer.guiLogicPkg.ContentPane;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ContentPaneProperties {

	public static final int DEFAULTX = 100;
	public static final int DEFAULTY = 100;
	private static final int DEFAULT_WIDTH = 810;
	private static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_BORDER = 0;
	private ContentPaneProperties(){}

	public static void initContentPane(ContentPane p){
		p.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
		p.setBorder(new EmptyBorder(DEFAULT_BORDER,DEFAULT_BORDER,DEFAULT_BORDER, DEFAULT_BORDER));
		p.setLayout(new BorderLayout(DEFAULT_BORDER, DEFAULT_BORDER));
		p.setLayout(null);
		p.setBackground(Color.WHITE);
	}
}
