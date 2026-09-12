package builder;

/**
 * The Director.
 * Knows two ready-made request configurations and builds them
 * through the HttpRequestBuilder interface only - it never depends
 * on a concrete builder.
 */
public class HttpRequestDirector {

    private static final String USERS_ENDPOINT = "https://api.example.com/users";
    private static final String LOGIN_ENDPOINT = "https://api.example.com/login";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String LOGIN_PAYLOAD = "{\"username\":\"john\",\"password\":\"secret\"}";

    public void makeGetUsersRequest(HttpRequestBuilder builder) {
        builder.method(HttpMethod.GET)
                .url(USERS_ENDPOINT)
                .header(ACCEPT_HEADER, JSON_CONTENT_TYPE);
    }

    public void makeLoginPostRequest(HttpRequestBuilder builder) {
        builder.method(HttpMethod.POST)
                .url(LOGIN_ENDPOINT)
                .header(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
                .body(LOGIN_PAYLOAD);
    }
}
