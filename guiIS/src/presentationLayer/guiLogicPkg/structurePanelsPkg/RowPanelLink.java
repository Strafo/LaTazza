package presentationLayer.guiLogicPkg.structurePanelsPkg;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import static presentationLayer.guiConfig.structurePanelsPropertiesPkg.RowPanelProperties.*;

public class RowPanelLink{

	private JLabel icon=new JLabel();
	private JPanel button=new JPanel();
	private JLabel buttonLabel;
	private MouseListener mouseListener;
	private ImageIcon iconWhite;



	//Constructor
	//create a panel that has got a label, that describes the link, and create another label to contain the imageIcon of link

	public RowPanelLink(String buttonLabel,int button_x, int icon_x, int y, ImageIcon iconWhite) {
		this.iconWhite=iconWhite;
		this.buttonLabel = new JLabel(buttonLabel);
		
		button.setBackground(null);
		button.setLayout(null);
		button.setOpaque(false);
		button.setBounds(button_x, y, DEFAULTWIDTH_BUTTON, DEFAULTHEIGHT_BUTTON);

		this.buttonLabel.setBounds(DEFAULTX_BUTTONLABEL, DEFAULTY_BUTTONLABEL, DEFAULTWIDTH_BUTTONLABEL, DEFAULTHEIGHT_BUTTONLABEL);
		this.buttonLabel.setFont(DEFAULTFONT);
        this.buttonLabel.setForeground(Color.white);

		button.add(this.buttonLabel);
		icon.setBounds(icon_x, y, DEFAULTWIDTH_ICON, DEFAULTHEIGHT_ICON);
		icon.setIcon(iconWhite);
	}


	public void setMouseListener(MouseAdapter mouseAdapter){
		this.mouseListener=mouseAdapter;
	}

    /**
     *
     */
	public void setLinesW() {
		button.setBorder(new MatteBorder(1, 1, 1, 0,  Color.WHITE));
	}


    /**
     *
     */
	public void setLinesB() {
		button.setBorder(new MatteBorder(0, 0, 1, 0,  Color.white));
	}


	public JPanel getButton() {
		return button;
	}
	
	public JLabel getIcon() {
		return icon;
	}



	
}
