package guiLogicPkg.contentsPanelsPkg;


import guiConfig.KGradientPanel;
import guiConfig.ResourcesClassLoader;
import guiLogicPkg.LaTazzaFrame;
import utils.LaTazzaColors;

import javax.swing.*;
import java.awt.*;


public abstract class AbstractPanel extends JPanel {

	private final long serialVersionUID;
    private final String LINKDESCRIPTION;
    private final LaTazzaFrame.JPanelsNames PANELNAME;

    protected AbstractPanel(long serialVersionUID, String linkdescription, LaTazzaFrame.JPanelsNames panelname) {
        this.serialVersionUID = serialVersionUID;
        LINKDESCRIPTION = linkdescription;
        PANELNAME = panelname;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLINKDESCRIPTION() {
        return LINKDESCRIPTION;
    }

    public LaTazzaFrame.JPanelsNames getPANELNAME() {
        return PANELNAME;
    }

    public void creaLabel(JLabel label, String descrizione,Font font, int x, int y, int width, int height, ImageIcon img)
    {
        label = new JLabel();
        label.setText(descrizione);
        label.setFont(font);
        label.setBounds(x,y,width,height);
        label.setIcon(img);
        this.add(label);
    }

    public void creaPanel(JPanel panel,int x, int y, int width, int height, Color color)
    {
        panel = new JPanel();
        panel.setBounds(x,y,width,height);
        panel.setBackground(color);
        this.add(panel);
    }

    public void creaJTextField(JTextField txt,int x,int y,int width,int height)
    {
        txt = new JTextField();
        txt.setBounds(x,y,width,height);
        this.add(txt);
    }

    public void addItems(String[] stringa, JComboBox<String> cb) {
        for(String s: stringa) {
            cb.addItem(s);
        }
    }
}
