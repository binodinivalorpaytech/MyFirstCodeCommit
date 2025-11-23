package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class viewAds {

    public static void main(String[] args) {

        // Base URL
        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        // Replace with your actual bearer token
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // Request Body
        String requestBody = "{"
                + "\"request_os_version\":\"16.4\","
                + "\"request_os\":\"iOS\","
                + "\"request_app_version_code\":\"1\","
                + "\"epi\":[\"2320049263\"],"
                + "\"request_device_model\":\"iPhone\","
                + "\"request_platform\":\"mobile\","
                + "\"request_app_version_name\":\"1.0\""
                + "}";

        // REST Assured Call
        Response response =
                given()
                        .relaxedHTTPSValidation()   // bypass SSL issues
                        .header("Authorization", "Bearer " + token)
                        .header("Accept", "application/json")
                        .contentType(ContentType.JSON)
                        .body(requestBody)

                .when()
                        .post("/api/mobileAPI/viewAds")

                .then()
                        .extract()
                        .response();

        // Print response for debugging
        System.out.println("========================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("Response Headers:");
        System.out.println(response.getHeaders());
        System.out.println("========================================");

        // Extract values
        if (response.statusCode() == 200) {

            String status = response.jsonPath().getString("status");
            String statusMsg = response.jsonPath().getString("statusMsg");
            int dataSize = response.jsonPath().getList("data").size();

            System.out.println("Status    : " + status);
            System.out.println("Message   : " + statusMsg);
            System.out.println("Ads Count : " + dataSize);

        } else {
            System.out.println("❌ API Failed");
            System.out.println("Server Message: " +
                    response.jsonPath().getString("message"));
        }
    }
}
