package tests;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MyFrameFullscreen extends JFrame implements ActionListener{

    private JButton b_close, b_larger, b_smaller;
    private GraphicsDevice device;


    public MyFrameFullscreen() throws HeadlessException {
        this.setLayout(null);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device =ge.getDefaultScreenDevice();

        if(device.isFullScreenSupported()){
            device.setFullScreenWindow(this);
        }


        b_close = new JButton("Beenden");
        b_close.setBounds(20, 20, 100, 30);
        b_close.addActionListener(this);

        b_larger = new JButton("FullScreen");
        b_larger.setBounds(20, 70, 100, 30);
        b_larger.addActionListener(this);
        b_larger.setEnabled(false);

        b_smaller = new JButton("Window");
        b_smaller.setBounds(20, 120, 100, 30);
        b_smaller.addActionListener(this);

        this.add(b_close);
        this.add(b_smaller);
        this.add(b_larger);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== b_close){
            device.setFullScreenWindow(null);
            System.exit(0);
        }
        if(e.getSource()== b_larger){
            b_larger.setEnabled(false);
            b_smaller.setEnabled(true);
            device.setFullScreenWindow(this);
        }
        if(e.getSource()== b_smaller){
            b_larger.setEnabled(true);
            b_smaller.setEnabled(false);
            device.setFullScreenWindow(null);
        }
    }

    public static void main(String[] args) {
        MyFrameFullscreen f = new MyFrameFullscreen();
        f.setVisible(true);
    }

}