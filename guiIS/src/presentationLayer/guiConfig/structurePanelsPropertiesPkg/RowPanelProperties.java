package presentationLayer.guiConfig.structurePanelsPropertiesPkg;

import presentationLayer.guiConfig.ResourcesClassLoader;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import presentationLayer.guiLogicPkg.structurePanelsPkg.RowPanelLink;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class RowPanelProperties {


    //rowpanel properties
    static public final int ROWPANEL_DEFAULTX_BUTTON = 50;
    static public final int ROWPANEL_DEFAULTX_ICON = 22;
    static public final int ROWPANEL_DEFAULTY = 160;
    static public final int ROWPANEL_DEFAULT_GAP = 60;

    //todo public final static
    static public final int DEFAULTWIDTH_BUTTON = 140;
    static public final int DEFAULTHEIGHT_BUTTON = 28;
    static public final int DEFAULTWIDTH_ICON = 30;
    static public final int DEFAULTHEIGHT_ICON = 28;
    static public final int DEFAULTWIDTH_BUTTONLABEL = 130;
    static public final int DEFAULTHEIGHT_BUTTONLABEL = 16;
    static public final int DEFAULTX_BUTTONLABEL = 6;
    public static final int DEFAULTY_BUTTONLABEL = 6;
    public final static Font DEFAULTFONT = new Font("Avenir", Font.PLAIN, 13);

    private static String[] nomeIconaLink = {"stato", "vendita", "pagamento", "rifornimento", "gestione"};


    private RowPanelProperties() {
    }

    private static RowPanelLink createRowPanel(int i, LaTazzaFrame.JPanelsNames panelName, LaTazzaFrame laTazzaFrame) {
        return new RowPanelLink(
                laTazzaFrame.getPanelByName(panelName).getLINKDESCRIPTION(),
                ROWPANEL_DEFAULTX_BUTTON, ROWPANEL_DEFAULTX_ICON, ROWPANEL_DEFAULTY + ROWPANEL_DEFAULT_GAP * i, ResourcesClassLoader.getIconW(nomeIconaLink[i]));
    }

    public static Map<LaTazzaFrame.JPanelsNames, RowPanelLink> getPanelsMap(LaTazzaFrame laTazzaFrame) {
        int i=0;
        Map<LaTazzaFrame.JPanelsNames, RowPanelLink> res=new EnumMap<>(LaTazzaFrame.JPanelsNames.class);
        for (LaTazzaFrame.JPanelsNames j : LaTazzaFrame.JPanelsNames.values()) {
            res.put(j,createRowPanel(i++,j,laTazzaFrame));
        }
        return res;
    }

}
