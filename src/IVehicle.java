public interface IVehicle {
    void move();
    void turnLeft();
    void turnRight();
    void gas(double amount);
    void brake(double amount);
    void startEngine();
    void stopEngine();
    double getCurrentSpeed();
    double getXPosition();
    double getYPosition();
}
