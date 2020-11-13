import java.awt.*;


/**
 * The type Car.
 */
public abstract class Car extends Vehicle implements Transportable {
    /**
     * The number of doors.
     */
    private int nrDoors; // Number of doors on the car

    private Color color; // Color of the car
    /**
     * The Model name.
     */
    private String modelName; // The car model name

    private boolean isLoadedOntoTransporter;


    // Getters & setters
    /**
     * Get nr doors int.
     *
     * @return the int
     */
    public int getNrDoors() {
        return nrDoors;
    }

    protected void setNrDoors(int nrDoors) {
        this.nrDoors = nrDoors;
    }

    public Color getColor(){
        return this.color;
    }

    protected void setColor(Color color){
        this.color = color;
    }

    public String getModelName() {
        return modelName;
    }

    protected void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public boolean getIsLoadedOntoTransporter() {
        return this.isLoadedOntoTransporter;
    }
    @Override
    public void setIsLoadedOntoTransporter(boolean isLoaded) {
        this.isLoadedOntoTransporter = isLoaded;
    }
    @Override
    public void move() {
        if (!this.getIsLoadedOntoTransporter()) {
            super.move();
        } else {
            throw new IllegalStateException("Cannot move car while loaded onto a transporter.");
        }
    }

    @Override
    public void turnLeft() {
        if (!this.getIsLoadedOntoTransporter()) {
            super.turnLeft();
        } else {
            throw new IllegalStateException("Cannot turn left while loaded onto a transporter.");
        }
    }

    @Override
    public void turnRight() {
        if (!this.getIsLoadedOntoTransporter()) {
            super.turnRight();
        } else {
            throw new IllegalStateException("Cannot turn right while loaded onto a transporter.");
        }
    }
}
