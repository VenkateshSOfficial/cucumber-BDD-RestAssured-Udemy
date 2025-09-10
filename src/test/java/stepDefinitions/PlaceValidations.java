package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.Wrappers.convertRawToJson;

import java.io.IOException;
import java.util.*;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import payloads.Payloads;
import resources.ApiResources;
import utilities.Wrappers;

public class PlaceValidations {
	String addPlaceBody;
	Response addPlaceResponse;
	String resource;
	String key = ApiResources.KEY.getResource();
	private static String placeId;

	public static String getPlaceID(){
		return placeId;
	}

	@Given("user has add place payload with {string} and {string}")
	public void user_has_add_place_payload_with_and(String name, String address) {
		addPlaceBody = Payloads.addPlacePayload(name, address);
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String api, String httpRequest) throws IOException {
		// Read configuration and setup API details
		Wrappers.readConfigFile();
		resource = ApiResources.valueOf(api).getResource();

		// Execute the HTTP request
		addPlaceResponse = Wrappers.performHttpRequest(resource, httpRequest, Optional.ofNullable(addPlaceBody), key,Optional.empty());

		// Parse Response and Add Place ID
		placeId = convertRawToJson(addPlaceResponse.asPrettyString()).getString("place_id");
		System.out.println("The place id :" + placeId);

	}

	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer statusCode) {
		addPlaceResponse.then().assertThat().statusCode(statusCode);
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String key, String value) {
		String actualValue = convertRawToJson(addPlaceResponse.asPrettyString()).getString(key);
		assertEquals(value, actualValue);
	}
}