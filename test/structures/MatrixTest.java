package structures;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixTest {

    public MatrixTest() {
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
    public void testMatrixCreation() throws Exception {
        Matrix matrix = new Matrix(new int[][]{
                                    {1,2,3,0},
                                    {3,2,3,1},
                                    {4,2,0,4}}, 5);

        assertArrayEquals(matrix.getVector(0).toArray(), new int[]{1,2,3,0});
        assertArrayEquals(matrix.getVector(1).toArray(), new int[]{3,2,3,1});
        assertArrayEquals(matrix.getVector(2).toArray(), new int[]{4,2,0,4});
    }
}
