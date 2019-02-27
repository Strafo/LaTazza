package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class RowPanelLink{
	
	private JLabel icon;
	private JPanel button;
	private JLabel buttonLabel;
	
	private ImageIcon iconWhite;
	private ImageIcon iconBlack;
	
	static private final int DEFAULTWIDTH_BUTTON = 140;
	static private final int DEFAULTHEIGHT_BUTTON = 28;

	
	static private final int DEFAULTWIDTH_ICON = 30;
	static private final int DEFAULTHEIGHT_ICON = 28;
	
	static private final int DEFAULTWIDTH_BUTTONLABEL = 130;
	static private final int DEFAULTHEIGHT_BUTTONLABEL = 16;
	static private final int DEFAULTX_BUTTONLABEL = 6;
	static private final int DEFAULTY_BUTTONLABEL = 6;


	//Constructor
	//create a panel that has got a label, that describes the link, and create another laber to contain the imageIcon of link
	public RowPanelLink(String buttonLabel,int button_x, int icon_x, int y, ImageIcon iconWhite, ImageIcon iconBlack) {
		
		this.iconWhite=iconWhite;
		this.iconBlack=iconBlack;
		this.buttonLabel = new JLabel(buttonLabel);
		
		button = new JPanel();
		button.setBackground(null);
		button.setLayout(null);
		button.setOpaque(false);
		button.setBounds(button_x, y, DEFAULTWIDTH_BUTTON, DEFAULTHEIGHT_BUTTON);

		this.buttonLabel.setBounds(DEFAULTX_BUTTONLABEL, DEFAULTY_BUTTONLABEL, DEFAULTWIDTH_BUTTONLABEL, DEFAULTHEIGHT_BUTTONLABEL);
		this.buttonLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.add(this.buttonLabel);
		
		icon = new JLabel("");
		icon.setBounds(icon_x, y, DEFAULTWIDTH_ICON, DEFAULTHEIGHT_ICON);
		
		setLinesB();
	}
	//set to white lines and foreground
	public void setLinesW() {
		buttonLabel.setForeground(Color.white);
		button.setBorder(new MatteBorder(1, 1, 1, 0, (Color) Color.WHITE));
		icon.setIcon(iconWhite);
	}
	//set to black lines and foreground
	public void setLinesB() {
		buttonLabel.setForeground(Color.black);
		button.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.black));
		icon.setIcon(iconBlack);
	}
	//getter methods
	public JPanel getButton() {
		return button;
	}
	
	public JLabel getIcon() {
		return icon;
	}
	
}
