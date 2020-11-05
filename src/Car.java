import java.awt.*;


/**
 * The type Car.
 */
public abstract class Car implements Movable{
    /**
     * The number of doors.
     */
    protected int nrDoors; // Number of doors on the car
    /**
     * The Engine power.
     */
    protected double enginePower; // Engine power of the car
    /**
     * The Current speed.
     */
    protected double currentSpeed; // The current speed of the car
    /**
     * The Color.
     */
    protected Color color; // Color of the car
    /**
     * The Model name.
     */
    protected String modelName; // The car model name
    /**
     * The X position.
     */
    protected double xPosition, /**
     * The Y position.
     */
    yPosition;
    /**
     * The direction the car is facing, starting with north = 0 then going clock-wise
     */
    protected int direction;

    // Constructors
    /**
     * Instantiates a new Car.
     */
    public Car(){
        this.direction = 0;
        stopEngine();
    }

    // Getters & setters
    /**
     * Get nr doors int.
     *
     * @return the int
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Get engine power double.
     *
     * @return the double
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * Get current speed double.
     *
     * @return the double
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Get color color.
     *
     * @return the color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Set color.
     *
     * @param clr the clr
     */
    public void setColor(Color clr){
        color = clr;
    }

    // Methods
    /**
     * Start engine.
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Stop engine.
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Move the position of the car in the direction it is facing
     * with the current speed
     */
    public void move(){
        if(direction==0) yPosition += currentSpeed;

        if(direction==1) xPosition += currentSpeed;

        if(direction==2) yPosition -= currentSpeed;

        if(direction==3) xPosition -= currentSpeed;
    }

    /**
     * Change the direction of the car
     * by 90 degrees in the counterclockwise direction
     */
    public void turnLeft(){
        direction = (direction+3) % 4;
    }
    /**
     * Change the direction of the car
     * by 90 degrees in the clockwise direction
     */
    public void turnRight(){
        direction = (direction+1) % 4;
    }

    /**
     *  Increase the current speed of the car by an amount multiplied by a speedfactor.
     *  The speed is the minimum of the current speed and the engine power
     *
     * @param amount
     */
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     *  Decrease the current speed of the car by an amount multiplied by a speedfactor.
     *  The speed is the maximum of the current speed and zero
     *
     * @param amount
     */
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * Increase the current speed by the provided amount
     *
     * @param amount double in the range [0,1]
     */
    void gas(double amount) {
        if (amount <= 1 && amount >= 0) {
            incrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("Gas accepts values of amount between 0 and 1.");
        }
    }

    /**
     * Decrease the current speed by the provided amount
     *
     * @param amount double in the range [0,1]
     */
    public void brake(double amount){
        if(amount <= 1 && amount >= 0) {
            decrementSpeed(amount);
        }else{
            throw new IllegalArgumentException("Brake accepts values of amount between 0 and 1.");
        }
    }

    /**
     * Calculate the specific speed factor of an instance.
     *
     * @return double
     */
    abstract double speedFactor();


}
