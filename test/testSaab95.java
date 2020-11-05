import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class testSaab95 {

    @Test
    public void testConstructor(){
        Saab95 saab95 = new Saab95();
        assertEquals(Saab95.class, saab95.getClass());
    }

    @Test
    public void testSetColor(){
        Saab95 saab95 = new Saab95();
        saab95.setColor(Color.blue);
        assertEquals(Color.blue,saab95.getColor());
    }

    @Test
    public void testGetColor(){
        Saab95 saab95 = new Saab95();
        assertEquals(Color.red,saab95.getColor());
    }

    @Test
    public void testStartEngine(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        assertEquals(0.1,saab95.currentSpeed,0.001);
    }
    @Test
    public void testTurboOn(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        saab95.setTurboOn();
        assertEquals(125*0.01*1.3, saab95.speedFactor(),0.001);
    }
    @Test
    public void testTurboOff(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        saab95.setTurboOn();
        saab95.setTurboOff();
        assertEquals(125*0.01*1, saab95.speedFactor(),0.001);
    }
    @Test
    public void testGetNrDoors(){
        Saab95 saab95 = new Saab95();
        assertEquals(saab95.getNrDoors(),2);
    }
    @Test
    public void testEnginePower(){
        Saab95 saab95 = new Saab95();
        assertEquals(125,saab95.getEnginePower(),0.001);
    }
    @Test
    public void testGetCurrentSpeed(){
        Saab95 saab95 = new Saab95();
        assertEquals(0,saab95.getCurrentSpeed(),0.01);
    }
    @Test
    public void testDirection(){
        Saab95 saab95 = new Saab95();
        assertEquals(0,saab95.direction);
    }
    @Test
    public void testTurnLeft(){
        Saab95 saab95 = new Saab95();
        saab95.turnLeft();
        assertEquals(3,saab95.direction);
    }
    @Test
    public void testTurnRight(){
        Saab95 saab95 = new Saab95();
        saab95.turnRight();
        assertEquals(1,saab95.direction);
    }
    @Test
    public void testMove(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        saab95.move();
        assertEquals(0.1,saab95.yPosition,0.01);
    }
    @Test
    public void testGas(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        saab95.gas(1);
        assertEquals(0.1+125*1*0.01,saab95.getCurrentSpeed(),0.001);
    }

    @Test
    public void testBrake(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        saab95.brake(1);
        assertEquals(0,saab95.getCurrentSpeed(),0.001);
    }
}
