

import javax.swing.*;
import java.awt.*;

public class Main {
    static int xFrameSize = 950+200;
    static int yFrameSize = 800;
    static int xBoundary = 800+200;
    static int yBoundary = 800;
    static int controlPanelSize = 240;
    static int delay = 50;

    public static void main(String[] args) {
        // Instance of this class

        JFrame frame = Main.initFrame();
        JPanel mainPanel = new JPanel();

        CarModel model = new CarModel(xBoundary, yBoundary-controlPanelSize);
        CarView carView = new CarView(frame,xBoundary,yBoundary-controlPanelSize);
        SpeedView speedView = new SpeedView(frame);

        CarController carController = new CarController(model, frame, delay);
        AddRemoveCarController addRemoveCarController = new AddRemoveCarController(frame, model);

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
        frame.setPreferredSize(new Dimension(xFrameSize,yFrameSize));
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
