public class Main {
    public static void main(String[] args) {
        // Instance of this class
        int xBoundary = CarViewOld.getFrameWidth();
        int yBoundary = CarViewOld.getFrameHeight();

        DrawPanel drawPanel = new DrawPanel(xBoundary, yBoundary);
        CarController cc = new CarController(drawPanel, xBoundary, yBoundary);

        CarViewOld carView = new CarViewOld("Den bästa simuleringen", cc, drawPanel, 50);


        cc.add(new Volvo240(0,0));
        cc.add(new Saab95(100,0));
        cc.add(new Scania(200,0));
        // Start a new view and send a reference of self

        // Start the timer
        carView.startTimer();
    }
}
