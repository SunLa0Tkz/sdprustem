package builder;

import java.util.Map;

public class HttpRequest {
    private final HttpMethod method;
    private final String url;
    private final Map<String, String> headers;
    private final String body;
    private final int timeoutMs;

    HttpRequest(HttpMethod method, String url, Map<String, String> headers, String body, int timeoutMs) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.timeoutMs = timeoutMs;
    }

    public HttpMethod getMethod() { return method; }
    public String getUrl() { return url; }
    public Map<String, String> getHeaders() { return headers; }
    public String getBody() { return body; }
    public int getTimeoutMs() { return timeoutMs; }

    @Override
    public String toString() {
        return "HttpRequest[" + method + " " + url +
                ", headers=" + headers +
                ", body=" + body +
                ", timeoutMs=" + timeoutMs + "]";
    }
}