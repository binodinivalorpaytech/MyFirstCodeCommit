package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

/*public class TransactionsOverViewAPI {

    public static void main(String[] args) {

        // Base URL
        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        // Replace with your actual token
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // Request body
        String requestBody = "{"
                + "\"query\":{"
                + "\"request_app_version_name\":\"1.0\","
                + "\"request_platform\":\"mobile\","
                + "\"request_os\":\"iOS\","
                + "\"dateFilter\":\"Recent\","
                + "\"request_device_model\":\"iPhone\","
                + "\"request_app_version_code\":\"1\","
                + "\"startDate\":\"2025-11-21\","
                + "\"endDate\":\"2025-11-21\","
                + "\"request_os_version\":\"16.4\""
                + "}"
                + "}";

        // API Call
        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Authorization", "Bearer " + token)
                        .header("Accept", "application/json")
                        .contentType(ContentType.JSON)
                        .body(requestBody)

                .when()
                        .post("/api/mobileAPI/getTransactionsOverView")

                .then()
                        .extract()
                        .response();

        // Print response
        System.out.println("==========================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("==========================================");

        // Validate and extract data
        if (response.statusCode() == 200) {

            String status = response.jsonPath().getString("status");
            String statusMsg = response.jsonPath().getString("statusMsg");

            // Extract values from transactions_details[0]
            int netAmount = response.jsonPath().getInt("transactions_details[0].Net Amount");
            int refundAmount = response.jsonPath().getInt("transactions_details[0].Refund Amount");
            int tipAmount = response.jsonPath().getInt("transactions_details[0].Tip Amount");
            int taxAmount = response.jsonPath().getInt("transactions_details[0].Tax Amount");
            int customFee = response.jsonPath().getInt("transactions_details[0].Custom Fee");

            System.out.println("Status       : " + status);
            System.out.println("Message      : " + statusMsg);
            System.out.println("Net Amount   : " + netAmount);
            System.out.println("Refund Amount: " + refundAmount);
            System.out.println("Tip Amount   : " + tipAmount);
            System.out.println("Tax Amount   : " + taxAmount);
            System.out.println("Custom Fee   : " + customFee);
        } else {
            System.out.println("❌ API Failed");
            System.out.println("Server Message: "
                    + response.jsonPath().getString("message"));
        }
    }
}*/


public class TransactionsOverViewAPI {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // ✅ Request body wrapped in "query"
        String requestBody = "{"
                + "\"query\":{"
                + "\"request_app_version_name\":\"1.0\","
                + "\"request_platform\":\"mobile\","
                + "\"request_os\":\"iOS\","
                + "\"dateFilter\":\"Recent\","
                + "\"request_device_model\":\"iPhone\","
                + "\"request_app_version_code\":\"1\","
                + "\"startDate\":\"2025-11-21\","
                + "\"endDate\":\"2025-11-21\","
                + "\"request_os_version\":\"16.4\""
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
                        .post("/api/mobileAPI/getTransactionsOverView")

                .then()
                        .extract()
                        .response();

        System.out.println("==========================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("==========================================");

        if (response.statusCode() == 200) {

            String status = response.jsonPath().getString("status");
            String statusMsg = response.jsonPath().getString("statusMsg");

            int netAmount = response.jsonPath().getInt("transactions_details[0].['Net Amount']");
            int refundAmount = response.jsonPath().getInt("transactions_details[0].['Refund Amount']");
            int tipAmount = response.jsonPath().getInt("transactions_details[0].['Tip Amount']");
            int taxAmount = response.jsonPath().getInt("transactions_details[0].['Tax Amount']");
            int customFee = response.jsonPath().getInt("transactions_details[0].['Custom Fee']");

            System.out.println("Status       : " + status);
            System.out.println("Message      : " + statusMsg);
            System.out.println("Net Amount   : " + netAmount);
            System.out.println("Refund Amount: " + refundAmount);
            System.out.println("Tip Amount   : " + tipAmount);
            System.out.println("Tax Amount   : " + taxAmount);
            System.out.println("Custom Fee   : " + customFee);
        } else {
            System.out.println("❌ API Failed");
            System.out.println("Server Message: "
                    + response.jsonPath().getString("message"));
        }
    }
}

