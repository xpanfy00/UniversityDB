package cz.vutbr.project;

import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {
    static JFrame frame;
    static JProgressBar bar;


    public static void main(String[] args) {

        Dimension dim = new Dimension();
        dim.width = 450;
        dim.height = 35;
        frame = new JFrame();
        JPanel panel = new JPanel();
        bar = new JProgressBar();
        frame.setLocationRelativeTo(null);
        bar.setValue(0);
        bar.setStringPainted(true);
        frame.add(panel);
        panel.add(bar);
        frame.setSize(500, 85);
        bar.setPreferredSize(dim);
        frame.setVisible(true);
        fill();
        frame.setVisible(false);
        AuthForm authForm = new AuthForm();
        frame.setLocationRelativeTo(null);
        frame.remove(bar);
        frame.remove(panel);
        frame.add(authForm.getMain());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.setSize(1024, 768);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void fill() {
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                bar.setValue(i + 10);
                Thread.sleep(350);
                i += 20;
            }
        } catch (Exception e) {

        }
    }

}
