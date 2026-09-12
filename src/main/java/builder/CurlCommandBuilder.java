package builder;

import java.util.LinkedHashMap;
import java.util.Map;

public class CurlCommandBuilder implements HttpRequestBuilder {

    private static final int DEFAULT_TIMEOUT_MS = 5000;
    private static final int MILLIS_PER_SECOND = 1000;

    private HttpMethod method;
    private String url;
    private final Map<String, String> headers = new LinkedHashMap<>();
    private String body;
    private int timeoutMs = DEFAULT_TIMEOUT_MS;

    @Override
    public CurlCommandBuilder method(HttpMethod method) {
        this.method = method;
        return this;
    }

    @Override
    public CurlCommandBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public CurlCommandBuilder header(String name, String value) {
        headers.put(name, value);
        return this;
    }

    @Override
    public CurlCommandBuilder body(String body) {
        this.body = body;
        return this;
    }

    @Override
    public CurlCommandBuilder timeout(int millis) {
        this.timeoutMs = millis;
        return this;
    }

    public String getResult() {
        if (method == null || url == null || url.isBlank()) {
            throw new IllegalStateException("Method and URL are required to build a curl command");
        }

        StringBuilder command = new StringBuilder("curl -X ").append(method).append(" \"").append(url).append("\"");

        for (Map.Entry<String, String> header : headers.entrySet()) {
            command.append(" -H \"").append(header.getKey()).append(": ").append(header.getValue()).append("\"");
        }

        if (body != null && !body.isBlank()) {
            command.append(" -d '").append(body).append("'");
        }

        command.append(" --max-time ").append(timeoutMs / MILLIS_PER_SECOND);

        return command.toString();
    }
}
