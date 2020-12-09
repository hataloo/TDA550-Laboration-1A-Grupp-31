import jdk.nashorn.internal.ir.Flags;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    private final List<VehicleImage> vehicles;
    private final CanMoveImages canMoveImages;
    private final int xBoundary, yBoundary;


    CarController(CanMoveImages canMoveImages, int xBoundary,int yBoundary) {
        this.canMoveImages = canMoveImages;
        this.vehicles = new ArrayList<>();
        this.xBoundary = xBoundary;
        this.yBoundary = yBoundary;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (VehicleImage vehicle : vehicles) {
            try {
                vehicle.getVehicle().gas(gas);
            }catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (VehicleImage vehicle : vehicles) {
            vehicle.getVehicle().brake(brake);
        }
    }

    void startEngine(){
        for (VehicleImage vehicle: vehicles){
            try {
                vehicle.getVehicle().startEngine();
            }catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    void stopEngine() {
        for (VehicleImage vehicle: vehicles) {
            vehicle.getVehicle().stopEngine();
        }
    }

    void turboOn() {
        for (VehicleImage vehicle: vehicles) {
            if(vehicle.getVehicle() instanceof Saab95){
                ((Saab95) vehicle.getVehicle()).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (VehicleImage vehicle: vehicles) {
            if(vehicle.getVehicle() instanceof Saab95){
                ((Saab95) vehicle.getVehicle()).setTurboOff();
            }
        }
    }

    void raiseFlatbed() {
        for (VehicleImage vehicle: vehicles) {
            if (vehicle.getVehicle() instanceof FlatbedCar) {
                if(vehicle.getVehicle().getCurrentSpeed() == 0) {
                    ((FlatbedCar) vehicle.getVehicle()).raiseFlatbed();
                } else{ System.out.println("Stop " + vehicle.getClass().getName()+ " before raising the Flatbed");}
            }
        }
    }

    void lowerFlatbed() {
        for (VehicleImage vehicle: vehicles) {
            if (vehicle.getVehicle() instanceof FlatbedCar) {
                ((FlatbedCar) vehicle.getVehicle()).lowerFlatbed();
            }
        }
    }
    public void add(Vehicle vehicle){
        VehicleImage vehicleImage = new VehicleImage(vehicle);
        this.vehicles.add(vehicleImage);
        Point point = convertCoordinatesToPoint(vehicle);
        this.canMoveImages.loadImage(vehicleImage.hashCode(),vehicleImage.getImage(), point);
    }

    private Point convertCoordinatesToPoint(Vehicle vehicle){
        int x = (int) Math.round(vehicle.getXPosition());
        int y = (int) Math.round(vehicle.getYPosition());
        return new Point(x,y);
    }

    private boolean vehicleInsideBoundary(VehicleImage vehicle){
        int xTrueBoundary = xBoundary - vehicle.getImage().getWidth();
        int yTrueBoundary = yBoundary - vehicle.getImage().getHeight();
        Point point = convertCoordinatesToPoint(vehicle.getVehicle());
        return point.x >= 0 && point.x <= xTrueBoundary && point.y >= 0 && point.y <= yTrueBoundary;
    }

    public void update(){
        for (VehicleImage vehicle : vehicles){
            vehicle.getVehicle().move();

            if(!vehicleInsideBoundary(vehicle)){
                vehicle.getVehicle().turnRight();
                vehicle.getVehicle().turnRight();
            }
            canMoveImages.moveImageLocation(vehicle.hashCode(), convertCoordinatesToPoint(vehicle.getVehicle()));
        }
    }

    /*
    private class VehicleImage {

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
                 loadedImage = ImageIO.read(DrawPanel.class.getResourceAsStream(filepath));
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

     */
}
