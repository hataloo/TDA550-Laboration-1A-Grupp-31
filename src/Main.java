import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Instance of this class
        int xBoundary = CarViewOld.getFrameWidth();
        int yBoundary = CarViewOld.getFrameHeight();

        DrawPanel drawPanel = new DrawPanel(xBoundary, yBoundary);
        CarController cc = new CarController(drawPanel, xBoundary, yBoundary);

        CarViewOld carView = new CarViewOld("Den bästa simuleringen", cc, drawPanel, 50);


        cc.add(new Volvo240(0,0));
        cc.add(new Saab95(100,0));
        cc.add(new Scania(200,0));
        // Start a new view and send a reference of self

        // Start the timer
        carView.startTimer();

//        this.setTitle(title);
//        this.setPreferredSize(new Dimension(X,Y));
//        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

//        // Make the frame pack all it's components by respecting the sizes if possible.
//        this.pack();
//
//        // Get the computer screen resolution
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        // Center the frame
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
//        // Make the frame visible
//        this.setVisible(true);
//        // Make sure the frame exits when "x" is pressed
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
