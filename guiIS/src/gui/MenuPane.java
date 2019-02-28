package gui;

import java.awt.Color;

import javax.swing.JPanel;

import guiConfig.KGradientPanel;
import guiConfig.MenuPaneProperties;
import guiConfig.MyClassLoader;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MenuPane extends KGradientPanel {

    private static final long serialVersionUID = 1L;

    private enum LinkName{
        LINKSTATO,
        LINKREGVENDITE,
        LINKREGPAGAMENTO,
        LINKREGRIFORNIMENTO,
        LINKGESTIONEPERSONALE
    }


	
	private static final Color CAPPUCCINO = new Color(155, 109, 80);
	private static final Color CAFFE = new Color(106, 59, 35);
	
	private JPanel panelSeparator;
	private JLabel labelTitolo;
	private JLabel labelIconaTazza;
	


	private Map linkSet;
	
	static private final int DEFAULTX_BUTTON = 50;
	static private final int DEFAULTX_ICON = 22;
	static private final int DEFAULTY= 133; 
	static private final int DEFAULT_GAP = 60;
	
	static private final int DEFAULTX_TITOLO = 69;
	static private final int DEFAULTY_TITOLO = 69;
	static private final int DEFAULT_WIDTH_TITOLO = 115;
	static private final int DEFAULT_HEIGHT_TITOLO = 26;
	
	static private final int DEFAULTX_ICONATITOLO = 14;
	static private final int DEFAULTY_ICONATITOLO = 45;
	static private final int DEFAULT_WIDTH_ICONATITOLO = 50;
	static private final int DEFAULT_HEIGHT_ICONATITOLO = 50;
	
	static private final int DEFAULTX_SEPARATOR = 20;
	static private final int DEFAULTY_SEPARATOR = 31;
	static private final int DEFAULT_WIDTH_SEPARATOR = 178;
	static private final int DEFAULT_HEIGHT_SEPARATOR = 2;

	//create the Menu Panel that contains five links
	public MenuPane(LaTazzaFrame frame) {
		
		MenuPaneProperties menuPaneProperties = new MenuPaneProperties();
		setBounds(menuPaneProperties.getX(), menuPaneProperties.getY(), menuPaneProperties.getWidth(), menuPaneProperties.getHeight());
		setLayout(null);
		setkEndColor(CAFFE);
		setkStartColor(CAPPUCCINO);
		//setkGradientFocus(menuPaneProperties.getGradient());
		
		panelSeparator = new JPanel();
		panelSeparator.setBounds(DEFAULTX_SEPARATOR, DEFAULTY_SEPARATOR, DEFAULT_WIDTH_SEPARATOR, DEFAULT_HEIGHT_SEPARATOR);
		panelSeparator.setBackground(Color.white);
		add(panelSeparator);
		
		labelTitolo = new JLabel("LaTazza");
		labelTitolo.setFont(new Font("Tahoma", Font.BOLD, 25));
		labelTitolo.setBounds(DEFAULTX_TITOLO, DEFAULTY_TITOLO, DEFAULT_WIDTH_TITOLO, DEFAULT_HEIGHT_TITOLO);
		add(labelTitolo);
		
		labelIconaTazza = new JLabel();
		labelIconaTazza.setBounds(DEFAULTX_ICONATITOLO, DEFAULTY_ICONATITOLO, DEFAULT_WIDTH_ICONATITOLO, DEFAULT_HEIGHT_ICONATITOLO);
		labelIconaTazza.setIcon(MyClassLoader.getIconTazza());
		add(labelIconaTazza);



		this.linkSet=new HashMap<LinkName,RowPanelLink>();
        for(LinkName i:LinkName.values()){
            linkSet.put(i,//todo check return value
                    new RowPanelLink("label", DEFAULTX_BUTTON, DEFAULTX_ICON,DEFAULTY,MyClassLoader.getIconStatoW25(), MyClassLoader.getIconStatoB25())

            );//todo trovare un modo per passare labels "Stato","Registra Vendita Cialde","Registra pagamento","Registra rifornimento","Getsione perosnale"

        }
        RowPanelLink link;
        for(LinkName i:LinkName.values()){
            link= (RowPanelLink) linkSet.get(i);
            add(link.getButton());
            add(link.getIcon());
            if(i.equals(LinkName.LINKSTATO)){
                link.setLinesB();
            }else{
                link.setLinesB();
            }
        }



		
		//set the event on state link
		linkStato.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkStato(frame);
			}
		});
		//set the event on sale register link
		linkRegVendite.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkRegVendite(frame);
			}
		});
		//set the event on payment register link
		linkRegPagamento.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkRegPagamento(frame);
			}
		});
		//set the event on provision register link
		linkRegRifornimento.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkRegRifornimento(frame);
			}
		});
		//set the event on personal management link
		linkGestionePersonale.getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLinkGestionePersonale(frame);
			}
		});
	}


	private void setLink(LaTazzaFrame frame,LinkName lName){


	    for(LinkName i:LinkName.values()){

            if(i.equals(lName)){
                ((RowPanelLink)linkSet.get(i)).setLinesW();
                frame.setFrame(true);
            }else{
                ((RowPanelLink)linkSet.get(i)).setLinesB();
                frame.setFrame(false);
            }
        }

	}








}
