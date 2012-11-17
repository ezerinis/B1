package mac_williams_method;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import structures.Matrix;

public class ControlMatrixFinderTest {

    public ControlMatrixFinderTest() {
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
        int q = 5;
        Matrix gMatrix = new Matrix(new int[][]{
                                     {1,0,0,2,2},
                                     {0,1,0,1,3},
                                     {0,0,1,0,4}}, q);
        ControlMatrixFinder instance = new ControlMatrixFinder();
        Matrix result = instance.findControlMatrix(gMatrix, q);
        assertEquals(2, result.getRowCount());
        assertArrayEquals(new int[]{3,4,0,1,0}, result.getVector(0).getData());
        assertArrayEquals(new int[]{3,2,1,0,1}, result.getVector(1).getData());
    }
}
