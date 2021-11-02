package core.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertAllTest {

    @Test
    @DisplayName("Sut shouldn`t be under current verification")
    void testSystemNotVerified() {
        SUT sut = new SUT("Our system under test");
        assertAll("By default sut is not under current verification",
                () -> assertEquals("Our system under test", sut.getSystemName()),
                () -> assertFalse(sut.isVerified(), () -> "System should be under verification")
        );
    }

    @Test
    @DisplayName("Sut should be under current verification")
    void testSystemUnderVerification() {
        SUT sut = new SUT("Our system under verification");
        sut.verify();
        assertAll("Sut under current verification",
                () -> assertEquals("Our system under verification", sut.getSystemName()),
                () -> assertTrue(sut.isVerified(), () -> "System should not be under verification")
        );
    }

    @Test
    @DisplayName("Sut should not have current job")
    void testCurrentJob() {
        SUT sut = new SUT("Our system under verification");
        assertNull(sut.getCurrentJob(), () -> "Sut should not have current job");
    }
}