package mac_williams_method;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import structures.Matrix;

public class DualCodeMatrixFinderTest {

    public DualCodeMatrixFinderTest() {
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
    public void testFind() throws Exception {
        int q = 2;
        Matrix gMatrix = new Matrix(new int[][]{
                                     {1,1,0},
                                     {1,0,1}}, q);
        DualCodeMatrixFinder instance = new DualCodeMatrixFinder();
        Matrix result = instance.find(gMatrix, q);
        int[] expResult = {1,1,1};
        assertEquals(1, result.getRowCount());
        assertArrayEquals(expResult, result.getVector(0).getData());
    }

    @Test
    public void testFind2() throws Exception {
        int q = 3;
        Matrix gMatrix = new Matrix(new int[][]{
                                     {1,1,2,0},
                                     {1,2,1,1}}, q);
        DualCodeMatrixFinder instance = new DualCodeMatrixFinder();
        Matrix result = instance.find(gMatrix, q);
        int[] expResult1 = {0,1,1,0};
        int[] expResult2 = {0,2,2,0};
        assertEquals(2, result.getRowCount());
        assertArrayEquals(expResult1, result.getVector(0).getData());
        assertArrayEquals(expResult2, result.getVector(1).getData());
    }
}
