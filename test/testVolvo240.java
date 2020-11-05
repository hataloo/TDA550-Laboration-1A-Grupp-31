import org.junit.Test;
import static org.junit.Assert.*;

public class testVolvo240 {
    @Test
    public  void testConstructor() {
        Volvo240 volvo240 = new Volvo240();
        assertEquals(volvo240.getClass(), Volvo240.class);
    }

    @Test
    public void testSpeedFactor() {
        Volvo240 volvo240 = new Volvo240();
        assertEquals(100 * 0.01 * 1.2, volvo240.speedFactor(), 0.001);
    }
}
