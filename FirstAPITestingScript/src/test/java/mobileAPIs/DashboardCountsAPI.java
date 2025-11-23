package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DashboardCountsAPI {

    public static void main(String[] args) {

        // Base URL
        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        // Replace this with your actual token
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // API Call
        Response response =
                given()
                        .relaxedHTTPSValidation()   // bypass SSL issues
                        .header("Authorization", "Bearer " + token)
                        .header("Accept", "application/json")
                        .contentType(ContentType.JSON)

                .when()
                        .get("/api/mobileAPI/getDashboardCounts")

                .then()
                        .extract()
                        .response();

        // Print full response for debugging
        System.out.println("=======================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("Response Headers:");
        System.out.println(response.getHeaders());
        System.out.println("=======================================");

        // Extract values from response
        if (response.statusCode() == 200) {

            String status = response.jsonPath().getString("status");
            String statusMsg = response.jsonPath().getString("statusMsg");
            int storeCount = response.jsonPath().getInt("store_count");
            int deviceCount = response.jsonPath().getInt("device_count");
            int userCount = response.jsonPath().getInt("user_count");

            System.out.println("Status       : " + status);
            System.out.println("Message      : " + statusMsg);
            System.out.println("Store Count  : " + storeCount);
            System.out.println("Device Count : " + deviceCount);
            System.out.println("User Count   : " + userCount);

        } else {
            System.out.println("❌ Request Failed");
            System.out.println("Server Message: " + response.jsonPath().getString("message"));
        }
    }
}
