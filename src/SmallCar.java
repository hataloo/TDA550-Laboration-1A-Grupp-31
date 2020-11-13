public abstract class SmallCar extends Car {
    private boolean LoadedOntoTransport;

    public SmallCar(){
        this.LoadedOntoTransport = false;
    }

    protected void setLoadedOntoTransport(boolean status){
        this.LoadedOntoTransport = status;
    }

    public boolean getLoadedOntoTransport(){
        return this.LoadedOntoTransport;
    }

    /*
    @Override

    public void move() {
        if (!this.LoadedOntoTransport) {
            super.move();
        } else {
            throw new IllegalStateException("Cannot move car while loaded.");
        }
    }

    @Override
    public void turnLeft() {
        if (!this.LoadedOntoTransport) {
            super.turnLeft();
        } else {
            throw new IllegalStateException("Cannot turn left while loaded.");
        }
    }

    @Override
    public void turnRight() {
        if (!this.LoadedOntoTransport) {
            super.turnRight();
        } else {
            throw new IllegalStateException("Cannot turn right while loaded.");
        }
    }

     */
}
