import java.util.ArrayList;

/**
 * The type Car workshop.
 *
 * @param <T> the type parameter
 */
public class CarWorkshop<T extends Car> {
    private ArrayList<T> carsInWorkshop;
    private int capacity;

    /**
     * Instantiates a new Car workshop.
     */
    public CarWorkshop(){
        this.capacity = 10;
        this.carsInWorkshop = new ArrayList<T>(this.capacity);
    }

    /**
     * Instantiates a new Car workshop.
     *
     * @param capacity the capacity
     */
    public CarWorkshop(int capacity){
        this.capacity = capacity;
        this.carsInWorkshop = new ArrayList<T>(capacity);
    }

    /**
     * Get capacity int.
     *
     * @return the int
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Admit car.
     *
     * @param carToAdd the car to add
     */
    public void admitCar(T carToAdd){
        this.carsInWorkshop.add(carToAdd);
    }

    /**
     * Return car from the workshop
     *
     * @param carToGet the car you want to return
     * @return the car
     */
    public T returnCar(T carToGet){
        int indexOfCar = this.carsInWorkshop.indexOf(carToGet);
        return this.carsInWorkshop.get(indexOfCar);
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public void main(String[] args){
        //Used to demonstrate static errors.
        CarWorkshop<SmallCar> mySaabWorkshop = new CarWorkshop<>();
        Saab95 myFavoriteCar = new Saab95();
        Volvo240 mySecondFavoriteCar = new Volvo240();

        mySaabWorkshop.admitCar(myFavoriteCar);
        mySaabWorkshop.admitCar(mySecondFavoriteCar);

        SmallCar myRepairedCar = mySaabWorkshop.returnCar(myFavoriteCar);

    }
}
