import java.util.LinkedList;
import java.lang.Math;

/**
 * The type Car transport.
 */
public class CarTransport extends FlatbedCar {
    private static final int MAX_STORAGE_CAPACITY = 10;
    private LinkedList<SmallCar> storedCars;


    /**
     * Instantiates a new Car transport.
     */
    public CarTransport() {
        this.storedCars = new LinkedList<SmallCar>();
    }

    /**
     * Load car.
     *
     * @param carToLoad the car to load
     */
    public void loadCar(SmallCar carToLoad) {
        if (carOkToLoad(carToLoad)) {
            carToLoad.xPosition = this.xPosition;
            carToLoad.yPosition = this.yPosition;
            carToLoad.setLoadedOntoTransport(true);
            storedCars.push(carToLoad);
        }
    }

    /**
     * Unload car small car.
     *
     * @return the small car
     */
    public SmallCar unloadCar() {
        if (!this.flatbedRaised && !storedCars.isEmpty()) {
            SmallCar carToUnload = storedCars.pop();
            carToUnload.setLoadedOntoTransport(false);
            carToUnload.xPosition = this.xPosition;
            carToUnload.yPosition = this.yPosition;

            return carToUnload;
        } else if (storedCars.isEmpty()) {
            throw new IllegalStateException("There are no cars to unload.");
        }else{
            throw new IllegalStateException("Cannot unload cars while flatbed is raised");
        }
    }

    /**
     * Checks that the car fullfills the requirements to be loaded.
     *
     * @param car - The car to be loaded
     * @return - boolean
     */
    private boolean carOkToLoad(SmallCar car) {
        double threshold = 0.1;
        if (car.hashCode() == this.hashCode()) {
            throw new IllegalStateException("Cannot load self");
        } else if (Math.abs(car.xPosition - this.xPosition) >= threshold || Math.abs(car.yPosition - this.yPosition) >= threshold) {
            throw new IllegalArgumentException("Car to load must be closer to CarTransport");
        } else if (this.flatbedRaised) {
            throw new IllegalStateException("Cannot load while flatbed is raised");
        } else if (storedCars.size() >= MAX_STORAGE_CAPACITY) {
            throw new IllegalStateException("CarTransport already full");
        }
        return true;
    }

    /**
     * Returns true if flatbed is raised.
     * @return - boolean
     */
    protected boolean flatbedInDriveablePosition(){
        if(this.flatbedRaised){
            return true;
        }
        else
            return false;
    }

    /**
     * Compute the speedfactor.
     *
     * @return double
     */
    @Override
    protected double speedFactor(){
        return enginePower * 0.01;
    }

    /**
     * Move the position of the car in the direction it is facing
     * with the current speed
     */
    @Override
    public void move(){
        if(direction==0) yPosition += currentSpeed;

        if(direction==1) xPosition += currentSpeed;

        if(direction==2) yPosition -= currentSpeed;

        if(direction==3) xPosition -= currentSpeed;

        for (SmallCar car: storedCars) {
            car.xPosition = this.xPosition;
            car.yPosition = this.yPosition;
        }
    }


}
