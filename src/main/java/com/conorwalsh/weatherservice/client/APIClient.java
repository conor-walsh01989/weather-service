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
	 * Call public API with a city to search for the current weather in that city
	 */
	public CurrentWeatherResponse getCurrentWeatherForCity(String city) {
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/weather").queryParam("q", city)
				.queryParam("appid", apiKey).build();
		return restTemplate.getForEntity(builder.toUriString(), CurrentWeatherResponse.class).getBody();
	}

}
