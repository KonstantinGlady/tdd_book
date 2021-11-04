package core.repetition;

import core.repeated.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestReporter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RepetitionTestsTest {
    Set<Integer> integerSet = new HashSet<>();
    List<Integer> integerList = new ArrayList<>();

    @RepeatedTest(value = 5, name = "{displayName} - repetition {currentRepetition} / {totalRepetitions}")
    @DisplayName("adding 3 and 1 should be 4")
    void addNumber() {
        Calculator calculator = new Calculator();
        assertEquals(4, calculator.add(3, 1));
    }

    @RepeatedTest(value = 5, name = "the lists contains {currentRepetition} element(s), the set contains 1 element")
    void testAddingToCollection(TestReporter testReporter, RepetitionInfo repetitionInfo) {
        integerSet.add(1);
        integerList.add(repetitionInfo.getCurrentRepetition());

        testReporter.publishEntry("Repetition number", String.valueOf(repetitionInfo.getCurrentRepetition()));
        assertEquals(1, integerSet.size());
        assertEquals(repetitionInfo.getCurrentRepetition(), integerList.size());
    }
}
