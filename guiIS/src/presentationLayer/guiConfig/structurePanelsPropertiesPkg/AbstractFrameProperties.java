package presentationLayer.guiConfig.structurePanelsPropertiesPkg;

import presentationLayer.guiConfig.ResourcesClassLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class AbstractFrameProperties {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int closeOp;

    static protected final int DEFAULTCLOSEOP= JFrame.EXIT_ON_CLOSE;


    public static void setDockImageFrame(JFrame frame)
    {
        if(System.getProperty("os.name").toLowerCase().contains("mac os x"))
            com.apple.eawt.Application.getApplication().setDockIconImage((ResourcesClassLoader.getIconTazza2()).getImage());
        else {
            frame.setIconImage(ResourcesClassLoader.getIconTazza2().getImage());
        }
    }

    public void setLocationCenter(JFrame frame) {

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - getWidth()) / 2;
        int iCoordY = (objDimension.height - getHeight()) / 2;
        frame.setLocation(iCoordX, iCoordY);

    }

    public void initFrame(JFrame frame,Color color){

        frame.setBounds(x,y,width, height);
        frame.setUndecorated(true);
        frame.setTitle("LaTazza");
        frame.setBackground(color);
        setLocationCenter(frame);
        setDockImageFrame(frame);
        frame.setLayout(null);
    }

    public void postPonedDisplay(JFrame frame)
    {
        Timer timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        }, 5*1000);
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

}
