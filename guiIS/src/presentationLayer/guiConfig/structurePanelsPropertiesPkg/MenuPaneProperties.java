package presentationLayer.guiConfig.structurePanelsPropertiesPkg;
import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiLogicPkg.structurePanelsPkg.MenuPane;
import utils.LaTazzaColors;
import utils.MyJLabel;

import java.awt.*;

public class MenuPaneProperties {
	

	 public static final int DEFAULTX = 0;
	 public static final int DEFAULTY = 0;
	 public static final int DEFAULT_WIDTH = 190;
	 public static final int DEFAULT_HEIGHT = 500;

	//labelTitolo
	private static final int DEFAULTX_TITOLO = 95;
	private static final int DEFAULTY_TITOLO = 69;
	private static final int DEFAULT_WIDTH_TITOLO = 110;
	private static final int DEFAULT_HEIGHT_TITOLO = 26;

	//labelTitolo2
	private static final int DEFAULTX_TITOLO2 = 69;
	private static final int DEFAULTY_TITOLO2 = 70;
	private static final int DEFAULT_WIDTH_TITOLO2 = 30;


	//labelIconTazza
	private static final int DEFAULTX_ICONATITOLO = 14;
	private static final int DEFAULTY_ICONATITOLO = 45;
	private static final int DEFAULT_WIDTH_ICONATITOLO = 50;
	private static  final int DEFAULT_HEIGHT_ICONATITOLO = 50;

	//panelSeparator
	private static  final int DEFAULTX_SEPARATOR = 20;
	private static  final int DEFAULTY_SEPARATOR = 31;
	private static  final int DEFAULT_WIDTH_SEPARATOR = 178;
	private static  final int DEFAULT_HEIGHT_SEPARATOR = 2;

	private static final Font DEFAULT_FONT_TITOLO = new Font("Avenir", Font.BOLD, 25);
	private static final Font DEFAULT_FONT_TITOLO2 = new Font("Avenir", Font.PLAIN, 22);
	private static final String DEFAULT_TITOLO = "TAZZA";
	private static final String DEFAULT_TITOLO_2 = "LA";




	private MenuPaneProperties(){}

	public static void initPanel(MenuPane panel){
		panel.setBounds(MenuPaneProperties.DEFAULTX,MenuPaneProperties.DEFAULTY,MenuPaneProperties.DEFAULT_WIDTH,MenuPaneProperties.DEFAULT_HEIGHT);
		panel.setLayout(null);
		panel.setkEndColor(Color.BLACK);
		panel.setkStartColor(LaTazzaColors.CAPPUCCINO);
		//setkGradientFocus(menuPaneProperties.getGradient());

		panel.getPanelSeparator().setBounds(DEFAULTX_SEPARATOR, DEFAULTY_SEPARATOR, DEFAULT_WIDTH_SEPARATOR, DEFAULT_HEIGHT_SEPARATOR);
		panel.getPanelSeparator().setBackground(Color.WHITE);

		panel.setLabelTitolo(new MyJLabel(DEFAULT_TITOLO,DEFAULT_FONT_TITOLO,DEFAULTX_TITOLO,DEFAULTY_TITOLO,DEFAULT_WIDTH_TITOLO,DEFAULT_HEIGHT_TITOLO,null));
		panel.getLabelTitolo().setForeground(Color.WHITE);

		panel.setLabelTitolo2(new MyJLabel(DEFAULT_TITOLO_2,DEFAULT_FONT_TITOLO2,DEFAULTX_TITOLO2,DEFAULTY_TITOLO2,DEFAULT_WIDTH_TITOLO2,DEFAULT_HEIGHT_TITOLO,null));
		panel.getLabelTitolo2().setForeground(Color.WHITE);

		panel.setLabelIconaTazza(new MyJLabel(null,null,DEFAULTX_ICONATITOLO,
				DEFAULTY_ICONATITOLO, DEFAULT_WIDTH_ICONATITOLO, DEFAULT_HEIGHT_ICONATITOLO,ResourcesClassLoader.getIconTazza()));

		panel.add(panel.getPanelSeparator());
		panel.add(panel.getLabelTitolo());
		panel.add(panel.getLabelIconaTazza());
		panel.add(panel.getLabelTitolo2());

	}


}
