package com.conorwalsh.weatherservice.client;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;

/**
 * APICLient class contains actions used to interact with public API
 */
public class APIClient {

	private String apiKey;
	private String baseUrl;

	RestTemplate restTemplate = new RestTemplate();

	public APIClient(String baseUrl, String apiKey) {
		this.baseUrl = baseUrl;
		this.apiKey = apiKey;
	}

	/**
	 * Call public API with city parameters
	 */
	public CurrentWeatherResponse getCurrentWeatherForCity(String city) {
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather").queryParam("q", city)
				.queryParam("appid", apiKey).build();
		return performRequest(builder.toUriString());
	}
	
	public CurrentWeatherResponse getCurrentWeatherForCityAndStateCode(String city, String stateCode) {
		String q = city+","+stateCode;
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather").queryParam("q",  q)
				.queryParam("appid", apiKey).build();
		return performRequest(builder.toUriString());
	}
	
	public CurrentWeatherResponse getWeatherForCityAndStateCodeAndCountryCode(String city, String stateCode, String countryCode) {
		String q = city+","+stateCode+","+countryCode;
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather").queryParam("q",  q)
				.queryParam("appid", apiKey).build();
		return performRequest(builder.toUriString());
	}

	
	/**
	 * Call public API with coordinate parameters
	 */
	public CurrentWeatherResponse getCurrentWeatherForLatLong(double lat, double lon) {
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather").queryParam("lat", lat).queryParam("lon",lon)
				.queryParam("appid", apiKey).build();
		return performRequest(builder.toUriString());
	}

	/**
	 * Call public API with zipCode parameters
	 */
	public CurrentWeatherResponse getCurrentWeatherForZipCode(String zipCode) {
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather").queryParam("zip", zipCode)
				.queryParam("appid", apiKey).build();
		return restTemplate.getForEntity(builder.toUriString(), CurrentWeatherResponse.class).getBody();
	}
	
	public CurrentWeatherResponse getCurrentWeatherForZipCodeAndCountryCode(String zipCode, String countryCode) {
		String q = zipCode+","+countryCode;
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather").queryParam("zip", q)
				.queryParam("appid", apiKey).build();
		return performRequest(builder.toUriString());
	}
	
	private CurrentWeatherResponse performRequest(String uri) {
		return restTemplate.getForEntity(uri, CurrentWeatherResponse.class).getBody();
	}

}
