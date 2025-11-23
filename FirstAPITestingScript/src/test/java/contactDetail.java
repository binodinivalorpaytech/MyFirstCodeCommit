import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class contactDetail {
	
	 public static void main(String[] args) {
	        // Set base URI
	        RestAssured.baseURI = "https://vpuat.valorpaytech.com/api";

	        // Sending GET request to the endpoint
	        Response response = RestAssured
	            .given()
	                .header("Content-Type", "application/json") // Set the content type
	                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjozOTc0NiwidXNlcl90eXBlX2lkIjoxLCJwZXJtaXNzaW9ucyI6WyJtb2R1bGVzOioiLCJ1c2VyOmFkbWluIl0sInNlc3Npb25faWQiOiI3ODcwNWRjMC1kNjlkLTQ5ZWUtODUxMC0zODA1OGVhN2Q5NjkiLCJjdXN0X2lkIjoyMDYsImlzX29wZXJhdG9yX2FkbWluIjowLCJpYXQiOjE3NjM2MjY3NjUsImV4cCI6MTc2MzcxMzE2NX0.HY30JnQikSnh-B_QTtcQgWEm8muvjQ5UqpriUtivgwcWBy4evJRtUZEU7W-eZ_BcuZhOOaWvXzCLfm7qbHEjGbCBqERjDYcRs4FTFlpNKnAkTsJy1XT87Pnu8NLdOftlumANsHZX_es-rOV8hJ9uRvKByGYauGmhO-FOqfneds4He93MdBlKOSoMDRA1sQXm1sVT74urgrbjutKjjFAs6TcwzoB-IeeWfc-uPnvi54xsmFLQ0vacE-ovtE8HrdH_7fBZ-GJcQkl3j-gfsLL4WzDGnqgSos2ZhWToI3bHZjRgEx4MaPlo37Z9skcvBc0FEoZqz46pvI9SZpXYRXhyYQ") // Add token if required
	            .when()
	                .get("/user-management/getModules/39746/2") // API endpoint
	            .then()
	                .contentType(ContentType.JSON) // Validate that the response is in JSON format
	                .extract().response();

	        // Print status code
	        System.out.println("Status Code: " + response.getStatusCode());

	        // Print the entire response body
	        String responseBody = response.getBody().asString();
	        System.out.println("Response Body: " + responseBody);

	        // Optional: Assert that the response contains expected values
	        if (response.getStatusCode() == 200) {
	            System.out.println("API request successful!");
	        } else {
	            System.out.println("API request failed with status code: " + response.getStatusCode());
	        }
	    }

}
