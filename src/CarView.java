import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarView extends JPanel implements CarObserver{
    private JFrame frame;
    private List<VehicleImage> vehicles;

    public CarView(JFrame frame) {
        this.frame = frame;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(frame.getX(), frame.getY()-240));
        this.setBackground(Color.green);
        this.frame.add(this);
    }

    public CarView(JFrame frame, int x, int y) {
        this.frame = frame;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x,y));
        this.setBackground(Color.green);
        this.frame.add(this);
    }

    @Override
    public void actOnVehicleMovement(List<VehicleImage> vehicles) {
        this.vehicles = vehicles;
        this.frame.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(VehicleImage vehicle : vehicles){
            Point point = convertCoordinatesToPoint(vehicle.getVehicle());
            g.drawImage(vehicle.getImage(), (int) point.getX(), (int) point.getY(), null);
        }
    }

    private Point convertCoordinatesToPoint(Vehicle vehicle){
        int x = (int) Math.round(vehicle.getXPosition());
        int y = (int) Math.round(vehicle.getYPosition());
        return new Point(x,y);
    }

}
