package guiLogicPkg.structurePanelsPkg;


import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import static guiConfig.structurePanelsPropertiesPkg.RowPanelProperties.*;

public class RowPanelLink{

	private JLabel icon=new JLabel();
	private JPanel button=new JPanel();
	private JLabel buttonLabel;
	
	private ImageIcon iconWhite;
	private ImageIcon iconBlack;

	//Constructor
	//create a panel that has got a label, that describes the link, and create another label to contain the imageIcon of link


	public RowPanelLink(String buttonLabel,int button_x, int icon_x, int y, ImageIcon iconWhite, ImageIcon iconBlack) {
		this.iconWhite=iconWhite;
		this.iconBlack=iconBlack;
		this.buttonLabel = new JLabel(buttonLabel);
		
		button.setBackground(null);
		button.setLayout(null);
		button.setOpaque(false);
		button.setBounds(button_x, y, DEFAULTWIDTH_BUTTON, DEFAULTHEIGHT_BUTTON);

		this.buttonLabel.setBounds(DEFAULTX_BUTTONLABEL, DEFAULTY_BUTTONLABEL, DEFAULTWIDTH_BUTTONLABEL, DEFAULTHEIGHT_BUTTONLABEL);
		this.buttonLabel.setFont(DEFAULTFONT);

		button.add(this.buttonLabel);
		icon.setBounds(icon_x, y, DEFAULTWIDTH_ICON, DEFAULTHEIGHT_ICON);
		
	}
	
    /**
     *
     */
	public void setLinesW() {
		buttonLabel.setForeground(Color.white);
		button.setBorder(new MatteBorder(1, 1, 1, 0,  Color.WHITE));
		icon.setIcon(iconWhite);
	}


    /**
     *
     */
	public void setLinesB() {
		buttonLabel.setForeground(Color.black);
		button.setBorder(new MatteBorder(0, 0, 1, 0,  Color.black));
		icon.setIcon(iconBlack);
	}


	public JPanel getButton() {
		return button;
	}
	
	public JLabel getIcon() {
		return icon;
	}


	
}
