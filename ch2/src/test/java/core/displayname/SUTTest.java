package core.displayname;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//@Disabled("Class yet under construction")
@DisplayName("Test name showing anotation @DisplayName")
class SUTTest {
    private SUT sut = new SUT();

    @Test
    //@Disabled("method under construction")
    @DisplayName("Our system under test say hello")
    void hello() {
        String result = sut.hello();
        String expected = "Hello";
        assertEquals(expected, result);
    }

    @Test
    void bye() {
        assertEquals("Bye", sut.bye());
    }

    @Test
    @DisplayName("checking conversation")
    void talk() {
        assertEquals("How are you?", sut.talk());
    }
}