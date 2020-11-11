import com.sun.deploy.security.SelectableSecurityManager;

import java.awt.*;

public class Scania extends FlatbedCar{

    /**
     * The angle of the flatbed
     */
    private static final double MAX_FLATBED_ANGLE = 70;
    private static final double MIN_FLATBED_ANGLE = 0;
    private double flatbedAngle;

    /**
     * Instantiates new Scania with default values.
     */
    public Scania(){
        this.nrDoors = 3;
        this.color = Color.green;
        this.enginePower =90;
        this.modelName = "Scania";
        this.flatbedAngle=0;
    }

    protected boolean flatbedInDriveablePosition(){
        if(this.flatbedAngle==0){
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
     * Get flatbed angle
     *
     * @return double
     */
    public double getFlatbedAngle(){
        return flatbedAngle;
    }

    @Override
    public void raiseFlatbed(){
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = true;
        this.flatbedAngle = MAX_FLATBED_ANGLE;
    }

    public void raiseFlatbed(double increasedAngle){
        double newAngle = this.flatbedAngle + increasedAngle;
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        else if(increasedAngle <=0) {
            throw new IllegalArgumentException("The increased angle needs to be larger than 0.");
        }
        else{ this.flatbedAngle = Math.min(newAngle, MAX_FLATBED_ANGLE);
        this.flatbedRaised = true;}
    }
    @Override
    public void lowerFlatbed(){
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = false;
        this.flatbedAngle = MIN_FLATBED_ANGLE;
    }

    public void lowerFlatbed(double decreasedAngle){
        double newAngle = this.flatbedAngle - decreasedAngle;
        if(this.currentSpeed != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        else if(decreasedAngle < 0) {
            throw new IllegalArgumentException("The decreased angle needs to be larger than 0.");
        }
        else{
            this.flatbedAngle = Math.max(newAngle, MIN_FLATBED_ANGLE);
            if(this.flatbedAngle==0){this.flatbedRaised = false;}
        }
    }

}
