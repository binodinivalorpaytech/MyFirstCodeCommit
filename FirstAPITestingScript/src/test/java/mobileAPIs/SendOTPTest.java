package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SendOTPTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        String requestBody = "{\n" +
                "  \"faData\": {\n" +
                "    \"action\": \"ecomm_refund\",\n" +
                "    \"amount\": \"10.0\",\n" +
                "    \"appid\": \"a819d06508519977b9bc88e9f00837ca\",\n" +
                "    \"appkey\": \"si6@p4mevgv5QWWxXkUXhIYVttgtWK#%\",\n" +
                "    \"epi\": 2320049263\n" +
                "  }\n" +
                "}";

        Response response =
                RestAssured
                        .given()
                            .header("Authorization", "Bearer " + token)
                            .contentType(ContentType.JSON)
                            .body(requestBody)
                            .log().all()
                        .when()
                            .post("/api/mobileAPI/sendOTP")
                        .then()
                            .log().all()
                            .statusCode(200)
                            .extract().response();

        System.out.println("✅ Raw Response:\n" + response.asString());

        // ✅ Extract required fields
        String status = response.jsonPath().getString("status");
        String uuid = response.jsonPath().getString("data.response.uuid");
        String referenceNo = response.jsonPath().getString("data.response.reference_no");
        Integer epiId = response.jsonPath().getInt("data.response.epi_id");

        System.out.println("Status       : " + status);
        System.out.println("UUID         : " + uuid);
        System.out.println("Reference No : " + referenceNo);
        System.out.println("EPI ID       : " + epiId);

        // Basic validation
        if (!status.equalsIgnoreCase("SUCCESS")) {
            throw new RuntimeException("❌ sendOTP API failed!");
        }

        if (uuid == null || referenceNo == null) {
            throw new RuntimeException("❌ UUID or Reference Number is missing!");
        }

        // Now you can pass uuid & referenceNo into next API
    }
}
