import java.awt.*;

public abstract class Car implements Movable{

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double xPosition, yPosition; // The position of the car
    protected int direction; // The direction the car is facing, starting with north = 0 then going clock-wise

    // Constructors
    public Car(){
        this.direction = 0;
        stopEngine();
    }

    // Getters & setters
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    // Methods
    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public void move(){
        if(direction==0) yPosition += currentSpeed;

        if(direction==1) xPosition += currentSpeed;

        if(direction==2) yPosition -= currentSpeed;

        if(direction==3) xPosition -= currentSpeed;
    }

    public void turnLeft(){
        direction = (direction-1) % 4;
    };
    public void turnRight(){
        direction = (direction+1) % 4;
    };

    // Abstract methods
    abstract double speedFactor();

    abstract void incrementSpeed(double amount);

    abstract void decrementSpeed(double amount);

    abstract void gas(double amount);

    abstract void brake(double amount);


}
