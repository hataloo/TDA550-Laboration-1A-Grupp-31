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
    private final List<IVehicle> vehicles;
    private final CarObserverComposite carObservers;
    private final int xBoundary, yBoundary;


    public CarModel( int xBoundary,int yBoundary) {
        this.vehicles = new ArrayList<>();
        this.carObservers = new CarObserverComposite();
        this.xBoundary = xBoundary;
        this.yBoundary = yBoundary;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (IVehicle vehicle : vehicles) {
            try {
                vehicle.gas(gas);
            }catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (IVehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }

    void startEngine(){
        for (IVehicle vehicle: vehicles){
            try {
                vehicle.startEngine();
            }catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    void stopEngine() {
        for (IVehicle vehicle: vehicles) {
            vehicle.stopEngine();
        }
    }

    void turboOn() {
        for (IVehicle vehicle: vehicles) {
            if(vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (IVehicle vehicle: vehicles) {
            if(vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    void raiseFlatbed() {
        for (IVehicle vehicle: vehicles) {
            if (vehicle instanceof FlatbedCar) {
                if(vehicle.getCurrentSpeed() == 0) {
                    ((FlatbedCar) vehicle).raiseFlatbed();
                } else{ System.out.println("Stop " + vehicle.getClass().getName()+ " before raising the Flatbed");}
            }
        }
    }

    void lowerFlatbed() {
        for (IVehicle vehicle: vehicles) {
            if (vehicle instanceof FlatbedCar) {
                ((FlatbedCar) vehicle).lowerFlatbed();
            }
        }
    }

    public void add(IVehicle vehicle){
        this.vehicles.add(vehicle);
        this.carObservers.actOnVehicleMovement(new ArrayList<IVehicle>(this.vehicles));
    }

    public IVehicle removeLast() {
        IVehicle removed =  this.vehicles.remove(vehicles.size()-1);
        this.carObservers.actOnVehicleMovement(vehicles);
        return removed;
    }

    private Point convertCoordinatesToPoint(IVehicle vehicle){
        int x = (int) Math.round(vehicle.getXPosition());
        int y = (int) Math.round(vehicle.getYPosition());
        return new Point(x,y);
    }

    private boolean vehicleInsideBoundary(IVehicle vehicle){
        int xTrueBoundary = xBoundary - 100;
        int yTrueBoundary = yBoundary - 60;
        Point point = convertCoordinatesToPoint(vehicle);
        return point.x >= 0 && point.x <= xTrueBoundary && point.y >= 0 && point.y <= yTrueBoundary;
    }

    public void update(){
        for (IVehicle vehicle : vehicles){
            vehicle.move();
            if(!vehicleInsideBoundary(vehicle)){
                vehicle.turnRight();
                vehicle.turnRight();
            }
            this.carObservers.actOnVehicleMovement(this.vehicles);
        }
    }

    public void addCarObserver(CarObserver carObserver) {
        this.carObservers.add(carObserver);
    }
    public int getNumberOfCars(){
        return vehicles.size();
    }
}
