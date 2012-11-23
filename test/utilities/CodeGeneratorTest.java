package utilities;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import structures.Matrix;
import structures.Vector;

public class CodeGeneratorTest {

    public CodeGeneratorTest() {
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
    public void testGenerate() throws Exception {
        int q = 2;
        Matrix gMatrix = new Matrix(new int[][]{
                                     {1,1,0,0},
                                     {0,1,1,1}}, q);
        CodeGenerator instance = new CodeGenerator(gMatrix, q);
        instance.execute();
        Vector[] result = instance.get();
        int[] expResult0 = {0,0,0,0};
        assertArrayEquals(expResult0, result[0].toArray());
        int[] expResult1 = {1,1,0,0};
        assertArrayEquals(expResult1, result[1].toArray());
        int[] expResult2 = {0,1,1,1};
        assertArrayEquals(expResult2, result[2].toArray());
        int[] expResult3 = {1,0,1,1};
        assertArrayEquals(expResult3, result[3].toArray());
    }
}
