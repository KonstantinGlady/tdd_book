package core.hamcrestList;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestListTest {
    private List<String> values;

    @BeforeEach
    void setUp() {
        values = new ArrayList<>();
        values.add("John");
        values.add("Michael");
        values.add("Edwin");
    }

    @Test
    @DisplayName("test without hamcrest")
    void testWithoutHamcrest() {
        assertEquals(3, values.size());
        assertTrue(values.contains("John") ||
                values.contains("Harry") ||
                values.contains("Oliver"));
    }

    @Test
    @DisplayName("test with hamcrest")
    void testWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(
                equalTo("John"),
                equalTo("Harry"),
                equalTo("Oliver")
        )));
    }
}
