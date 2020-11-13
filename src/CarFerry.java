import java.awt.*;
import java.util.ArrayList;

public class CarFerry implements Transporter<Car> {

    private ArrayList<Car> carsOnFerry;
    private int capacity;

    public CarFerry(){
        this.capacity=20;
        this.carsOnFerry = new ArrayList<Car>(this.capacity);
    }


    @Override
    public void loadTransportable(Car toBeLoaded) {
        if(toBeLoaded.getCurrentSpeed()!=0){
            throw new IllegalStateException("Car must not move while loaded onto ferry");
        }
    }

    @Override
    public SmallCar unloadTransportable(Car toBeUnloaded) {
        return null;
    }

}
