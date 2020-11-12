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

        assertThrows(IllegalArgumentException.class,() -> {carTransport.loadCar(saab95);});
    }

    @Test
    public void testCarOkToLoad_flatbedRaised(){
        CarTransport carTransport = new CarTransport();
        Saab95 saab95 = new Saab95();

        carTransport.raiseFlatbed();

        assertThrows(IllegalStateException.class,() -> {carTransport.loadCar(saab95);});
    }

    @Test
    public void testUnLoadCar() {
        CarTransport carTransport = new CarTransport();
        Volvo240 volvo = new Volvo240();
        carTransport.loadCar(volvo);
        carTransport.unloadCar();
        assertThrows(IllegalStateException.class,() ->{carTransport.unloadCar();});
    }

    @Test
    public void testUnloadCarOrder() {
        CarTransport carTransport = new CarTransport();
        Saab95 car1 = new Saab95();
        Saab95 car2= new Saab95();

        carTransport.lowerFlatbed();
        carTransport.loadCar(car1);
        carTransport.loadCar(car2);

        SmallCar car3 = carTransport.unloadCar();

        assertEquals(car2.hashCode(), car3.hashCode());
    }

    @Test
    public void testMoveMovesLoadedCar(){
        CarTransport carTransport = new CarTransport();
        Volvo240 volvo = new Volvo240();
        carTransport.loadCar(volvo);
        carTransport.startEngine();
        carTransport.move();
        assertEquals(carTransport.yPosition,volvo.yPosition,0.001);
    }
}
