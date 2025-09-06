package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.Wrappers.convertRawToJson;
import static utilities.Wrappers.preRequisite;

import java.io.IOException;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import payloads.Payloads;
import resources.ApiResources;
import utilities.Wrappers;


public class PlaceValidations {
	String addPlaceBody;
	Response addPlaceResponse;

	@Given("user has add place payload")
	public void user_has_add_place_payload() {
		addPlaceBody = Payloads.addPlacePayload("anbu illam", "karuppu koil street");
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String api, String httpRequest) throws IOException {
		Wrappers.readConfigFile();
		addPlaceResponse = given().spec(preRequisite())
				             .queryParam("key", ApiResources.KEY.GetResourcePath())
				             .contentType(ContentType.JSON).body(addPlaceBody)
				.when().post(ApiResources.ADD_PLACE_RESOURCE.GetResourcePath());
	}
	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
		addPlaceResponse.then().assertThat().statusCode(200);
	}
	@Then("{string} in response is {string}")
	public void in_response_is(String key, String value) {
		assertEquals(convertRawToJson(addPlaceResponse.asPrettyString()).getString(key),value);
		System.out.println(addPlaceResponse.asPrettyString());
	}
}
