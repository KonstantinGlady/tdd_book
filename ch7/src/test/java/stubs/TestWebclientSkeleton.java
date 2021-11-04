package stubs;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestWebclientSkeleton {

    @BeforeAll
    void beforeAll() {
        //start
    }

    @AfterAll
    void tearDown() {
        //stop
    }

    @Test
    @Disabled(value = "This is just the initial skeleton of a test")
    void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost:8081/getContentOk"));
        assertEquals("it works", workingContent);
    }
}
