import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {
    
    private MathUtils mathUtils = new MathUtils();

    @Test
    public void testAdd() {
        assertEquals(5, mathUtils.add(2, 3));
        assertEquals(-1, mathUtils.add(-2, 1));
        assertEquals(0, mathUtils.add(0, 0));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, mathUtils.subtract(3, 2));
        assertEquals(-3, mathUtils.subtract(0, 3));
        assertEquals(0, mathUtils.subtract(5, 5));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, mathUtils.multiply(2, 3));
        assertEquals(0, mathUtils.multiply(0, 5));
        assertEquals(-4, mathUtils.multiply(2, -2));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, mathUtils.divide(4, 2));
        assertEquals(-1.0, mathUtils.divide(5, 0));
        assertEquals(0.5, mathUtils.divide(1, 2));
    }
}