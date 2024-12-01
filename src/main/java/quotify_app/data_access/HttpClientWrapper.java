package quotify_app.data_access;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A wrapper class for {@link HttpClient} to abstract its functionality.
 */
public class HttpClientWrapper {
    private final HttpClient client;

    public HttpClientWrapper() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Sends an HTTP request and retrieves the response.
     *
     * @param request The {@link HttpRequest} to send.
     * @return The {@link HttpResponse} containing the response.
     * @throws IOException If an I/O error occurs when sending or receiving.
     * @throws InterruptedException If the operation is interrupted.
     */
    public HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
