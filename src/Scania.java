import java.awt.*;

public class Scania extends Car{

    /**
     * The angle of the flatbed
     */
    private double flatbedAngle;

    /**
     * Instantiates new Scania
     */
    public Scania(){
        this.nrDoors = 3;
        this.color = Color.green;
        this.enginePower =90;
        this.modelName = "Scania";
        this.flatbedAngle=0;
        stopEngine();
    }

    /**
     * Get flatbed angle
     *
     * @return double
     */
    public double getFlatbedAngle(){
        return flatbedAngle;
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
     * Start engine.
     */
    @Override
    public void startEngine(){
        if(flatbedAngle!=0)
            throw new IllegalStateException("The flatbed angle must be zero to start the engine.");
        else {
            currentSpeed = 0.1;
        }
    }

    /**
     *  Decrease the current speed of the car by an amount multiplied by a speedfactor.
     *  The speed is the maximum of the current speed and zero
     *
     * @param amount - The amount to gas, must be in [0,1]
     */
    @Override
    public void gas(double amount) {
        if (amount <= 1 && amount >= 0 && this.flatbedAngle == 0) {
            this.incrementSpeed(amount);
        }else if(this.flatbedAngle != 0){
            throw new IllegalStateException("The flatbed angle must be zero to increase the speed of the truck.");
        }
        else {
            throw new IllegalArgumentException("Gas accepts values of amount between 0 and 1.");
        }
    }

    /**
     * Set flatbed angle
     *
     * @param newAngle of the flatbed
     */
    public void setFlatbedAngle(double newAngle) {
        if (this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        else if (newAngle <= 70 && newAngle >= 0) {
            this.flatbedAngle = newAngle;
        }
        else{
            throw new IllegalArgumentException("Flatbedangle needs to be between 0 and 70");
        }
    }




}
