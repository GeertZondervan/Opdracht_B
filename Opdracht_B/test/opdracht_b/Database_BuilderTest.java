package opdracht_b;

import opdracht_b.pojo.TestKlasse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.internal.runners.TestClass;

/**
 *
 * @author Geert
 */
public class Database_BuilderTest {

    private TestKlasse testClass;
    private Database_Builder dbBuilder = new Database_Builder();

    public Database_BuilderTest() {
    }

    @Before
    public void setUp() {
        testClass = new TestKlasse();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createTable method, of class Database_Builder.
     */
    @Test
    public void testCreateTable() {
        dbBuilder.createTable(testClass);
    }

    /**
     * Test of buildCreateTableStatememt method, of class Database_Builder.
     */
//    @Test
//    public void testBuildCreateTableStatememt() {
//        System.out.println("buildCreateTableStatememt");
//        Object object = null;
//        Database_Builder instance = new Database_Builder();
//        String expResult = "";
//        String result = instance.buildCreateTableStatememt(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkForPrimaryKey method, of class Database_Builder.
//     */
//    @Test
//    public void testCheckForPrimaryKey() {
//        System.out.println("checkForPrimaryKey");
//        Object object = null;
//        Database_Builder instance = new Database_Builder();
//        boolean expResult = false;
//        boolean result = instance.checkForPrimaryKey(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
