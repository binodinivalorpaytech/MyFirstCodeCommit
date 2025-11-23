package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OpenBatchTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        String requestBody = "{\n" +
                "  \"request_app_version_code\" : \"1\",\n" +
                "  \"request_os\" : \"iOS\",\n" +
                "  \"key\" : \"tssVh5Tp0tPszRvHzVFY0g==\",\n" +
                "  \"offset\" : 0,\n" +
                "  \"request_device_model\" : \"iPhone\",\n" +
                "  \"language\" : \"en\",\n" +
                "  \"limit\" : 10,\n" +
                "  \"storeInfo\" : \"419935\",\n" +
                "  \"request_platform\" : \"mobile\",\n" +
                "  \"request_app_version_name\" : \"1.0\",\n" +
                "  \"epiID\" : \"2320049263\",\n" +
                "  \"request_os_version\" : \"16.4\"\n" +
                "}";

        Response response =
                RestAssured
                        .given()
                            .header("Authorization", "Bearer " + token)
                            .contentType(ContentType.JSON)
                            .body(requestBody)
                            .log().all()
                        .when()
                            .post("/api/mobileAPI/openBatch")
                        .then()
                            .log().all()
                            .statusCode(200)
                            .extract().response();

        // ✅ Extracting useful values
        String status = response.jsonPath().getString("status");
        String statusMsg = response.jsonPath().getString("statusMsg");

        int dashboardNetAmount = response.jsonPath().getInt("batchSummary[0].dashboard_netamount");
        int dashboardBatchCount = response.jsonPath().getInt("batchSummary[0].dashboard_batchcount");

        String rrn = response.jsonPath().getString("batchSummaryDetails[0].rrn");
        String approvalCode = response.jsonPath().getString("batchSummaryDetails[0].approval_code");
        int tranNo = response.jsonPath().getInt("batchSummaryDetails[0].tran_no");
        String maskedCard = response.jsonPath().getString("batchSummaryDetails[0].masked_card_no");

        System.out.println("Status             : " + status);
        System.out.println("Status Message     : " + statusMsg);
        System.out.println("Net Amount         : " + dashboardNetAmount);
        System.out.println("Batch Count        : " + dashboardBatchCount);
        System.out.println("RRN                : " + rrn);
        System.out.println("Approval Code      : " + approvalCode);
        System.out.println("Tran No            : " + tranNo);
        System.out.println("Masked Card        : " + maskedCard);
    }
}
