package mobileAPIs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class paramDownloadAPI {
//param download
    public static void main(String[] args) {

        RestAssured.baseURI = "https://tms-dev.valorpaytech.com:4430";

        Response response =
                given()
                    .relaxedHTTPSValidation()   // important for SSL
                    .queryParam("pkgid", "9")
                    .queryParam("epi", "2320049263")
                    .queryParam("action", "DOWNLOAD")
                    .queryParam("did", "auto")
                    .header("Content-Type", "application/json")
                .when()
                    .post("/1.0.0/param/")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Time: " + response.time() + " ms");
        System.out.println("Response Body:");
        System.out.println(response.prettyPrint());
    }
}

