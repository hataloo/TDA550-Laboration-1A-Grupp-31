public class CarFerry implements Transporter<Car> {

    @Override
    public void loadTransportable(Car toBeLoaded) {
    }

    @Override
    public SmallCar unloadTransportable(Car toBeUnloaded) {
        return null;
    }
}
