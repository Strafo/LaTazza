package presentationLayer.guiLogicPkg;

import presentationLayer.guiConfig.structurePanelsPropertiesPkg.LaTazzaFrameInitProperties;
import utils.MyJLabel;

import javax.swing.*;


public class LaTazzaFrameInit extends JFrame {

    private MyJLabel labelTitolo;
    private MyJLabel labelTitolo2;
    private MyJLabel labelIconaTazza;


    public LaTazzaFrameInit() {

        add(labelTitolo=LaTazzaFrameInitProperties.createAndInitLabelTitolo());
        add(labelTitolo2=LaTazzaFrameInitProperties.createAndInitLabelTitolo2());
        add(labelIconaTazza=LaTazzaFrameInitProperties.createAndInitLabeIcona());
    }

}
