import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class testScania {

    @Test
    public void testConstructor() {
        Scania scania = new Scania();
        assertEquals(Scania.class, scania.getClass());
    }

    @Test
    public void testSetFlatbedAngle(){
        Scania scania = new Scania();
        scania.setFlatbedAngle(70);
        assertEquals(70,scania.getFlatbedAngle(),0.001);
    }

    @Test
    public void testSetFlatbedAngleThrowsIllegalArgumentException(){
        Scania scania = new Scania();
        assertThrows(IllegalArgumentException.class,() -> {scania.setFlatbedAngle(80);});
    }

    @Test
    public void testSetFlatbedAngleThrowsIllegalStateException(){
        Scania scania = new Scania();
        scania.startEngine();
        assertThrows(IllegalStateException.class,() -> {scania.setFlatbedAngle(10);});
    }

    @Test
    public void testSpeedFactor(){
        Scania scania = new Scania();
        assertEquals(90*0.01,scania.speedFactor(),0.001);
    }
}
