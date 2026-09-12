package builder;

public class Main {

    public static void main(String[] args) {
        HttpRequestDirector director = new HttpRequestDirector();

        HttpRequestObjectBuilder objectBuilder = new HttpRequestObjectBuilder();
        director.makeLoginPostRequest(objectBuilder);
        HttpRequest loginRequest = objectBuilder.getResult();
        System.out.println(loginRequest);

        CurlCommandBuilder curlBuilder = new CurlCommandBuilder();
        director.makeLoginPostRequest(curlBuilder);
        String curlCommand = curlBuilder.getResult();
        System.out.println(curlCommand);

        System.out.println();

        HttpRequestObjectBuilder usersObjectBuilder = new HttpRequestObjectBuilder();
        director.makeGetUsersRequest(usersObjectBuilder);
        System.out.println(usersObjectBuilder.getResult());

        CurlCommandBuilder usersCurlBuilder = new CurlCommandBuilder();
        director.makeGetUsersRequest(usersCurlBuilder);
        System.out.println(usersCurlBuilder.getResult());

        System.out.println();

        try {
            new HttpRequestObjectBuilder().getResult();
        } catch (IllegalStateException e) {
            System.out.println("Validation works as expected: " + e.getMessage());
        }
    }
}
