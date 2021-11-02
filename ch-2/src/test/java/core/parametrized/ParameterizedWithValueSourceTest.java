package core.parametrized;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedWithValueSourceTest {
    private WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
    @ValueSource(strings = {"check three parameters", "Junit in action"})
    void testWordsInSentence(String source) {
        assertEquals(3, wordCounter.countWords(source));
    }
}