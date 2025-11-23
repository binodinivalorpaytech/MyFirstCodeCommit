package mobileAPIs;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class FilterTransactionsDetailsAPI {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        // Replace with your valid token
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        // Replace this with your real storeinfo value
        String storeInfo = "{storeinfo}";

        // ✅ Request body with "query" wrapper
        String requestBody = "{"
                + "\"query\":{"
                + "\"epageId\":0,"
                + "\"request_os\":\"iOS\","
                + "\"dateFilter\":\"Recent\","
                + "\"key\":\"tssVh5Tp0tPszRvHzVFY0g==\","
                + "\"request_os_version\":\"16.4\","
                + "\"offset\":0,"
                + "\"transactionType\":\"ALL\","
                + "\"newList\":\"ALL\","
                + "\"request_app_version_code\":\"1\","
                + "\"limit\":10,"
                + "\"cardBrands\":\"ALL\","
                + "\"request_app_version_name\":\"1.0\","
                + "\"request_device_model\":\"iPhone\","
                + "\"epiInfo\":[\"2320049263\"],"
                + "\"searchValue\":\"\","
                + "\"subscriptionId\":\"0\","
                + "\"storeInfo\":[\"" + storeInfo + "\"],"
                + "\"startDate\":\"2025-11-21\","
                + "\"freeSelectTxt\":\"\","
                + "\"transactionStatus\":\"ALL\","
                + "\"devices\":\"ALL\","
                + "\"endDate\":\"2025-11-21\","
                + "\"approvedTran\":\"ALL\","
                + "\"freeSelect\":\"ALL\","
                + "\"request_platform\":\"mobile\","
                + "\"lang\":\"en\""
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
                        .post("/api/mobileAPI/filterTransactionsDetails")

                .then()
                        .extract()
                        .response();

        System.out.println("=============================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("=============================================");

        // ✅ Validation & Extraction
        if (response.statusCode() == 200) {

            String status = response.jsonPath().getString("status");
            String statusMsg = response.jsonPath().getString("statusMsg");
            int totalCount = response.jsonPath().getInt("totalCount");
            int paginationCount = response.jsonPath().getInt("paginationCount");

            System.out.println("Status            : " + status);
            System.out.println("Message           : " + statusMsg);
            System.out.println("Total Count       : " + totalCount);
            System.out.println("Pagination Count  : " + paginationCount);

            // Extract first record fields
            String firstTxnType = response.jsonPath().getString("records[0].txn_type");
            String firstRrn = response.jsonPath().getString("records[0].rrn");
            int firstTranNo = response.jsonPath().getInt("records[0].tran_no");
            int firstAmount = response.jsonPath().getInt("records[0].amount");

            System.out.println("First Transaction Type : " + firstTxnType);
            System.out.println("First RRN             : " + firstRrn);
            System.out.println("First Tran No         : " + firstTranNo);
            System.out.println("First Amount          : " + firstAmount);

        } else {
            System.out.println("❌ API Failed");
            System.out.println("Error Message: " +
                    response.jsonPath().getString("message"));
        }
    }
}

