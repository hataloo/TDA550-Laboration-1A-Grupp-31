import java.util.LinkedList;
import java.lang.Math;

/**
 * The type Car transport.
 */
public class CarTransport extends FlatbedCar implements Transporter<SmallCar> {
    private static final int MAX_STORAGE_CAPACITY = 10;
    private static double threshold = 0.1;
    private LinkedList<SmallCar> storedCars;


    /**
     * Instantiates a new Car transport.
     */
    public CarTransport() {
        this.storedCars = new LinkedList<SmallCar>();
    }


    @Override
    public void loadTransportable(SmallCar carToLoad) {
        if (carOkToLoad(carToLoad)) {
            carToLoad.setXPosition(this.getXPosition());
            carToLoad.setYPosition(this.getYPosition());
            carToLoad.setIsLoadedOntoTransporter(true);
            storedCars.push(carToLoad);
        }
    }

    @Override
    public SmallCar unloadTransportable() {
        if (storedCars.isEmpty()) {
            throw new IllegalStateException("There are no cars to unload.");
        } else if (this.flatbedRaised) {
            throw new IllegalStateException("Cannot unload cars while flatbed is raised");
        } else {
            SmallCar carToUnload = storedCars.pop();
            carToUnload.setIsLoadedOntoTransporter(false);
            return carToUnload;
        }
    }

    /**
     * Checks that the car fullfills the requirements to be loaded.
     *
     * @param car - The car to be loaded
     * @return - boolean
     */
    private boolean carOkToLoad(SmallCar car) {
        if (car.hashCode() == this.hashCode()) {
            throw new IllegalStateException("Cannot load self");
        } else if (Math.abs(car.getXPosition() - this.getXPosition()) >= threshold || Math.abs(car.getYPosition() - this.getYPosition()) >= threshold) {
            throw new IllegalArgumentException("Car to load must be closer to CarTransport");
        } else if (this.flatbedRaised) {
            throw new IllegalStateException("Cannot load while flatbed is raised");
        } else if (storedCars.size() >= MAX_STORAGE_CAPACITY) {
            throw new IllegalStateException("CarTransport already full");
        } else if (car.getIsLoadedOntoTransporter()) {
            throw new IllegalStateException("Car is already loaded onto another transporter");
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

        for (SmallCar car: storedCars) {
            car.setXPosition(this.getXPosition());
            car.setYPosition(this.getYPosition());
        }
    }


}
