package tests;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class JPanelDemo {

    JFrame frame;
    JPanel panel;

    JPanelDemo(){
        createFrame();
    }

    public void createFrame(){
        //create the frame

        frame=new JFrame("GUI Sample");
        frame.setSize(new Dimension(750,300));
        frame.setMaximumSize(new Dimension(2147483647, 2147483647));
        frame.setVisible(true);
        //create the panel

        panel= new JPanel();
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.yellow, Color.black));
        panel.setAlignmentX((float) 0.5);
        panel.setAlignmentY((float) 0.5);
        panel.setDoubleBuffered(true);
        panel.setFocusable(true);
        panel.setCursor(Cursor.getDefaultCursor());
        panel.setMaximumSize(new Dimension(32767, 32767));
        panel.setSize(new Dimension(732, 146));
        panel.size();
        frame.setLayout(null);
        frame.add(panel);

    }

    public static void main(String [] args){

        JPanelDemo jpd= new JPanelDemo();
    }
}