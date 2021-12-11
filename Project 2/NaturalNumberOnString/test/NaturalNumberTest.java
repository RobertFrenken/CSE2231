import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Robert Frenken
 * @author Bennett Palmer
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // 1 constructor test no argument empty
    // 3 constructor tests string
    // 3 constructor tests ints
    // 3 constructor tests nn
    // 3 constructor tests multiplyBy10 (empty, routine, > int MAX)
    // 3 constructor tests divideBy10 (empty, routine, > int MAX)
    // 3 constructor tests isZero (true, false, > int MAX)
    // total: >19 tests

    @Test
    public final void testConstructorNoArgumentEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorStringZero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorStringRoutine() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("23");
        NaturalNumber nExpected = this.constructorRef("23");
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorStringIntMax() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("1234567891011121314");
        NaturalNumber nExpected = this.constructorRef("1234567891011121314");
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorIntBoundary() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorIntRoutine() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(2300);
        NaturalNumber nExpected = this.constructorRef(2300);
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorIntMax() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorNNBoundary() {
        /*
         * Set up variables
         */

        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber n = this.constructorTest(nExpected);
        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorNNRoutine() {
        /*
         * Set up variables
         */
        NaturalNumber nExpected = this.constructorRef(12340);
        NaturalNumber n = this.constructorTest(nExpected);

        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testConstructorNNIntMax() {
        /*
         * Set up variables
         */
        NaturalNumber nExpected = this.constructorRef("1234567891011121314");
        NaturalNumber n = this.constructorTest(nExpected);

        /*
         * Call method under test
         */

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testMultiplyBy10Boundary() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(6);
        /*
         * Call method under test
         */
        n.multiplyBy10(6);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testMultiplyBy10Routine() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(1234);
        NaturalNumber nExpected = this.constructorRef(12345);
        /*
         * Call method under test
         */
        n.multiplyBy10(5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testMultiplyBy10IntMax() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("12345678910");
        NaturalNumber nExpected = this.constructorRef("123456789109");
        /*
         * Call method under test
         */
        n.multiplyBy10(9);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
    }

    @Test
    public final void testDivideBy10Boundary() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("9");
        NaturalNumber nExpected = this.constructorRef("0");
        /*
         * Call method under test
         */
        int remainder = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
        assertEquals(remainder, 9);
    }

    @Test
    public final void testDivideBy10Routine() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("1234");
        NaturalNumber nExpected = this.constructorRef("123");
        /*
         * Call method under test
         */
        int remainder = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
        assertEquals(remainder, 4);
    }

    @Test
    public final void testDivideBy10IntMax() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("12345678910");
        NaturalNumber nExpected = this.constructorRef("1234567891");
        /*
         * Call method under test
         */
        int remainder = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
        assertEquals(remainder, 0);
    }

    @Test
    public final void testIsZeroTrue() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        /*
         * Call method under test
         */
        boolean bool = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
        assertEquals(bool, true);
    }

    @Test
    public final void testIsZeroFalse() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("3");
        NaturalNumber nExpected = this.constructorRef("3");
        /*
         * Call method under test
         */
        boolean bool = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
        assertEquals(bool, false);
    }

    @Test
    public final void testIsZeroFalseIntMax() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("12345678910");
        NaturalNumber nExpected = this.constructorRef("12345678910");
        /*
         * Call method under test
         */
        boolean bool = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n, nExpected);
        assertEquals(bool, false);
    }

}
