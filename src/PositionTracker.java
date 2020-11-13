public class PositionTracker implements Movable {
    private double x, y, currentSpeed, maxSpeed, speedFactor;
    private int direction;

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public PositionTracker() {
        this.x = 0;
        this.y = 0;
        this.currentSpeed = 0;
        this.direction = 0;
    }

    public PositionTracker(double x, double y, double speedFactor) {
        this.x = x;
        this.y = y;
        this.speedFactor = speedFactor;
        this.currentSpeed = 0;
        this.direction = 0;
    }

    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(currentSpeed + speedFactor * amount,maxSpeed);
    }
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor* amount,0);
    }

    @Override
    public void move() {
        if (direction == 0) y += currentSpeed;

        if (direction == 1) x += currentSpeed;

        if (direction == 2) y -= currentSpeed;

        if (direction == 3) x -= currentSpeed;
    }

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

    @Override
    public void turnLeft() {
        this.direction = (this.direction+3) % 4;
    }

    @Override
    public void turnRight() {
        this.direction = (this.direction+1) % 4;
    }
}
