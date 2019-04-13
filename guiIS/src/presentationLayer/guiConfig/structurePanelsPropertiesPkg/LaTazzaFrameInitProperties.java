package presentationLayer.guiConfig.structurePanelsPropertiesPkg;

import presentationLayer.guiConfig.ResourcesClassLoader;
import utils.MyJLabel;

import java.awt.*;

public class LaTazzaFrameInitProperties extends AbstractFrameProperties {

    static private final int DEFAULTX=300;
    static private final int DEFAULTY=300;
    static private final int DEFAULTWIDITH=400;
    static private final int DEFAULTHEIGHT=200;

    public static final Font DEFAULT_FONT_TITOLO = new Font("Avenir", Font.BOLD, 50);
    public static final Font DEFAULT_FONT_TITOLO_2= new Font("Avenir", Font.PLAIN, 46);

    public static final String titolo[]={"LA","TAZZA"};

    //labelTitolo
    public static final int DEFAULTX_TITOLO = 193;
    public static final int DEFAULTY_TITOLO = 87;
    public static final int DEFAULT_WIDTH_TITOLO = 250;
    public static final int DEFAULT_HEIGHT_TITOLO = 52;

    //labelTitolo2
    public static final int DEFAULTX_TITOLO2 = 142;
    public static final int DEFAULTY_TITOLO2 = 88;
    public static final int DEFAULT_WIDTH_TITOLO2 = 60;

    //labelIconTazza
    public static final int DEFAULTX_ICONATITOLO = 40;
    public static  final int DEFAULTY_ICONATITOLO = 30;
    public static final int DEFAULT_WIDTH_ICONATITOLO = 100;
    public static  final int DEFAULT_HEIGHT_ICONATITOLO = 100;


    public LaTazzaFrameInitProperties(int x,int y,int width,int height) throws IllegalArgumentException
    {
        if(x<=0||y<=0||width<=0||height<=0) throw new IllegalArgumentException("Dimensioni per il frame inferiori a 0.");
        this.x=x;
        this.height=height;
        this.y=y;
        this.width=width;
    }

    /**DEFAULT CONSTRUCTOR***/
    public LaTazzaFrameInitProperties(){
        this(DEFAULTX,DEFAULTY,DEFAULTWIDITH,DEFAULTHEIGHT);
    }



    public static MyJLabel createAndInitLabelTitolo2(){
        MyJLabel labelTitolo2=new MyJLabel(titolo[0],
                DEFAULT_FONT_TITOLO_2,
                DEFAULTX_TITOLO2,
                DEFAULTY_TITOLO2,
                DEFAULT_WIDTH_TITOLO2,
                DEFAULT_HEIGHT_TITOLO,
                null);
        labelTitolo2.setForeground(Color.WHITE);
        return labelTitolo2;
    }

    public static MyJLabel createAndInitLabelTitolo(){
        MyJLabel labelTitolo= new MyJLabel(titolo[1],
                DEFAULT_FONT_TITOLO,
                DEFAULTX_TITOLO,
                DEFAULTY_TITOLO,
                DEFAULT_WIDTH_TITOLO,
                DEFAULT_HEIGHT_TITOLO,
                null);
        labelTitolo.setForeground(Color.WHITE);
        return labelTitolo;
    }

    public static MyJLabel createAndInitLabeIcona(){
        return new MyJLabel(null,
                null,
                DEFAULTX_ICONATITOLO,
                DEFAULTY_ICONATITOLO,
                DEFAULT_WIDTH_ICONATITOLO,
                DEFAULT_HEIGHT_ICONATITOLO,
                ResourcesClassLoader.getIconaInit());
    }


}
