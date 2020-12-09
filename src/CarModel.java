import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * This class represents the Model part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarModel {
    // member fields:
    private final List<VehicleImage> vehicles;
    private final CarObserverComposite carObservers;
    private final CanMoveImages canMoveImages;
    private final int xBoundary, yBoundary;


    public CarModel(CanMoveImages canMoveImages, int xBoundary,int yBoundary) {
        this.canMoveImages = canMoveImages;
        this.vehicles = new ArrayList<>();
        this.carObservers = new CarObserverComposite();
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
        this.carObservers.actOnVehicleMovement(this.vehicles);
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
            this.carObservers.actOnVehicleMovement(this.vehicles);
        }
    }

    public void addCarObserver(CarObserver carObserver) {
        this.carObservers.add(carObserver);
    }
}
