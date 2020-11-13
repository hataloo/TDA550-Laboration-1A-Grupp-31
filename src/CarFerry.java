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

    private boolean carOkToLoad(Car car) {

        if (Math.abs(car.getXPosition() - this.getXPosition()) >= threshold || Math.abs(car.getYPosition()- this.getYPosition()) >= threshold) {
            throw new IllegalArgumentException("Car to load must be closer to Ferry");}
        else
            return true;
        }
    }
