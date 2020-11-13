import org.junit.Test;

import javax.swing.text.StyleContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class testCarWorkshop {


    @Test
    public void testConstructor() {
        CarWorkshop<Car> carWorkshop = new CarWorkshop<>();
        assertEquals(CarWorkshop.class, carWorkshop.getClass());
    }

    @Test
    public void testCapacity() {
        int capacity = 7;
        CarWorkshop<Car> carWorkshop = new CarWorkshop<>(capacity);
        assertEquals(capacity, carWorkshop.getCapacity());
    }

    @Test
    public void testAdmitCarAndReturnCar(){
        CarWorkshop<Saab95> carWorkshop = new CarWorkshop<>();
        Saab95 saab95 = new Saab95();
        Saab95 saab95theSecond = new Saab95();
        carWorkshop.admitCar(saab95);
        carWorkshop.admitCar(saab95theSecond);
        assertEquals(carWorkshop.unloadTransportable(),saab95);
    }

    @Test
    public void testAdmitMultipleCarTypes() {
        CarWorkshop<SmallCar> carWorkshop = new CarWorkshop<>();
        Saab95 saab95 = new Saab95();
        Volvo240 volvo240 = new Volvo240();

        carWorkshop.admitCar(saab95);
        carWorkshop.admitCar(volvo240);

        assertEquals(saab95, carWorkshop.unloadTransportable());
    }
}
