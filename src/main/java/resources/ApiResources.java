package resources;

public enum ApiResources {
	ADD_PLACE_RESOURCE("/maps/api/place/add/json"),
	GET_PLACE_RESOURCE("/maps/api/place/get/json"),
	UPDATE_PLACE_RESOURCE("/maps/api/place/update/json"),
	DELETE_PLACE_RESOURCE("/maps/api/place/delete/json"),
	KEY("qaclick123");
	
	private final String resourcePath;
	

	ApiResources(String resourcePath){
		this.resourcePath=resourcePath;
	}
	
	public String GetResourcePath() {
		return resourcePath;
	}
	
}
