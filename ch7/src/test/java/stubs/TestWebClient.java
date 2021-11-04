package stubs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
//Поднимаем полноценный сервер, что бы реализовать stub. Помимо unit тестирования делается integrated тестирование.
//Сложный вариант, можно реализовать просто через конекшен
public class TestWebClient {

    private WebClient client = new WebClient();

    @BeforeAll
    public static void setUp() throws Exception {
        Server server = new Server(8081);

        Context contextOkContent = new Context(server, "/testGetContentOk");
        contextOkContent.setHandler(new TestGetContentOkHandler());

        Context contextGetError = new Context(server, "/testGetContentError");
        contextGetError.setHandler(new TestGetContentErrorHandler());

        server.setStopAtShutdown(true);
        server.start();
    }

    @Test
    public void TestGetContentOk() throws MalformedURLException {
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));
        assertEquals("its works", workingContent);
    }

    private static class TestGetContentOkHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException {

            OutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("its works");
            writer.flush();
            response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
            writer.writeTo(out);
            writer.flush();
        }
    }

    private static class TestGetContentErrorHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}
