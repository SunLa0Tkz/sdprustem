package builder;

public interface HttpRequestBuilder {

    HttpRequestBuilder method(HttpMethod method);

    HttpRequestBuilder url(String url);

    HttpRequestBuilder header(String name, String value);

    HttpRequestBuilder body(String body);

    HttpRequestBuilder timeout(int millis);
}
