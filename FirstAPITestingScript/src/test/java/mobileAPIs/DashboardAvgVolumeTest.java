package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DashboardAvgVolumeTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        String requestBody = "{\n" +
                "  \"request_app_version_code\": \"1\",\n" +
                "  \"endDate\": \"2025-11-22\",\n" +
                "  \"Mp_Id\": 369685,\n" +
                "  \"startDate\": \"2025-11-22\",\n" +
                "  \"request_app_version_name\": \"1.0\",\n" +
                "  \"request_device_model\": \"iPhone\",\n" +
                "  \"request_platform\": \"mobile\",\n" +
                "  \"request_os\": \"iOS\",\n" +
                "  \"request_os_version\": \"16.4\"\n" +
                "}";

        Response response =
                RestAssured
                        .given()
                            .header("Authorization", "Bearer " + token)
                            .contentType(ContentType.JSON)
                            .body(requestBody)
                            .log().all()
                        .when()
                            .post("/api/mobileAPI/dashboardAvgVolume")
                        .then()
                            .log().all()
                            .statusCode(200)
                            .extract().response();

        // ✅ Extract avg volume
        String avgVolume = response.jsonPath().getString("data[0].txn_avg_volume");

        System.out.println("Average Transaction Volume: " + avgVolume);
    }
}
