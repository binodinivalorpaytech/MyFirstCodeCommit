package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

/*public class DashboardDetailsAPI {

    public static void main(String[] args) {

        // Base URL
        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        // Replace this with your actual token
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // Request body
        String requestBody = "{"
                + "\"request_os\":\"iOS\","
                + "\"Mp_Id\":369685,"
                + "\"request_app_version_code\":\"1\","
                + "\"request_app_version_name\":\"1.0\","
                + "\"request_os_version\":\"16.4\","
                + "\"request_platform\":\"mobile\","
                + "\"request_device_model\":\"iPhone\""
                + "}";

        // REST Assured call
        Response response =
                given()
                        .relaxedHTTPSValidation()   // ignore SSL errors
                        .header("Authorization", "Bearer " + token)
                        .header("Accept", "application/json")
                        .contentType(ContentType.JSON)
                        .body(requestBody)

                .when()
                        .post("/api/mobileAPI/getDashboardDetails")

                .then()
                        .extract()
                        .response();

        // Debug output
        System.out.println("=======================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("Response Headers:");
        System.out.println(response.getHeaders());
        System.out.println("=======================================");

        // If success, extract values
        if (response.statusCode() == 200) {

            String status = response.jsonPath().getString("status");
            String statusMsg = response.jsonPath().getString("statusMsg");

            System.out.println("Status     : " + status);
            System.out.println("Message    : " + statusMsg);

            // If you want, I can help you extract nested dashboard fields too
            // based on your actual response structure.
        } else {
            System.out.println("❌ Request Failed");
            System.out.println("Error Message: " +
                    response.jsonPath().getString("message"));
        }
    }
}*/



public class DashboardDetailsAPI {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // ✅ Notice: added "query" wrapper
        String requestBody = "{"
                + "\"query\":{"
                + "\"request_os\":\"iOS\","
                + "\"Mp_Id\":369685,"
                + "\"request_app_version_code\":\"1\","
                + "\"request_app_version_name\":\"1.0\","
                + "\"request_os_version\":\"16.4\","
                + "\"request_platform\":\"mobile\","
                + "\"request_device_model\":\"iPhone\""
                + "}"
                + "}";

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + token)
                        .header("Accept", "application/json")
                        .contentType(ContentType.JSON)
                        .body(requestBody)

                .when()
                        .post("/api/mobileAPI/getDashboardDetails")

                .then()
                        .extract()
                        .response();

        System.out.println("=======================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("=======================================");

        if (response.statusCode() == 200) {
            System.out.println("✅ Request Successful");

            // Example extraction
            String status = response.jsonPath().getString("status");
            String statusMsg = response.jsonPath().getString("statusMsg");

            System.out.println("Status  : " + status);
            System.out.println("Message : " + statusMsg);
        } else {
            System.out.println("❌ Request Failed");
            System.out.println("Error: " + response.jsonPath().getString("message"));
        }
    }
}


