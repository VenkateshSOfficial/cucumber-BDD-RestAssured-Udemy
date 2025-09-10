package utilities;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Wrappers {

	public static JsonPath js;
	private static FileInputStream fis;
	private static Properties prop;
	static Response response;
	
	public static JsonPath convertRawToJson(String response) {
		try {
			js=new JsonPath(response);
		}catch(Exception ae) {
			ae.printStackTrace();
		}
		return js;	
	}
	
	public static void readConfigFile() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties");
		prop = new Properties();
		prop.load(fis);
	}
	
	public static String fetchDataFromProperties(String key) throws IOException {
		Properties properties = new Properties();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		} catch (FileNotFoundException e) {
			throw new IOException("Properties file not found at path: " + filePath, e);
		}

		return properties.getProperty(key);
	}
	public static RequestSpecification preRequisite() throws IOException {
		if (requestSpecification == null) {
			requestSpecification = new RequestSpecBuilder()
					.setBaseUri(Wrappers.fetchDataFromProperties("baseUri")) // Ensure baseUri is valid
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(new PrintStream(
							Files.newOutputStream(Paths.get("src/main/java/logs/requestLogs.txt")))))
					.addFilter(ResponseLoggingFilter.logResponseTo(new PrintStream(
							Files.newOutputStream(Paths.get("src/main/java/logs/responseLogs.txt")))))
					.build();
		}
		return requestSpecification; // Always return the initialized object
	}
	public static Response performHttpRequest(String resource, String httpRequest, Optional<String> payload, String key,Optional<String> placeId) throws IOException {
		Response response;

		// Perform the HTTP Request
		switch (httpRequest.toUpperCase()) { // Handle case-insensitive HTTP methods
			case "POST":
				response = given()
						.spec(preRequisite())
						.queryParam("key", key)
						.contentType(ContentType.JSON)
						.body(payload.orElse(""))
						.when()
						.post(resource);
				break;

			case "GET":
				response = given()
						.spec(preRequisite())
						.queryParam("key", key)
						.queryParam("place_id", placeId.orElse(""))
						.contentType(ContentType.JSON)
						.when()
						.get(resource);
				break;

			case "PUT":
				response = given()
						.spec(preRequisite())
						.queryParam("key", key)
						.contentType(ContentType.JSON)
						.body(payload.orElse("")) // Use payload if present, else fallback to empty string
						.when()
						.put(resource);
				break;

			case "DELETE":
				response = given()
						.spec(preRequisite())
						.queryParam("key", key)
						.contentType(ContentType.JSON)
						.body(payload.orElse("")) // Use payload if present, else fallback to empty string
						.when()
						.delete(resource);
				break;

			default:
				throw new IllegalArgumentException("Unsupported HTTP method: " + httpRequest);
		}
		return response;
	}
}
