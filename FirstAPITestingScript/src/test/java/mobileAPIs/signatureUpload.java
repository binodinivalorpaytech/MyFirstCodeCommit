package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class signatureUpload {

    public static void main(String[] args) {

        // Base URL
        RestAssured.baseURI = "https://rcpt-dev.valorpaytech.com:4430";

        // Replace this with your real token
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // Request Body
        String requestBody = "{"
                + "\"REMOVE_FEE\":\"0\","
                + "\"request_os_version\":\"16.4\","
                + "\"TVR\":\"\","
                + "\"ORIGINAL_CVM\":\"2\","
                + "\"CARDHOLDER_NAME\":\"Karthik\","
                + "\"MMID\":\"1\","
                + "\"AID\":\"\","
                + "\"PIN_TYPE\":\"\","
                + "\"PERFORM_CVM\":\"2\","
                + "\"request_platform\":\"mobile\","
                + "\"PROFILE_SCREEN\":\"0\","
                + "\"EPI_ID\":\"2320049263\","
                + "\"request_device_model\":\"iPhone\","
                + "\"CARDHOLDER_MOBILENO\":\"\","
                + "\"request_app_version_code\":\"1\","
                + "\"RRN\":\"532510503058\","
                + "\"BIN_BASE\":\"0\","
                + "\"TXN_ID\":\"532510503058\","
                + "\"CARD_TYPE\":\"2\","
                + "\"CARDHOLDER_SIGNATURE\":\"\","
                + "\"request_app_version_name\":\"1.0\","
                + "\"language\":\"en\","
                + "\"SERVICE\":\"SIG_UPLOAD\","
                + "\"request_os\":\"iOS\","
                + "\"APP_NAME\":\"\","
                + "\"MAIL_ID\":\"\","
                + "\"TSI\":\"\","
                + "\"SURCHARGE_LABEL\":\"Card Difference\","
                + "\"CASH_DISCOUNT_LABEL\":\"\""
                + "}";

        // Rest Assured Call
        Response response =
                given()
                        .relaxedHTTPSValidation()    // Ignore SSL issues
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")

                        .contentType(ContentType.JSON)
                        .body(requestBody)

                .when()
                        .post("/")   // root endpoint since URL is just the domain

                .then()
                        .extract()
                        .response();

        // Debug Output
        System.out.println("====================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("Response Headers:");
        System.out.println(response.getHeaders());
        System.out.println("====================================");
    }
}

