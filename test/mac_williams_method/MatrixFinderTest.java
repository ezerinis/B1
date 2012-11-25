package mac_williams_method;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import structures.Matrix;

public class MatrixFinderTest {

    MatrixFinder instance = new MatrixFinder();

    public MatrixFinderTest() {
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
        Matrix result = instance.findControlMatrix(gMatrix, q);
        assertEquals(2, result.getRowCount());
        assertArrayEquals(new int[]{3,4,0,1,0}, result.getVector(0).toArray());
        assertArrayEquals(new int[]{3,2,1,0,1}, result.getVector(1).toArray());
    }

    @Test
    public void testFindStandardMatrix() throws Exception {
        int q = 5;
        Matrix gMatrix = new Matrix(new int[][]{
                                     {4,3,1,4,3},
                                     {3,1,2,0,4},
                                     {4,1,4,2,4}}, q);
        Matrix result = instance.findStandardMatrix(gMatrix, q);
        assertEquals(3, result.getRowCount());
        int[] expResult0 = {1,0,0,2,2};
        assertArrayEquals(expResult0, result.getVector(0).toArray());
        int[] expResult1 = {0,1,0,1,3};
        assertArrayEquals(expResult1, result.getVector(1).toArray());
        int[] expResult2 = {0,0,1,0,4};
        assertArrayEquals(expResult2, result.getVector(2).toArray());
    }
}
