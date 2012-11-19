package structures;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VectorTest {

    public VectorTest() {
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
    public void data1() throws Exception {
        int[] expResult = {1,2,3};
        Vector instance = new Vector(expResult, 5);
        assertArrayEquals(expResult, instance.toArray());
    }

    @Test
    public void data2() throws Exception {
        int[] expResult = {0,0,1,2,3};
        Vector instance = new Vector(expResult, 5);
        assertArrayEquals(expResult, instance.toArray());
    }

    @Test
    public void data3() throws Exception {
        int[] expResult = {0,0,1,2,3,0,0};
        Vector instance = new Vector(expResult, 5);
        assertArrayEquals(expResult, instance.toArray());
    }

    @Test
    public void data4() throws Exception {
        int[] expResult = {0,0,12,2,3};
        Vector instance = new Vector(expResult, 13);
        assertArrayEquals(expResult, instance.toArray());
    }

    @Test
    (expected= Exception.class) public void data5() throws Exception {
        Vector instance = new Vector(new int[]{0,0,0,0,1,2,13,3}, 13);
    }

    @Test
    public void zeroVector()throws Exception {
        Vector instance = new Vector(5);
        int[] expResult = {0,0,0,0,0};
        assertArrayEquals(expResult, instance.toArray());
    }

    @Test
    public void zeroVector2()throws Exception {
        Vector instance = new Vector(5);
        int[] expResult = {0,0,0,0};
        assertThat(expResult, IsNot.not(IsEqual.equalTo(instance.toArray())));
    }

    /*
    @Test
    (expected= Exception.class) public void primeNumber1() throws Exception {
        Vector instance = new Vector("123", 4);
    }

    @Test
    (expected= Exception.class) public void primeNumber2() throws Exception {
        Vector instance = new Vector("123", 0);
    }

    @Test
    (expected= Exception.class) public void primeNumber3() throws Exception {
        Vector instance = new Vector("123", -2);
    }

    @Test
    public void data1() throws Exception {
        Vector instance = new Vector("123", 5);
        int[] expResult = {1,2,3};
        assertArrayEquals(expResult, instance.getData());
    }

    @Test
    public void data2() throws Exception {
        Vector instance = new Vector("00123", 5);
        int[] expResult = {0,0,1,2,3};
        assertArrayEquals(expResult, instance.getData());
    }

    @Test
    public void data3() throws Exception {
        Vector instance = new Vector("0012300", 5);
        int[] expResult = {0,0,1,2,3,0,0};
        assertArrayEquals(expResult, instance.getData());
    }

    @Test
    public void data4() throws Exception {
        Vector instance = new Vector("0000120203", 13);
        int[] expResult = {0,0,12,2,3};
        assertArrayEquals(expResult, instance.getData());
    }

    @Test
    (expected= Exception.class) public void data5() throws Exception {
        Vector instance = new Vector("000120203", 13);
    }

    @Test
    (expected= Exception.class) public void data6() throws Exception {
        Vector instance = new Vector("0000121303", 13);
    }
    */
}
