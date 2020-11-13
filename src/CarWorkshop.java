import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The type Car workshop.
 *
 * @param <T> the type parameter
 */
public class CarWorkshop<T extends Car> implements Transporter<T>{
    private final LinkedList<T> carsInWorkshop;
    private final int capacity;

    /**
     * Instantiates a new Car workshop.
     */
    public CarWorkshop(){
        this.capacity = 10;
        this.carsInWorkshop = new LinkedList<T>();
    }

    /**
     * Instantiates a new Car workshop.
     *
     * @param capacity the capacity
     */
    public CarWorkshop(int capacity){
        this.capacity = capacity;
        this.carsInWorkshop = new LinkedList<T>();
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
        this.carsInWorkshop.push(carToAdd);
    }

    @Override
    public void loadTransportable(T carToLoad) {
        if(carToLoad.getIsLoadedOntoTransporter()){
            throw new IllegalArgumentException("The input Car is already loaded onto a transporter");
        }
        else if(this.capacity <= this.carsInWorkshop.size()){
            throw new IllegalStateException("The workshop is full, try unloading first.");
        } else {
            this.carsInWorkshop.add(carToLoad);
            carToLoad.setIsLoadedOntoTransporter(true);
        }
    }

    @Override
    public T unloadTransportable() {
        if(this.carsInWorkshop.isEmpty()){
            throw new IllegalStateException("The workshop is empty.");
        }
        T unloadedCar = this.carsInWorkshop.removeLast();
        unloadedCar.setIsLoadedOntoTransporter(false);
        return unloadedCar;
    }

//    /**
//     * Return car from the workshop
//     *
//     * @param carToGet the car you want to return
//     * @return the car
//     */
//    public T returnCar(T carToGet){
//        int indexOfCar = this.carsInWorkshop.indexOf(carToGet);
//        return this.carsInWorkshop.get(indexOfCar);
//    }

//    /**
//     * Main.
//     *
//     * @param args the args
//     */
//    public void main(String[] args){
//        //Used to demonstrate static errors.
//        CarWorkshop<SmallCar> mySaabWorkshop = new CarWorkshop<>();
//        Saab95 myFavoriteCar = new Saab95();
//        Volvo240 mySecondFavoriteCar = new Volvo240();
//
//        mySaabWorkshop.admitCar(myFavoriteCar);
//        mySaabWorkshop.admitCar(mySecondFavoriteCar);
//
//        SmallCar myRepairedCar = mySaabWorkshop.returnCar(myFavoriteCar);
//
//    }
}
