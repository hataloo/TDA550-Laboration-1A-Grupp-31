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

public class DrawPanel extends JPanel implements CanMoveImages{

    // Just a single image, TODO: Generalize
    // BufferedImage volvoImage;
    //Map<Vehicle,BufferedImage> images;
    // To keep track of a single cars position
    // Point volvoPoint = new Point();
    Map<Integer, ImageLocation> images;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.images = new HashMap<>();
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Set<Integer> keys = images.keySet();
        for(Integer key : keys){
            ImageLocation imageLocation = images.get(key);
            Point point = imageLocation.getLocation();
            g.drawImage(imageLocation.getImage(),(int) point.getX() ,(int) point.getY(),null);
        }
    }

    @Override
    public void moveImageLocation(int hashcode, Point point) {
        this.images.get(hashcode).setLocation(point);
        this.repaint();
    }

    @Override
    public void loadImage(int hashcode, BufferedImage image, Point point) {
        this.images.put(hashcode, new ImageLocation(image, point));
        this.repaint();
    }

    private class ImageLocation{
        private BufferedImage image;
        private Point location;

        ImageLocation(BufferedImage image, Point location){
            this.image = image;
            this.location = location;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        public void setLocation(Point location) {
            this.location = location;
        }

        public BufferedImage getImage() {
            return image;
        }

        public Point getLocation() {
            return location;
        }
    }
}
