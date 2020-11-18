import java.awt.*;

public abstract class Vehicle implements Movable {

    private int magnitude;
    /**
     * The Engine power.
     */
    private double enginePower; // Engine power of the car
    /**
     * The Current speed.
     */
    private double currentSpeed; // The current speed of the car
    /**
     * The X & Y position.
     */
    private double xPosition, yPosition;
    /**
     * The direction the car is facing, starting with north = 0 then going clock-wise
     */
    private int direction;
    // Constructors

    /**
     * Instantiates a new Vehicle.
     */
    public Vehicle() {
        this.direction = 0;
        stopEngine();
    }
    // ================== Methods ==================


    public int getMagnitude() { return magnitude; }

    public void setMagnitude(int magnitude) { this.magnitude = magnitude; }

    protected double getEnginePower() {
        return enginePower;
    }

    protected void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    protected double getCurrentSpeed() {
        return currentSpeed;
    }

    protected void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    protected double getXPosition() {
        return xPosition;
    }

    protected void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    protected double getYPosition() {
        return yPosition;
    }

    protected void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

  protected int getDirection() {
        return direction;
    }

    protected void setDirection(int direction) {
        this.direction = direction;
    }

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
    @Override
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
    @Override
    public void turnLeft(){
        direction = (direction+3) % 4;
    }
    /**
     * Change the direction of the car
     * by 90 degrees in the clockwise direction
     */

    @Override
    public void turnRight(){
        direction = (direction+1) % 4;
    }

    /**
     *  Increase the current speed of the car by an amount multiplied by a speedfactor.
     *  The speed is the minimum of the current speed and the engine power
     *
     * @param amount - the amount to increase currentSpeed by
     */
    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     *  Decrease the current speed of the car by an amount multiplied by a speedfactor.
     *  The speed is the maximum of the current speed and zero
     *
     * @param amount - The amount to decrement currentSpeed by
     */
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * Increase the current speed by the provided amount
     *
     * @param amount double in the range [0,1]
     */
    public void gas(double amount) {
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
    protected double speedFactor(){
        return this.enginePower*0.01;
    }

}
