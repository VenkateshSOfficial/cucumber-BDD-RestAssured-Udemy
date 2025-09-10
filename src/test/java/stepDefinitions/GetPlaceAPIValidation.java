package stepDefinitions;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Optional;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import resources.ApiResources;
import utilities.Wrappers;


public class GetPlaceAPIValidation {
	String apiResource;
	String key=ApiResources.KEY.getResource();
	Response getPlaceResponse;
	String place_id;

	@Given("having placeId's created")
	public void having_place_id_s_created() {
		place_id  = PlaceValidations.getPlaceID();
		System.out.println("The id is : " + place_id);
	}
	@When("user hits {string} using {string} request")
	public void user_hits_using_request(String resource, String httpRequest) throws IOException {
		apiResource = ApiResources.valueOf(resource).getResource();
		getPlaceResponse=Wrappers.performHttpRequest(apiResource,httpRequest,Optional.empty(),key,Optional.ofNullable(place_id));
	}
	@Then("validate if name {string} matches the ones received in the response")
	public void validate_if_name_matches_the_ones_received_in_the_response(String name) {
		String actualName=Wrappers.convertRawToJson(getPlaceResponse.asPrettyString()).getString("name");
		assertEquals(actualName,name);
	}
}
