package core.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SUTTest {
    private static ResourceForAllTests resourceForAllTests;
    private SUT sut;

    @BeforeAll
    static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("Our resource for all tests");
    }

    @AfterAll
    static void shutDownCass() {
        resourceForAllTests.close();
    }

    @BeforeEach
    void setUp() {
        sut = new SUT("Our system under test");
    }

    @AfterEach
    void tearDown() {
        sut.close();
    }

    @Test
    void testRegularWork() {
        boolean canReceiveRegularWork = sut.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    void testAdditionalWork() {
        boolean canReceiveAdditionalWork = sut.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }
}