package mac_williams_method;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomialTest {

    public PolynomialTest() {
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
    public void testNormalizeSort() {
        Polynomial instance = new Polynomial(new Term[]{
                                                  new Term(4,4),
                                                  new Term(1,3),
                                                  new Term(8,2),
                                                  new Term(3,1),
                                                  new Term(4,0)});
        instance.normalize();
        assertEquals(5, instance.size());
        assertEquals(0, instance.getTerm(0).getPow());
        assertEquals(1, instance.getTerm(1).getPow());
        assertEquals(2, instance.getTerm(2).getPow());
        assertEquals(3, instance.getTerm(3).getPow());
        assertEquals(4, instance.getTerm(4).getPow());
    }

    @Test
    public void testNormalize() {
        Polynomial instance = new Polynomial(new Term[]{
                                                  new Term(4,3),
                                                  new Term(1,1),
                                                  new Term(8,0),
                                                  new Term(3,3),
                                                  new Term(-1,3),
                                                  new Term(4,0)});
        instance.normalize();
        assertEquals(3, instance.size());
        assertEquals(0, instance.getTerm(0).getPow());
        assertEquals(1, instance.getTerm(1).getPow());
        assertEquals(3, instance.getTerm(2).getPow());
        assertEquals(12, instance.getTerm(0).getCof());
        assertEquals(1, instance.getTerm(1).getCof());
        assertEquals(6, instance.getTerm(2).getCof());
    }
}
