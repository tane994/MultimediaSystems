package Assignment2;

import javax.swing.JFrame;

public class BresenhamTest
{

    public static void main(String[] args)
    {
        // frame object is initialized with title
        JFrame frame = new JFrame("Bresenham");
        // exit application using System.exit() method
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // size of frame
        frame.setSize(300, 300);
        // calling BrasenhamCanvas object
        // initialized with n of col and rows
        BresenhamCanvas bresenhamCanvas = new BresenhamCanvas(20, 20);
        // The getContentPane() method retrieves the content pane layer so that you can add an object to it.
        // in this case the bresenhamCanvas object is appended to it.
        bresenhamCanvas.bresenhamLine(1, 10, 10, 5);
        frame.getContentPane().add(bresenhamCanvas);

        // setting visibility
        frame.setVisible(true);

    }
}