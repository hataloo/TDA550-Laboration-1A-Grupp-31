import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VehicleImage {
    private IVehicle vehicle;
    private BufferedImage image;
    private static Map<Class<? extends IVehicle>, BufferedImage> loadedImages = new HashMap<>();

    VehicleImage(IVehicle vehicle) {
        this.vehicle = vehicle;
        loadImageFromDrive(vehicle);
    }

    private void loadImageFromDrive(IVehicle vehicle){
        if (loadedImages.containsKey(vehicle.getClass())) {
            this.image = loadedImages.get(vehicle.getClass());
        }else{
            String filepath = "pics/" + vehicle.getClass().getName() + ".jpg";
            System.out.println("Loading: " + filepath);
            BufferedImage loadedImage = new BufferedImage(100,100,1);
            try {
                loadedImage = ImageIO.read(VehicleImage.class.getResourceAsStream(filepath));
                loadedImages.put(vehicle.getClass(),loadedImage);
                this.image = loadedImage;
                return;
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Error: Could not load vehicle image from " + filepath);
        }

    }

    public IVehicle getVehicle() {
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
