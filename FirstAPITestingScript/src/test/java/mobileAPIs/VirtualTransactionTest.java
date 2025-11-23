package mobileAPIs;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class VirtualTransactionTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";
        // Request JSON body
        String requestBody = "{"
                + "\"amount\":\"399.00\","
                + "\"avs\":1,"
                + "\"b_street_no\":\"\","
                + "\"b_zip\":\"\","
                + "\"binType\":0,"
                + "\"card_brand\":\"VISA\","
                + "\"card_holder_name\":\"Karthik\","
                + "\"card_type\":\"D\","
                + "\"cashback\":\"0.0\","
                + "\"custom_fee\":\"0.0\","
                + "\"cvv\":999,"
                + "\"discriptor\":\"LoadFDISO4MerchantTest01\","
                + "\"email\":\"\","
                + "\"epi\":2320049263,"
                + "\"expiry_date\":1225,"
                + "\"is_signature\":1,"
                + "\"language\":\"en\","
                + "\"mtype\":\"0200\","
                + "\"pan\":4111111111111111,"
                + "\"phone\":\"\","
                + "\"reason\":\"Test\","
                + "\"request_app_version_code\":1,"
                + "\"request_app_version_name\":\"1.0\","
                + "\"request_device_model\":\"iPhone\","
                + "\"request_os\":\"iOS\","
                + "\"request_os_version\":\"16.4\","
                + "\"request_platform\":\"mobile\","
                + "\"s_street_no\":\"\","
                + "\"s_zip\":\"\","
                + "\"save_card\":0,"
                + "\"surchargeIndicator\":1,"
                + "\"surchargeRemoved\":0,"
                + "\"tax\":\"4.39\","
                + "\"taxFee\":\"0.0\","
                + "\"tip\":\"\","
                + "\"tipFee\":\"0.0\","
                + "\"txn_mode\":1"
                + "}";

        Response response =
                given()
                        .relaxedHTTPSValidation()   // bypass SSL validation

                        // Important headers to avoid 401
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .header("Origin", "https://vpuat.valorpaytech.com")
                        .header("Referer", "https://vpuat.valorpaytech.com/")
                        .header("User-Agent", "Mozilla/5.0")

                        // If your API needs token, add here:
                        // .header("Authorization", "Bearer YOUR_TOKEN_HERE")

                        .contentType(ContentType.JSON)
                        .body(requestBody)

                .when()
                        .post("/api/mobileAPI/virtualtransaction")
                .then()
                        .extract()
                        .response();

        // ✅ Always print raw response for debugging
        System.out.println("==================================");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
        System.out.println("Headers:");
        System.out.println(response.getHeaders());
        System.out.println("==================================");

        // ✅ If call succeeds, extract values
        if (response.statusCode() == 200) {

            String status = response.jsonPath().getString("status");
            String errorCode = response.jsonPath().getString("data.error_code");
            String rrn = response.jsonPath().getString("data.rrn");
            String stan = response.jsonPath().getString("data.stan");
            int tranNo = response.jsonPath().getInt("data.tran_no");

            System.out.println("Transaction Status : " + status);
            System.out.println("Error Code         : " + errorCode);
            System.out.println("RRN               : " + rrn);
            System.out.println("STAN              : " + stan);
            System.out.println("Tran No           : " + tranNo);

        } else {
            System.out.println("Request Failed ❌ - Check Authorization / Headers / Token");
        }
    }
}
