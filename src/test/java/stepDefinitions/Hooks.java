package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {
	@Before("@getPlace")
	public void BeforeScenario() throws IOException {
		PlaceValidations validations=new PlaceValidations();
		if(PlaceValidations.getPlaceID()==null){
			validations.user_has_add_place_payload_with_and("sweet house","p koil street");
			validations.user_calls_with_http_request("ADD_PLACE_RESOURCE","POST");
			validations.in_response_is("status","OK");
		}
	}
}
