# HTTP Request Builder

A small Java project that implements the **Builder** design pattern for an
`HttpRequest` object.

The same construction steps (method, url, header, body, timeout) produce
**two different representations**:

1. A real, immutable `HttpRequest` object.
2. A ready-to-run **cURL command** (`String`).

## Structure

```
src/main/java/builder/
├── HttpMethod.java              enum: GET, POST, PUT, DELETE
├── HttpRequest.java             the Product (immutable)
├── HttpRequestBuilder.java      the Builder interface (fluent steps)
├── HttpRequestObjectBuilder.java  concrete builder -> HttpRequest object
├── CurlCommandBuilder.java        concrete builder -> curl command string
├── HttpRequestDirector.java       two ready configurations
└── Main.java                      demo / client code
```

## Run

```bash
javac -d out src/main/java/builder/*.java
java -cp out builder.Main
```

## Expected output

```
HttpRequest[POST https://api.example.com/login, headers={Content-Type=application/json}, body={"username":"john","password":"secret"}, timeoutMs=5000]
curl -X POST "https://api.example.com/login" -H "Content-Type: application/json" -d '{"username":"john","password":"secret"}' --max-time 5

HttpRequest[GET https://api.example.com/users, headers={Accept=application/json}, body=null, timeoutMs=5000]
curl -X GET "https://api.example.com/users" -H "Accept: application/json" --max-time 5

Validation works as expected: Method and URL are required to build an HttpRequest
```

## Design notes

- **Fluent API** - every builder step returns the builder itself.
- **Builder interface** decouples the `Director` from the concrete builders.
- **Two concrete builders** produce two different result types
  (`HttpRequest` vs `String`), which is why `getResult()` lives on each
  concrete class instead of the shared interface.
- **Director** (`HttpRequestDirector`) only depends on `HttpRequestBuilder`
  and offers two ready configurations: `makeGetUsersRequest` and
  `makeLoginPostRequest`.
- **Product is immutable**: package-private constructor, `final` fields,
  no public setters.
- **Validation**: `getResult()` throws `IllegalStateException` when
  `method` or `url` is missing.
- No magic numbers/strings: endpoints, headers and timeouts are named
  constants; HTTP methods use the `HttpMethod` enum.
