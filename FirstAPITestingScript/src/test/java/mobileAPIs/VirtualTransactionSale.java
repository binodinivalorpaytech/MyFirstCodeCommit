package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class VirtualTransactionSale {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        String requestBody = "{\n" +
                "  \"amount\": \"2.0\",\n" +
                "  \"avs\": \"\",\n" +
                "  \"b_street_no\": \"\",\n" +
                "  \"b_zip\": \"\",\n" +
                "  \"card_brand\": \"VISA\",\n" +
                "  \"card_holder_name\": \"\",\n" +
                "  \"card_type\": \"D\",\n" +
                "  \"cvv\": 999,\n" +
                "  \"discriptor\": \"VJ Cofe\",\n" +
                "  \"email\": \"\",\n" +
                "  \"epi\": \"2320049263\",\n" +
                "  \"expiry_date\": 1225,\n" +
                "  \"is_signature\": 1,\n" +
                "  \"language\": \"en\",\n" +
                "  \"mtype\": \"0200\",\n" +
                "  \"pan\": \"4111111111111111\",\n" +
                "  \"phone\": \"\",\n" +
                "  \"s_street_no\": \"\",\n" +
                "  \"s_zip\": \"\",\n" +
                "  \"save_card\": 0,\n" +
                "  \"surchargeIndicator\": 1,\n" +
                "  \"txn_mode\": 1\n" +
                "}";

        Response response =
                RestAssured
                        .given()
                            .header("Authorization", "Bearer " + token)
                            .contentType(ContentType.JSON)
                            .body(requestBody)
                            .log().all()
                        .when()
                            .post("/api/mobileAPI/virtualtransaction")
                        .then()
                            .log().all()
                            .statusCode(200)
                            .extract().response();

        System.out.println("✅ Raw Response:\n" + response.asString());

        // ✅ Extract important fields
        String status = response.jsonPath().getString("status");
        String statusMsg = response.jsonPath().getString("statusMsg");
        String approvalCode = response.jsonPath().getString("data.approval_code");
        String rrn = response.jsonPath().getString("data.rrn");
        String txnId = response.jsonPath().getString("data.txnid");
        String tokenValue = response.jsonPath().getString("data.token");
        String description = response.jsonPath().getString("data.desc");
        String netAmount = response.jsonPath().getString("data.netamt");

        System.out.println("Status       : " + status);
        System.out.println("Message      : " + statusMsg);
        System.out.println("ApprovalCode : " + approvalCode);
        System.out.println("RRN          : " + rrn);
        System.out.println("TxnID        : " + txnId);
        System.out.println("Token        : " + tokenValue);
        System.out.println("Description  : " + description);
        System.out.println("Net Amount   : " + netAmount);

        // ✅ Assertion Example
        if (!status.equalsIgnoreCase("SUCCESS")) {
            throw new RuntimeException("Transaction Failed!");
        }

        if (!description.contains("APPROVAL")) {
            throw new RuntimeException("Transaction not approved!");
        }
    }
}
