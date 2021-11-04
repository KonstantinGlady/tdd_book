package web;

import java.io.InputStream;

public interface ConnectionFactory {
    /**
     * Read the data from the connection.
     *
     * @return
     * @throws Exception
     */
    InputStream getData() throws Exception;
}