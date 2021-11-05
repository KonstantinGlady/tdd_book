package web;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class TestWebClientJMock {

    @RegisterExtension
    Mockery context = new JUnit5Mockery() {
        {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        }
    };


    @Test
    public void testGetContentOk() throws Exception {
        ConnectionFactory factory = context.mock(ConnectionFactory.class);
        InputStream stream = context.mock(InputStream.class);

        context.checking(new Expectations() {
            {
                oneOf(factory).getData();
                will(returnValue(stream));

                atLeast(1).of(stream).read();
                will(onConsecutiveCalls(returnValue(Integer.valueOf((byte) 'W')),
                        returnValue(Integer.valueOf((byte) 'o')),
                        returnValue(Integer.valueOf((byte) 'r')),
                        returnValue(Integer.valueOf((byte) 'k')),
                        returnValue(Integer.valueOf((byte) 's')),
                        returnValue(Integer.valueOf((byte) '!')),
                        returnValue(-1)
                ));
                oneOf(stream).close();
            }
        });


        WebClient2 client2 = new WebClient2();
        String workingContent = client2.getContent(factory);
        assertEquals("Works!", workingContent);
    }

    @Test
    public void TestGetContentCannotClose() throws Exception {

        ConnectionFactory factory = context.mock(ConnectionFactory.class);
        InputStream stream = context.mock(InputStream.class);

        context.checking(new Expectations() {
            {
                oneOf(factory).getData();
                will(returnValue(stream));
                oneOf(stream).read();
                will(returnValue(-1));
                oneOf(stream).close();
                will(throwException(new IOException("cannot close")));
            }
        });
        WebClient2 client2 = new WebClient2();
        String workContent = client2.getContent(factory);
        assertNull(workContent);
    }

}