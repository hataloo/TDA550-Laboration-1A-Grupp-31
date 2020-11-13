import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class testCarFerry {

    @Test
    public void testConstructor(){
        CarFerry carferry = new CarFerry();
        assertEquals(CarFerry.class,carferry.getClass());
    }

    @Test
    public void testConstructorWithCapacity() {
        int capacity = 3;
        CarFerry carFerry = new CarFerry(capacity);

        assertEquals(carFerry.getClass(), CarFerry.class);
    }

    @Test
    public void testCapacity(){
        CarFerry carferry = new CarFerry();
        assertEquals(20,carferry.getCapacity());
    }

    @Test
    public void testCapacity_argument(){
        int cap = 1000;
        CarFerry carferry = new CarFerry(cap);
        assertEquals(cap,carferry.getCapacity());
    }


    @Test
    public void testUnLoadCarThrowsIllegalStateExceptionWhenEmpty() {
        CarFerry carFerry = new CarFerry();
        Volvo240 volvo = new Volvo240();
        carFerry.loadTransportable(volvo);
        carFerry.unloadTransportable();
        assertThrows(IllegalStateException.class,() ->{carFerry.unloadTransportable();});
    }

    @Test public void testMoveMovesLoadedCar(){
        CarFerry carFerry = new CarFerry();
        Scania scania = new Scania();
        carFerry.loadTransportable(scania);
        carFerry.startEngine();
        carFerry.move();
        assertEquals(carFerry.getYPosition(),scania.getYPosition(),0.0001);
    }

}
