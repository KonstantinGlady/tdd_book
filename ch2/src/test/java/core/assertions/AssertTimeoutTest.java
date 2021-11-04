package core.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AssertTimeoutTest {
    private SUT sut = new SUT("Our system under test");

    @Test
    @DisplayName("A test executed with timeout")
    void testTimeout() throws InterruptedException {
        sut.addJob(new Job("Job 1"));
        assertTimeout(Duration.ofMillis(500), () -> sut.run(200));
    }

    @Test
    @DisplayName("A test executed preemptively with timeout")
    void testPreemptivelyTimeout() {
        sut.addJob(new Job("Job 1"));
        //stop the executable when the time has expired
        assertTimeoutPreemptively(Duration.ofMillis(500), () -> sut.run(200));
    }
}