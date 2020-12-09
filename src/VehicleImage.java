import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VehicleImage {
    Vehicle vehicle;
    BufferedImage image;

    VehicleImage(Vehicle vehicle) {
        this.vehicle = vehicle;
        loadImageFromDrive(vehicle);
    }

    private void loadImageFromDrive(Vehicle vehicle){
        String filepath = "pics/" + vehicle.getClass().getName() + ".jpg";
        BufferedImage loadedImage = new BufferedImage(100,100,1);
        try {
            loadedImage = ImageIO.read(VehicleImage.class.getResourceAsStream(filepath));
            this.image = loadedImage;
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Error: Could not load vehicle image from " + filepath);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
