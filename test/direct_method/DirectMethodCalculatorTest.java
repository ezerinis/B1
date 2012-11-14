package direct_method;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import structures.Vector;

public class DirectMethodCalculatorTest {

    public DirectMethodCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCalculateDistribution() throws Exception {
        Vector[] code = new Vector[]{
            new Vector(new int[]{1,1,0,1,0},2),
            new Vector(new int[]{0,1,0,1,0},2),
            new Vector(new int[]{1,0,1,0,1},2),
            new Vector(new int[]{0,0,0,0,0},2),
            new Vector(new int[]{1,1,1,1,1},2)
        };
        DirectMethodCalculator instance = new DirectMethodCalculator();
        int[] expResult = new int[]{1,0,1,2,0,1};
        int[] result = instance.calculateDistribution(code);
        assertArrayEquals(expResult, result);
    }
}
