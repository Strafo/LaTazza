package utils;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {

    public MyJLabel(String descrizione, Font font, int x, int y, int width, int height, ImageIcon img)
    {
        this.setText(descrizione);
        this.setFont(font);
        this.setBounds(x,y,width,height);
        this.setIcon(img);
    }
}
