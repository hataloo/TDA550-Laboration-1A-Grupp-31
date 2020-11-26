import com.sun.deploy.security.SelectableSecurityManager;

import java.awt.*;

/**
 * The type Scania.
 */
public class Scania extends FlatbedCar{

    private static final double MAX_FLATBED_ANGLE = 70;
    private static final double MIN_FLATBED_ANGLE = 0;

    /**
     * The angle of the flatbed
     */
    private double flatbedAngle;

    /**
     * Instantiates new Scania with default values.
     */
    public Scania(){
        this.setNrDoors(2);
        this.setColor(Color.green);
        this.setEnginePower(90);
        this.setModelName("Scania");
        this.flatbedAngle=0;
    }
    public Scania(int x, int y){
        this();
        this.setXPosition(x);
        this.setYPosition(y);
    }
    /**
     * Returns true if flatbedAngle is 0.
     * @return boolean
     */
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
        return this.getEnginePower() * 0.01;
    }

    /**
     * Start engine.
     */
    @Override
    public void startEngine(){
        if(flatbedAngle!=0)
            throw new IllegalStateException("The flatbed angle must be zero to start the engine.");
        else {
            this.setCurrentSpeed(0.1);
        }
    }

    /**
     * Get flatbed angle
     *
     * @return double double
     */
    public double getFlatbedAngle(){
        return flatbedAngle;
    }

    /**
     * raise the Flatbed to its greatest angle
     *
     */
    @Override
    public void raiseFlatbed(){
        if(this.getCurrentSpeed() != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = true;
        this.flatbedAngle = MAX_FLATBED_ANGLE;
    }

    /**
     * Increase the flatbed by an angle given as argument
     *
     * @param increasedAngle is the angle which the flatbed increases
     */
    public void raiseFlatbed(double increasedAngle){
        double newAngle = this.flatbedAngle + increasedAngle;
        if(this.getCurrentSpeed() != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        else if(increasedAngle <=0) {
            throw new IllegalArgumentException("The increased angle needs to be larger than 0.");
        }
        else{ this.flatbedAngle = Math.min(newAngle, MAX_FLATBED_ANGLE);
        this.flatbedRaised = true;}
    }

    /**
     * lower the angle of the flatbed to its lowest degree
     *
     */
    @Override
    public void lowerFlatbed(){
        if(this.getCurrentSpeed() != 0){
            throw new IllegalStateException("The currentSpeed is non-zero, use stopEngine() first.");
        }
        this.flatbedRaised = false;
        this.flatbedAngle = MIN_FLATBED_ANGLE;
    }

    /**
     * Lower flatbed by an angle given as argument.
     *
     * @param decreasedAngle the angle which the flatbed is decreased
     */
    public void lowerFlatbed(double decreasedAngle){
        double newAngle = this.flatbedAngle - decreasedAngle;
        if(this.getCurrentSpeed() != 0){
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
