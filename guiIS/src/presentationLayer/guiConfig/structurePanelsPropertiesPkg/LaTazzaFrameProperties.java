package presentationLayer.guiConfig.structurePanelsPropertiesPkg;

public class LaTazzaFrameProperties extends AbstractFrameProperties {

    static private final int DEFAULTX=100;
    static private final int DEFAULTY=100;
    static private final int DEFAULTWIDITH=810;
    static private final int DEFAULTHEIGHT=500;

    public LaTazzaFrameProperties(int x,int y,int width,int height,int closeOperation) throws IllegalArgumentException
    {
        if(x<=0||y<=0||width<=0||height<=0) throw new IllegalArgumentException("Dimensioni per il frame inferiori a 0.");
        this.x=x;
        this.height=height;
        this.y=y;
        this.width=width;
        this.closeOp=closeOperation;
    }

    /**DEFAULT CONSTRUCTOR***/
    public LaTazzaFrameProperties(){
        this(DEFAULTX,DEFAULTY,DEFAULTWIDITH,DEFAULTHEIGHT,DEFAULTCLOSEOP);
    }

}
