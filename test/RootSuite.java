import direct_method.Direct_methodSuite;
import mac_williams_method.Mac_williams_methodSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import structures.StructuresSuite;
import utilities.UtilitiesSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({Direct_methodSuite.class, UtilitiesSuite.class, Mac_williams_methodSuite.class, StructuresSuite.class})
public class RootSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
