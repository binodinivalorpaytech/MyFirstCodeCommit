package mobileAPIs;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class paramrequest {

    public static void main(String[] args) {

        String url = "https://tms-dev.valorpaytech.com:4430/1.0.0/param/";

        Response response = RestAssured
                .given()
                .queryParam("epi", "2320049263")
                .queryParam("action", "REQUEST")
                .queryParam("did", "auto")
                .header("Content-Type", "application/json")
                .when()
                .post(url);

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.prettyPrint());
    }
}

