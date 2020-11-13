import java.awt.*;

/**
 * The type Volvo 240. Extends the Car-class.
 */
public class Volvo240 extends SmallCar {

    private final static double trimFactor = 1.2;

    /**
     * Instantiates a new Volvo 240.
     */
    public Volvo240() {
        this.setNrDoors(4);
        this.setColor(Color.black);
        this.setEnginePower(100);
        this.setModelName("Volvo240");
        this.stopEngine();
    }

    /**
     * Calculate the speed factor
     *
     * @return double
     */
    protected double speedFactor(){
        return this.getEnginePower() * 0.01 * trimFactor;
    }

}
