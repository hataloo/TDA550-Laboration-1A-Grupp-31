import javax.swing.*;
import java.util.List;

public class CarView extends JPanel implements CarObserver{
    private JFrame frame;
    private List<VehicleImage> vehicles;

    public CarView(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actOnVehicleMovement(List<VehicleImage> vehicles) {

    }
}
