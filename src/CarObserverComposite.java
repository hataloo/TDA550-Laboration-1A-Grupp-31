import java.util.ArrayList;
import java.util.List;

public class CarObserverComposite implements CarObserver{
    private List<CarObserver> carObservers;

    public CarObserverComposite() {
        this.carObservers = new ArrayList<>();
    }


    public void add(CarObserver c){
        carObservers.add(c);
    }

    public void remove(CarObserver c) {
        carObservers.remove(c);
    }

    public void actOnVehicleMovement(List<VehicleImage> vehicleImages){
        for (CarObserver carObserver : carObservers) {
            carObserver.actOnVehicleMovement(vehicleImages);
        }
    }

}
