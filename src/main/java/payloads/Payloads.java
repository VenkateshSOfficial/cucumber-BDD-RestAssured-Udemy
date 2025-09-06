package payloads;

public class Payloads {
	public static String addPlacePayload(String name,String address) {
		String addPlacePayload="{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \""+name+"\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \""+address+"\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
		

		return addPlacePayload;
	}
	
	public static String updatePlacePayload(String place_id,String newAddress,String key) {
		String updatePlacePayload="{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\""+key+"\"\r\n"
				+ "}\r\n"
				+ "";
		
		return updatePlacePayload;
	}
	
	public static String deletePlacePayload(String place_id) {
		String deletePlacePayload="{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
		
		return deletePlacePayload;
	}
}
