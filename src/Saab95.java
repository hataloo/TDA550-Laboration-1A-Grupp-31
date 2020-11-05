import java.awt.*;

/**
 * The type Saab 95. Extends the Car-class
 */
public class Saab95 extends Car{

    private boolean turboOn;

    /**
     * Instantiates a new Saab 95.
     */
    public Saab95(){
        this.nrDoors = 2;
        this.color = Color.red;
        this.enginePower = 125;
        this.turboOn = false;
        this.modelName = "Saab95";
        stopEngine();
    }

    /**
     * Set turbo on.
     */
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * Set turbo off.
     */
    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     *  Check if turbo is on and compute the speedfactor.
     *
     * @return double
     */
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}
