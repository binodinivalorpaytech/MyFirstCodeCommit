package mobileAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ViewTicketTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://vpuat.valorpaytech.com";

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MTY2NjIsInVzZXJfdHlwZV9pZCI6NCwicGVybWlzc2lvbnMiOlsibW9kdWxlczoqIiwidXNlcjpvcGVyYXRvciJdLCJzZXNzaW9uX2lkIjoiNDU2N2QxZmItMzZjZC00Y2M4LTgyZGItYWEwYzRkNzRmYjY3IiwiY3VzdF9pZCI6MTEzMzgsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM3MTc1MDYsImV4cCI6MTc2MzgwMzkwNn0.G3Jys5cwXoVWFt0pNg7xx8YkvBhABCJAQtQYQA0qR2H9_zkPROwgRIdcBvJl1XaW9TpsI3AQK6njwB2PLc_Szo5Yv6SG9gptH_-foLTGBiiQfhjb_5eLh6TbyjXt7yYS6lt7f1KnnuQ4PlWvATt74u7761IBJwGDGVzmF1pZz20Vu3rIVsT3d3EHQdzKIS3FRFBlX6wKFDWGNmXFuYj8nZ3QAUuPMDhV4RiSOuFjTB4JSJy1YwU9lRroPqihxYk3G-OlGrTxtkkGPSk_rDQ5zHoK02LKn_vP9ufKsF_gLtRyA9Gev27ru5aQZzQqWPlEYfSg6KWFjYBpjdGVExcWSQ";

        String requestBody = "{\n" +
                "  \"offset\" : 0,\n" +
                "  \"request_app_version_name\" : \"1.0\",\n" +
                "  \"epi\" : \"2320049263\",\n" +
                "  \"limit\" : 10,\n" +
                "  \"request_app_version_code\" : \"1\",\n" +
                "  \"request_platform\" : \"mobile\",\n" +
                "  \"status\" : \"ALL\",\n" +
                "  \"search\" : \"\",\n" +
                "  \"request_os\" : \"iOS\",\n" +
                "  \"request_os_version\" : \"16.4\",\n" +
                "  \"key\" : \"tssVh5Tp0tPszRvHzVFY0g==\",\n" +
                "  \"language\" : \"en\",\n" +
                "  \"request_device_model\" : \"iPhone\"\n" +
                "}";

        Response response =
                RestAssured
                        .given()
                            .header("Authorization", "Bearer " + token)
                            .contentType(ContentType.JSON)
                            .body(requestBody)
                            .log().all()
                        .when()
                            .post("/api/mobileAPI/viewTicket")
                        .then()
                            .log().all()
                            .statusCode(200)
                            .extract().response();

        // ✅ Print key details
        String status = response.jsonPath().getString("status");
        String message = response.jsonPath().getString("statusMsg");

        System.out.println("Status  : " + status);
        System.out.println("Message : " + message);

        // ✅ Extract Tickets Count
        int ticketCount = response.jsonPath().getList("data.tickets").size();
        System.out.println("Tickets Count: " + ticketCount);

        // ✅ Extract Subjects Count
        int subjectCount = response.jsonPath().getList("data.subjects").size();
        System.out.println("Subjects Count: " + subjectCount);

        // ✅ Extract First Subject Details
        if (subjectCount > 0) {
            int subjectId = response.jsonPath().getInt("data.subjects[0].id");
            String subjectName = response.jsonPath().getString("data.subjects[0].subject");
            String sendTo = response.jsonPath().getString("data.subjects[0].send_to");

            System.out.println("First Subject ID   : " + subjectId);
            System.out.println("First Subject Name : " + subjectName);
            System.out.println("Send To            : " + sendTo);
        } else {
            System.out.println("No subjects found!");
        }
    }
}
