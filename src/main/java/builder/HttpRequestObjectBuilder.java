package builder;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequestObjectBuilder implements HttpRequestBuilder {

    private static final int DEFAULT_TIMEOUT_MS = 5000;

    private HttpMethod method;
    private String url;
    private final Map<String, String> headers = new LinkedHashMap<>();
    private String body;
    private int timeoutMs = DEFAULT_TIMEOUT_MS;

    @Override
    public HttpRequestObjectBuilder method(HttpMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public HttpRequestObjectBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public HttpRequestObjectBuilder header(String name, String value) {
        headers.put(name, value);
        return this;
    }

    @Override
    public HttpRequestObjectBuilder body(String body) {
        this.body = body;
        return this;
    }

    @Override
    public HttpRequestObjectBuilder timeout(int millis) {
        this.timeoutMs = millis;
        return this;
    }

    public HttpRequest getResult() {
        if (method == null || url == null || url.isBlank()) {
            throw new IllegalStateException("Method and URL are required to build an HttpRequest");
        }
        return new HttpRequest(method, url, headers, body, timeoutMs);
    }
}
