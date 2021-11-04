package core.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertThrowsTest {
    private SUT sut = new SUT("Our system under test");

    @Test
    @DisplayName("An Exception expected")
    void testExceptionExpected() {
        assertThrows(NoJobException.class, sut::run);
    }

    @Test
    @DisplayName("an exception caught")
    void testCatchException() {
        Throwable throwable = assertThrows(NoJobException.class, () -> sut.run(1000));
        assertEquals("No jobs on the execution list!",throwable.getMessage());
    }

}