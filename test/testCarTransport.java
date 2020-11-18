import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class testCarTransport {

    @Test
    public void testConstructor() {
        CarTransport carTransport = new CarTransport();
        assertEquals(CarTransport.class, carTransport.getClass());
    }

    @Test
    public void testCarOkToLoad_distanceToCar(){
        CarTransport carTransport = new CarTransport();
        Saab95 saab95 = new Saab95();

        saab95.startEngine();
        saab95.gas(1);
        saab95.move();

        assertThrows(IllegalArgumentException.class,() -> {carTransport.loadTransportable(saab95);});
    }

    @Test
    public void testCarOkToLoadThrowsExceptionIfFlatbedIsRaised(){
        CarTransport carTransport = new CarTransport();
        Saab95 saab95 = new Saab95();

        carTransport.raiseFlatbed();

        assertThrows(IllegalStateException.class,() -> {carTransport.loadTransportable(saab95);});
    }

    @Test
    public void testUnLoadCarThrowsIllegalStateExceptionWhenEmpty() {
        CarTransport carTransport = new CarTransport();
        Volvo240 volvo = new Volvo240();
        carTransport.loadTransportable(volvo);
        carTransport.unloadTransportable();
        assertThrows(IllegalStateException.class,() ->{carTransport.unloadTransportable();});
    }

    @Test
    public void testUnloadCarOrder() {
        CarTransport carTransport = new CarTransport();
        Saab95 car1 = new Saab95();
        Saab95 car2= new Saab95();

        carTransport.lowerFlatbed();
        carTransport.loadTransportable(car1);
        carTransport.loadTransportable(car2);

        Car car3 = carTransport.unloadTransportable();

        assertEquals(car2.hashCode(), car3.hashCode());
    }

    @Test
    public void testMoveMovesLoadedCar(){
        CarTransport carTransport = new CarTransport();
        Volvo240 volvo = new Volvo240();
        carTransport.loadTransportable(volvo);
        carTransport.startEngine();
        carTransport.move();
        assertEquals(carTransport.getYPosition(),volvo.getYPosition(),0.001);
    }
}
