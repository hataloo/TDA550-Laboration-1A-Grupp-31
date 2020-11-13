import java.util.ArrayList;
import java.util.*;

public class CarFerry extends Vehicle implements Transporter<Car> {

    private static double threshold = 0.5;
    private ArrayList<Car> carsOnFerry;
    private int capacity;

    public CarFerry() {
        this.capacity = 20;
        this.carsOnFerry = new ArrayList<Car>(this.capacity);
    }

    public CarFerry(int capacity) {
        this.capacity = capacity;
        this.carsOnFerry = new ArrayList<Car>(this.capacity);
    }
    public int getCapacity(){
        return this.capacity;
    }

    @Override
    public Car unloadTransportable() {
        if (!carsOnFerry.isEmpty()) {
            Car unloadedCar = this.carsOnFerry.remove(0);
            unloadedCar.setIsLoadedOntoTransporter(false);
            return unloadedCar; //Or .removeAll() ?
        } else
            throw new IllegalStateException("Cannot unload empty ferry");
    }

    @Override
    public void loadTransportable(Car toBeLoaded) {
        if(carOkToLoad(toBeLoaded)) {
            this.carsOnFerry.add(toBeLoaded);
            toBeLoaded.setIsLoadedOntoTransporter(true);
        } else if (toBeLoaded.getIsLoadedOntoTransporter()) {
            throw new IllegalStateException("Car is already loaded onto another transporter");
        }
        else{
            throw new IllegalStateException("Car is unable to load");
        }
    }

    /**
     * Move the position of the car in the direction it is facing
     * with the current speed
     */
    @Override
    public void move(){
        int direction = this.getDirection();
        double xPosition = this.getXPosition();
        double yPosition = this.getYPosition();
        double currentSpeed = this.getCurrentSpeed();

        if(direction==0) this.setYPosition(yPosition + currentSpeed);

        if(direction==1) this.setXPosition(xPosition + currentSpeed);

        if(direction==2) this.setYPosition(yPosition - currentSpeed);

        if(direction==3) this.setXPosition(xPosition - currentSpeed);

        for (Car car : carsOnFerry) {
            car.setXPosition(this.getXPosition());
            car.setYPosition(this.getYPosition());
        }
    }

    private boolean carOkToLoad(Car car) {
        if (Math.abs(car.getXPosition() - this.getXPosition()) >= threshold || Math.abs(car.getYPosition()- this.getYPosition()) >= threshold) {
            throw new IllegalArgumentException("Car to load must be closer to Ferry");}
        else
            return true;
        }
    }
