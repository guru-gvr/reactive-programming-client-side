package guru.springframework.reactivebeerclient.webClientConfig;

public class WebClientProperties {

	public static String BASE_URL = "http://api.springframework.guru";

	public static String BEER_V1_PATH = "/api/v1/beer";
	
	public static String BEER_V1_PATH_GET_BY_ID = "/api/v1/beer/{uuid}";
	public static String BEER_V1_PATH_GET_BY_UPC = "/api/v1/beer/{upc}";
}
