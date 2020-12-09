import javax.swing.*;
import java.awt.*;

public class Main {
    static int xBoundary = 800;
    static int yBoundary = 800;
    static int controlPanelSize = 240;
    static int delay = 50;

    public static void main(String[] args) {
        // Instance of this class

        JFrame frame = Main.initFrame();

        CarModel model = new CarModel(xBoundary, yBoundary);
        CarView carView = new CarView(frame);
        SpeedView speedView = new SpeedView(frame);
        CarController carController = new CarController(model, frame, delay);

        model.addCarObserver(carView);
        model.addCarObserver(speedView);

        model.add(new Volvo240(0,0));
        model.add(new Saab95(100,0));
        model.add(new Scania(200,0));

        carController.startTimer();


    }

    private static JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Bästa bilarna");
        frame.setPreferredSize(new Dimension(xBoundary,yBoundary));
        frame.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        // Make the frame pack all it's components by respecting the sizes if possible.
        frame.pack();
        // Center the frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Make the frame visible
        frame.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }
}
