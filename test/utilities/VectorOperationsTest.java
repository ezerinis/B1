package utilities;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import structures.Matrix;
import structures.Vector;

public class VectorOperationsTest {

    VectorOperations vo = new VectorOperations();

    public VectorOperationsTest() {
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
    public void testMultiply() throws Exception {
        int q = 2;
        Vector vector = new Vector(new int[]{1,1,0}, q);
        Matrix matrix = new Matrix(new int[][]{
                                        {1,1,0,0},
                                        {0,1,1,1},
                                        {1,0,1,0}}, q);
        Vector result = vo.multiply(matrix, vector, q);
        assertArrayEquals(new int[]{1,0,1,1}, result.toArray());
    }

    @Test
    public void testMultiply2() throws Exception {
        int q = 5;
        Vector vector = new Vector(new int[]{3,4,0}, q);
        Matrix matrix = new Matrix(new int[][]{
                                        {3,1,0,3},
                                        {2,1,0,3},
                                        {1,4,2,0}}, q);
        Vector result = vo.multiply(matrix, vector, q);
        assertArrayEquals(new int[]{2,2,0,1}, result.toArray());
    }

    @Test
    public void testScalarMultiply() throws Exception {
        int q = 3;
        Vector vector1 = new Vector(new int[]{2,1,1}, q);
        Vector vector2 = new Vector(new int[]{1,0,0}, q);
        int result = vo.scalarMultiply(vector1, vector2, q);
        assertEquals(result, 2);
    }

}
