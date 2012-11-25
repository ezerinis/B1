package mac_williams_method;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MacWilliamsMethodCalculatorTest {

    public MacWilliamsMethodCalculatorTest() {
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
    public void testCalculateDistribution() {
        long[] dualDistribution = new long[]{1,0,1,1,0,1};
        int n = 5;
        int q = 2;
        MacWilliamsMethodCalculator instance = new MacWilliamsMethodCalculator();
        long[] expResult = new long[]{1,0,4,0,3,0};
        long[] result = instance.calculateDistribution(dualDistribution, n, q);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testCalculateDistribution2() {
        long[] dualDistribution = new long[]{1,0,1,0};
        int n = 3;
        int q = 2;
        MacWilliamsMethodCalculator instance = new MacWilliamsMethodCalculator();
        long[] expResult = new long[]{1,1,1,1};
        long[] result = instance.calculateDistribution(dualDistribution, n, q);
        assertArrayEquals(expResult, result);
    }
}
