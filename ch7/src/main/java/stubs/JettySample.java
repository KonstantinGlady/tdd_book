package stubs;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

//http://localhost:8081
public class JettySample {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8081);
        Context root = new Context(server, "/");

        root.setResourceBase("./pom.xml");
        root.setHandler(new ResourceHandler());

        server.setStopAtShutdown(true);
        server.start();
    }
}
