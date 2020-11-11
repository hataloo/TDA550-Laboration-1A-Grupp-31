import java.awt.*;

/**
 * The type Volvo 240. Extends the Car-class.
 */
public class Volvo240 extends Car {

    private final static double trimFactor = 1.2;

    /**
     * Instantiates a new Volvo 240.
     */
    public Volvo240() {
        this.nrDoors = 4;
        this.color = Color.black;
        this.enginePower = 100;
        this.modelName = "Volvo240";
        this.stopEngine();
    }

    /**
     * Calculate the speed factor
     *
     * @return double
     */
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

}
