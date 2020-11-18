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
        this.setNrDoors(2);
        this.setColor(Color.red);
        this.setEnginePower(125);
        this.turboOn = false;
        this.setModelName("Saab95");
        this.setMagnitude(10);
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
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return this.getEnginePower() * 0.01 * turbo;
    }
}
/**/