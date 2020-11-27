import sun.java2d.pipe.BufferedBufImgOps;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.util.List;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    // BufferedImage volvoImage;
    Map<Vehicle,BufferedImage> images;
    // To keep track of a single cars position
    // Point volvoPoint = new Point();
    Map<Vehicle,Point> points;

    // TODO: Make this general for all cars
    void moveit(Vehicle vehicle, int x, int y){
        points.get(vehicle).setLocation(x,y);
        //volvoPoint.x = x;
        //volvoPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(List<Vehicle> vehicles, int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.images = new HashMap<>();
        this.points = new HashMap<>();
        // Print an error message in case file is not found with a try/catch block
        for(Vehicle vehicle : vehicles ) {
            this.points.put(vehicle,new Point((int) vehicle.getXPosition(),(int)vehicle.getYPosition()));
            try {
                // You can remove the "pics" part if running outside of IntelliJ and
                // everything is in the same main folder.
                // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

                // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
                // if you are starting in IntelliJ.
                //volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
                String filepath = "pics/" + vehicle.getClass().getName() + ".jpg";
                BufferedImage newImage = ImageIO.read(DrawPanel.class.getResourceAsStream(filepath));
                images.put(vehicle, newImage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Set<Vehicle> keys = images.keySet();
        for(Vehicle key : keys){
            Point point = points.get(key);
            g.drawImage(images.get(key),(int) point.getX() ,(int) point.getY(),null);
        }
        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }

    public int getImageHeight(Vehicle vehicle) {
        return images.get(vehicle).getHeight();
    }

    public int getImageWidth(Vehicle vehicle) {
        return images.get(vehicle).getWidth();
    }
}
