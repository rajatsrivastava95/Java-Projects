import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class MyCLIParserTest extends TestCase {

    ByteArrayOutputStream outContent;
    PrintStream originalOut;

    /**
     * Prepare to capture command line output.
     */
    public void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test addition with 3 numbers.
     */
    public void testAdd00() {

        // Perform test.
        MyCLIParser.main(new String[]{"-add", "2", "4", "8"});
        assertTrue(outContent.toString().matches("14\\r?\\n"));

    }

    /**
     * Test addition with 1 number.
     */
    public void testAdd01() {
        assertFalse("TODO", true);
    }

    /**
     * Test addition with no arguments.
     */
    public void testAdd02() {
        assertFalse("TODO", true);
    }

    /**
     * Test addition with non-int argument.
     */
    public void testAdd03() {
        assertFalse("TODO", true);
    }

    /**
     * Test subtraction with 2 numbers.
     */
    public void testSub00() {
        assertFalse("TODO", true);
    }

    /**
     * Test subtraction with 1 number.
     */
    public void testSub01() {
        assertFalse("TODO", true);
    }

    /**
     * Test subtraction with no arguments.
     */
    public void testSub02() {
        assertFalse("TODO", true);
    }

    /**
     * Test subtraction with non-int argument.
     */
    public void testSub03() {
        assertFalse("TODO", true);
    }

    /**
     * Test multiplication with 3 numbers.
     */
    public void testMul00() {
        assertFalse("TODO", true);
    }

    /**
     * Test multiplication with 1 number.
     */
    public void testMul01() {
        assertFalse("TODO", true);
    }

    /**
     * Test multiplication with no arguments.
     */
    public void testMul02() {
        assertFalse("TODO", true);
    }

    /**
     * Test multiplication with non-int argument.
     */
    public void testMul03() {
        assertFalse("TODO", true);
    }

    /**
     * Test division with 2 numbers.
     */
    public void testDiv00() {
        assertFalse("TODO", true);
    }

    /**
     * Test division with 1 number.
     */
    public void testDiv01() {
        assertFalse("TODO", true);
    }

    /**
     * Test division with no arguments.
     */
    public void testDiv02() {
        assertFalse("TODO", true);
    }

    /**
     * Test division with non-int argument.
     */
    public void testDiv03() {
        assertFalse("TODO", true);
    }

    /**
     * Test division with a divisor of 0.
     */
    public void testDiv04() {
        assertFalse("TODO", true);
    }

    /**
     * Test statistics with 3 numbers.
     */
    public void testStats00() {
        assertFalse("TODO", true);
    }

    /**
     * Test statistics with 1 number.
     */
    public void testStats01() {
        assertFalse("TODO", true);
    }

    /**
     * Test statistics with no arguments.
     */
    public void testStats02() {
        assertFalse("TODO", true);
    }

    /**
     * Test statistics with non-int argument.
     */
    public void testStats03() {
        assertFalse("TODO", true);
    }

    /**
     * Test seq with 2 numbers.
     */
    public void testSeq00() {
        assertFalse("TODO", true);
    }

    /**
     * Test seq with no arguments.
     */
    public void testSeq01() {
        assertFalse("TODO", true);
    }

    /**
     * Test seq with non-int argument.
     */
    public void testSeq02() {
        assertFalse("TODO", true);
    }

    public void tearDown() {
        // Clean up console I/O.
        System.setOut(originalOut);
    }

}
