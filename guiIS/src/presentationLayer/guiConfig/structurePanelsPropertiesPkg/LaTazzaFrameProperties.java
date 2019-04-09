package presentationLayer.guiConfig.structurePanelsPropertiesPkg;

import javax.swing.*;

public class LaTazzaFrameProperties {

    private int x;
    private int y;
    private int width;
    private int height;
    private int closeOp;

    static private final int DEFAULTCLOSEOP= JFrame.EXIT_ON_CLOSE;
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

    /**PROPERTIES GETTER**/
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCloseOp() {
        return closeOp;
    }

    public void initFrame(JFrame frame){

        frame.setBounds(x,y,width, height);
        frame.setDefaultCloseOperation(closeOp);//todo checksetBounds(100, 100, 800, 500);
        frame.setUndecorated(true);
        frame.setTitle("LaTazza");
    }

}
