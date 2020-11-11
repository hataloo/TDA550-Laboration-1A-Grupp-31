import java.util.LinkedList;

public class CarTransport extends FlatbedCar {
    private static final int MAX_STORAGE_CAPACITY = 10;
    private LinkedList<SmallCar> storedCars;
    private int storageCapacity;

    public CarTransport() {
        this.storedCars = new LinkedList<SmallCar>();
    }

    public void loadCar(SmallCar carToLoad) {
        if (carOkToLoad(carToLoad)) {
            carToLoad.xPosition = this.xPosition;
            carToLoad.yPosition = this.yPosition;
            storedCars.push(carToLoad);
        }
    }

    public Car unloadCar() {
        if (!this.flatbedRaised && !storedCars.isEmpty()) {
            Car carToUnload = storedCars.pop();
            carToUnload.xPosition = this.xPosition;
            carToUnload.yPosition = this.yPosition;

            return carToUnload;
        } else if (storedCars.isEmpty()) {
            throw new IllegalStateException("There are no cars to unload.");
        }else{
            throw new IllegalStateException("Cannot unload cars while flatbed is raised");
        }
    }


    private boolean carOkToLoad(SmallCar car) {
        double threshold = 0.1;
        if (car.hashCode() == this.hashCode()) {
            throw new IllegalStateException("Cannot load self");
        } else if (car.xPosition - this.xPosition >= threshold && car.yPosition - this.yPosition >= threshold) {
            throw new IllegalArgumentException("Car to load must be closer to CarTransport");
        } else if (this.flatbedRaised) {
            throw new IllegalStateException("Cannot load while flatbed is raised");
        } else if (storedCars.size() >= MAX_STORAGE_CAPACITY) {
            throw new IllegalStateException("CarTransport already full");
        }
        return true;
    }

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
