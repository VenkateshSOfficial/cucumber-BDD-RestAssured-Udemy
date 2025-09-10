package resources;

public enum ApiResources {
	ADD_PLACE_RESOURCE("/maps/api/place/add/json"),
	GET_PLACE_RESOURCE("/maps/api/place/get/json"),
	UPDATE_PLACE_RESOURCE("/maps/api/place/update/json"),
	DELETE_PLACE_RESOURCE("/maps/api/place/delete/json"),
	KEY("qaclick123");
	
	private final String resource;
	

	ApiResources(String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
	
}
