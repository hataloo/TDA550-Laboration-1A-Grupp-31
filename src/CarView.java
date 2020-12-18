import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        this.vehicles = new ArrayList<>();
    }

    @Override
    public void actOnVehicleMovement(List<IVehicle> vehicles) {
        List<VehicleImage> tempList = new ArrayList<>();
        for (IVehicle vehicle : vehicles) {
            tempList.add(new VehicleImage(vehicle));
        }
        this.vehicles = tempList;
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

    private Point convertCoordinatesToPoint(IVehicle vehicle){
        int x = (int) Math.round(vehicle.getXPosition());
        int y = (int) Math.round(vehicle.getYPosition());
        return new Point(x,y);
    }

}
