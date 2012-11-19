package utilities;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import structures.Matrix;

public class MatrixOperationsTest {

    MatrixOperations mo = new MatrixOperations();

    public MatrixOperationsTest() {
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
    public void testSplit() throws Exception {
        Matrix inputMatrix = new Matrix(new int[][]{
                                             {1,0,0,2,2},
                                             {0,1,0,1,3},
                                             {0,0,1,0,4}}, 5);
        Matrix result = mo.split(inputMatrix, 3);
        assertArrayEquals(new int[]{2,2}, result.getVector(0).toArray());
        assertArrayEquals(new int[]{1,3}, result.getVector(1).toArray());
        assertArrayEquals(new int[]{0,4}, result.getVector(2).toArray());
    }

    @Test
    public void testTranspose() throws Exception {
        Matrix inputMatrix = new Matrix(new int[][]{
                                             {2,2},
                                             {1,3},
                                             {0,4}}, 5);
        Matrix result = mo.transpose(inputMatrix);
        assertArrayEquals(new int[]{2,1,0}, result.getVector(0).toArray());
        assertArrayEquals(new int[]{2,3,4}, result.getVector(1).toArray());
    }

    @Test
    public void testMakeNegative() throws Exception {
        Matrix inputMatrix = new Matrix(new int[][]{
                                             {2,1,0},
                                             {2,3,4}}, 5);
        Matrix result = mo.makeNegative(inputMatrix, 5);
        assertArrayEquals(new int[]{3,4,0}, result.getVector(0).toArray());
        assertArrayEquals(new int[]{3,2,1}, result.getVector(1).toArray());
    }

    @Test
    public void testJoin() throws Exception {
        Matrix matrix1 = new Matrix(new int[][]{
                                         {4,3,1,4,3},
                                         {3,1,2,0,4},
                                         {4,1,4,2,4}}, 5);
        Matrix matrix2 = new Matrix(new int[][]{
                                         {3,4},
                                         {2,0},
                                         {0,4}}, 5);
        Matrix result = mo.join(matrix1, matrix2);
        assertArrayEquals(new int[]{4,3,1,4,3,3,4}, result.getVector(0).toArray());
        assertArrayEquals(new int[]{3,1,2,0,4,2,0}, result.getVector(1).toArray());
        assertArrayEquals(new int[]{4,1,4,2,4,0,4}, result.getVector(2).toArray());
    }
}
