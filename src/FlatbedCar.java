/**
 * The type Flatbed car.
 */
public abstract class FlatbedCar extends Car{

    /**
     * The Flatbed raised.
     */
    protected boolean flatbedRaised;

    /**
     * Instantiates a new Flatbed car.
     */
    public FlatbedCar(){
        this.flatbedRaised = false;
    }

    /**
     * Raise flatbed.
     */
    public void raiseFlatbed(){
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = true;
    }

    /**
     * Lower flatbed.
     */
    public void lowerFlatbed(){
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = false;
    }

    /**
     * Get flatbed raised boolean.
     *
     * @return the boolean
     */
    public boolean getFlatbedRaised(){
        return this.flatbedRaised;
    }

    /**
     *  Decrease the current speed of the car by an amount multiplied by a speedfactor.
     *  The speed is the maximum of the current speed and zero
     *
     * @param amount - The amount to gas, must be in [0,1]
     */
    @Override
    public void gas(double amount) {
        if (amount <= 1 && amount >= 0 && this.flatbedInDriveablePosition()) {
            this.incrementSpeed(amount);
        }else if(!this.flatbedInDriveablePosition()){
            //If the flatbed is raised and not drivable then we should lower the flatbed and vice versa.
            String exceptionString = "The flatbed is in the incorrect position, use " + ((this.flatbedRaised)? "lowerFlatbed()":"raiseFlatbed()")
                                        + " before using gas().";
            throw new IllegalStateException(exceptionString);
        }
        else {
            throw new IllegalArgumentException("Gas accepts values of amount between 0 and 1.");
        }
    }

    /**
     * Flatbed in driveable position boolean.
     *
     * @return the boolean
     */
    protected abstract boolean flatbedInDriveablePosition();




}
