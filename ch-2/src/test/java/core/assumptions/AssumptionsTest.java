package core.assumptions;

import core.assumptions.environment.JavaSpecification;
import core.assumptions.environment.OperationSystem;
import core.assumptions.environment.TestsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionsTest {
    private static String EXPECTED_JAVA_VERSION = "1.8";
    private TestsEnvironment environment = new TestsEnvironment(
            new JavaSpecification(
                    System.getProperty("java.vm.specification.version")),
            new OperationSystem(
                    System.getProperty("os.name"),
                    System.getProperty("os.arch"))
    );
    SUT sut = new SUT();

    @BeforeEach
    void beforeEach() {
        assumeTrue(environment.isWindows());
    }

    @Test
    void testNoJobToRun() {
        assumingThat(() -> environment.getJavaVersion()
                        .equals(EXPECTED_JAVA_VERSION),
                () -> assertFalse(sut.hasJobToRun()));
    }

    @Test
    void testJobToRun() {
        assumeTrue(environment.isAmd64Architecture());
        sut.run(new Job());
        assertTrue(sut.hasJobToRun());
    }
}
