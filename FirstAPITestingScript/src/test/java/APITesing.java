	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	import io.restassured.http.ContentType;
	import org.json.JSONObject;

	public class APITesing { 

	    public static void main(String[] args) {
	        // Base URI for the API
	        RestAssured.baseURI = "https://vpuat.valorpaytech.com/api/auth/login";

	        // Creating a JSON Object for the request body
	        JSONObject requestBody = new JSONObject();
	        requestBody.put("username", "binodini");  // Use actual credentials
	        requestBody.put("password", "U2FsdGVkX1/w8UoWQ6KhD+MEnOvrZ4iLMi8I7ky8TTg=");  // Use actual credentials

	        // Sending POST request and receiving the response
	        Response response = RestAssured
	            .given()
	                .header("Content-Type", "application/json")
	                .body(requestBody.toString())
	            .when()
	                .post()
	            .then()
	                .contentType(ContentType.JSON)
	                .extract().response();

	        // Printing out response details
	        int statusCode = response.getStatusCode();
	        String responseBody = response.getBody().asString();

	        System.out.println("Status Code: " + statusCode);
	        System.out.println("Response Body: " + responseBody);

	        // Asserting response status and body
	        if (statusCode == 200) {
	            System.out.println("API request was successful!");
	        } else {
	            System.out.println("API request failed with status code: " + statusCode);
	        }
	    }
	    
	    
	}


