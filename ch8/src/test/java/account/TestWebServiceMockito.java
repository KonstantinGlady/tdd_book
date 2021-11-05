package account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import web.ConnectionFactory;
import web.WebClient2;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestWebServiceMockito {

    @Mock
    ConnectionFactory factory;

    @Mock
    InputStream stream;

    @Test
    public void testGetContentOk() throws Exception {
        when(factory.getData()).thenReturn(stream);
        when(stream.read()).thenReturn((int) 'W')
                .thenReturn((int) 'o')
                .thenReturn((int) 'r')
                .thenReturn((int) 'k')
                .thenReturn((int) 's')
                .thenReturn((int) '!')
                .thenReturn(-1);

        WebClient2 client2 = new WebClient2();
        String workingContent = client2.getContent(factory);
        assertEquals("Works!", workingContent);
    }

    @Test
    public void getContentCannotClose() throws Exception {
        when(factory.getData()).thenReturn(stream);
        when(stream.read()).thenReturn(-1);
        doThrow(new IOException("cannot close")).when(stream).close();

        WebClient2 client2 = new WebClient2();
        String workingContent = client2.getContent(factory);
        assertNull(workingContent);
    }
}
