package mac_williams_method;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomialOperationsTest {

    PolynomialOperations instance = new PolynomialOperations();

    public PolynomialOperationsTest() {
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
    public void testAdd() {
        Polynomial poly1 = new Polynomial(new Term[]{
                                               new Term(4, 5),
                                               new Term(0, 2),
                                               new Term(-14, 1),
                                               new Term(4, 7),
                                               new Term(6, 0),
                                               new Term(7, 22),
                                               new Term(-11, 4)});
        Polynomial poly2 = new Polynomial(new Term[]{
                                               new Term(9, 5),
                                               new Term(0, 7),
                                               new Term(7, 1),
                                               new Term(-8, 4),
                                               new Term(6, 0),
                                               new Term(7, 12)});
        Polynomial result = instance.add(poly1, poly2);
        assertEquals(8, result.size());
        assertEquals(12, result.getTerm(0).getCof());
        assertEquals(0, result.getTerm(0).getPow());
        assertEquals(-7, result.getTerm(1).getCof());
        assertEquals(1, result.getTerm(1).getPow());
        assertEquals(0, result.getTerm(2).getCof());
        assertEquals(2, result.getTerm(2).getPow());
        assertEquals(-19, result.getTerm(3).getCof());
        assertEquals(4, result.getTerm(3).getPow());
        assertEquals(13, result.getTerm(4).getCof());
        assertEquals(5, result.getTerm(4).getPow());
        assertEquals(4, result.getTerm(5).getCof());
        assertEquals(7, result.getTerm(5).getPow());
        assertEquals(7, result.getTerm(6).getCof());
        assertEquals(12, result.getTerm(6).getPow());
        assertEquals(7, result.getTerm(7).getCof());
        assertEquals(22, result.getTerm(7).getPow());
    }

    @Test
    public void testRaiseToPow() {
        Polynomial poly = new Polynomial(new Term[]{new Term(2, 3), new Term(-1, 1)});
        int pow = 5;
        Polynomial result = instance.raiseToPow(poly, pow);
        assertEquals(6, result.size());
        assertEquals(-1, result.getTerm(0).getCof());
        assertEquals(5, result.getTerm(0).getPow());
        assertEquals(10, result.getTerm(1).getCof());
        assertEquals(7, result.getTerm(1).getPow());
        assertEquals(-40, result.getTerm(2).getCof());
        assertEquals(9, result.getTerm(2).getPow());
        assertEquals(80, result.getTerm(3).getCof());
        assertEquals(11, result.getTerm(3).getPow());
        assertEquals(-80, result.getTerm(4).getCof());
        assertEquals(13, result.getTerm(4).getPow());
        assertEquals(32, result.getTerm(5).getCof());
        assertEquals(15, result.getTerm(5).getPow());
    }

    @Test
    public void testMul() {
        Polynomial poly1 = new Polynomial(new Term[]{new Term(2, 3), new Term(-1, 1)});
        Polynomial poly2 = new Polynomial(new Term[]{new Term(3, 2), new Term(1, 3)});
        Polynomial result = instance.mul(poly1, poly2);
        assertEquals(4, result.size());
        assertEquals(-3, result.getTerm(0).getCof());
        assertEquals(3, result.getTerm(0).getPow());
        assertEquals(-1, result.getTerm(1).getCof());
        assertEquals(4, result.getTerm(1).getPow());
        assertEquals(6, result.getTerm(2).getCof());
        assertEquals(5, result.getTerm(2).getPow());
        assertEquals(2, result.getTerm(3).getCof());
        assertEquals(6, result.getTerm(3).getPow());
    }

    @Test
    public void testDiv() {
        Polynomial poly1 = new Polynomial(new Term[]{new Term(24, 3), new Term(-8, 1), new Term(4, 6)});
        int denominator = 4;
        Polynomial result = instance.div(poly1, 4);
        assertEquals(3, result.size());
        assertEquals(6, result.getTerm(0).getCof());
        assertEquals(3, result.getTerm(0).getPow());
        assertEquals(-2, result.getTerm(1).getCof());
        assertEquals(1, result.getTerm(1).getPow());
        assertEquals(1, result.getTerm(2).getCof());
        assertEquals(6, result.getTerm(2).getPow());
    }
}
