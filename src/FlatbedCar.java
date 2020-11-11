
public abstract class FlatbedCar extends Car{

    protected boolean flatbedRaised;

    public FlatbedCar(){
        this.flatbedRaised = false;
    }

    public void raiseFlatbed(){
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = true;
    }
    public void lowerFlatbed(){
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = false;
    }

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

    protected abstract boolean flatbedInDriveablePosition();




}
