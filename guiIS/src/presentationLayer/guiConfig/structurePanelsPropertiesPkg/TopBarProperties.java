package presentationLayer.guiConfig.structurePanelsPropertiesPkg;

import presentationLayer.guiLogicPkg.structurePanelsPkg.TopBarPane;

import static utils.LaTazzaColors.CAFFE;
import static utils.LaTazzaColors.CAPPUCCINO;

public class TopBarProperties {
	private static final int DEFAULTX = 0;
	private static final int DEFAULTY = 0;
	private static final int DEFAULT_WIDTH = 810;
	private static final int DEFAULT_HEIGHT = 31;

	private TopBarProperties(){}


	public static void initTopBarPane(TopBarPane t){
		 t.setBounds(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
		 t.setLayout(null);
		 t.setkEndColor(CAFFE);
		 t.setkStartColor(CAPPUCCINO);
	}

}
