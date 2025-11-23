package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class BinValueDownloadAPI {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://tms-dev.valorpaytech.com:4430";

        Response response =
                given()
                        .relaxedHTTPSValidation()   // handles SSL
                        .queryParam("epi", "2320049263")
                        .queryParam("action", "BinValueDownload")
                        .queryParam("bin_number", "4111111111111111")
                        .queryParam("operation", "Bininfo")
                        .queryParam("vendorid", "1")
                        .queryParam("requestFrom", "einvoiceecomm")
                        .header("Content-Type", "application/json")
                        .body("{}")
                .when()
                        .post("/1.0.0/param/")
                .then()
                        .statusCode(200)
                        .extract()
                        .response();

        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.prettyPrint());

        // ✅ Extract fields
        String panNumber = response.jsonPath().getString("pan_number");
        String cardBrand = response.jsonPath().getString("card_brand");
        String cardType = response.jsonPath().getString("card_type");

        System.out.println("PAN Number: " + panNumber);
        System.out.println("Card Brand: " + cardBrand);
        System.out.println("Card Type: " + cardType);
    }
}

