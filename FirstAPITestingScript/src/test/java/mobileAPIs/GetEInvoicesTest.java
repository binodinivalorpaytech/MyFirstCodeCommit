package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetEInvoicesTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        String requestBody = "{\n" +
                "  \"request_app_version_code\" : \"1\",\n" +
                "  \"search\" : \"\",\n" +
                "  \"language\" : \"en\",\n" +
                "  \"request_app_version_name\" : \"1.0\",\n" +
                "  \"limit\" : 10,\n" +
                "  \"request_platform\" : \"mobile\",\n" +
                "  \"offset\" : 0,\n" +
                "  \"status\" : \"ALL\",\n" +
                "  \"Date_filter\" : \"Recent\",\n" +
                "  \"epi\" : \"2320049263\",\n" +
                "  \"request_device_model\" : \"iPhone\",\n" +
                "  \"request_os_version\" : \"16.4\",\n" +
                "  \"request_os\" : \"iOS\",\n" +
                "  \"key\" : \"tssVh5Tp0tPszRvHzVFY0g==\"\n" +
                "}";

        Response response =
                RestAssured
                        .given()
                            .header("Authorization", "Bearer " + token)
                            .contentType(ContentType.JSON)
                            .body(requestBody)
                            .log().all()
                        .when()
                            .post("/api/mobileAPI/getEInvoices")
                        .then()
                            .log().all()
                            .statusCode(200)
                            .extract().response();

        // ✅ Basic validations
        String status = response.jsonPath().getString("status");
        String statusMsg = response.jsonPath().getString("statusMsg");
        int totalCount = response.jsonPath().getInt("totalCount");

        System.out.println("Status     : " + status);
        System.out.println("Message    : " + statusMsg);
        System.out.println("Total Count: " + totalCount);

        // ✅ Extract first invoice details
        if (totalCount > 0) {
            String firstInvoiceId = response.jsonPath().getString("data[0].id");
            String firstInvoiceUID = response.jsonPath().getString("data[0].uid");
            String firstInvoiceStatus = response.jsonPath().getString("data[0].status");

            System.out.println("First Invoice ID     : " + firstInvoiceId);
            System.out.println("First Invoice UID    : " + firstInvoiceUID);
            System.out.println("First Invoice Status : " + firstInvoiceStatus);
        } else {
            System.out.println("❌ No E-Invoices found.");
        }
    }
}
