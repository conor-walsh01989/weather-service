package com.conorwalsh.weatherservice.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;
import com.conorwalsh.weatherservice.client.response.Weather;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.MediaType;

class APIClientTest {

	private MockRestServiceServer mockServer;

	APIClient apiClient;

	@BeforeEach
	public void setUp() {
		apiClient = new APIClient("http://localhost:8080", "123");
		mockServer = MockRestServiceServer.createServer(apiClient.restTemplate);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testSimpleRequest() {
		// Given - A mock web service endpoint has been configured
		mockServer.expect(once(), requestTo("http://localhost:8080/weather?q=Galway&appid=123"))
				.andRespond(withSuccess(getTestWeatherResponse(), MediaType.APPLICATION_JSON));

		// When I call the endpoint with a valid request
		CurrentWeatherResponse weatherResponse = apiClient.getCurrentWeatherForCity("Galway");

		// Then the request was executed successfully
		mockServer.verify();
		assert weatherResponse.getName().equals("Galway");
	}
	
	@Test
	public void testResponseObjectPopulation() {
		// Given - A mock web service endpoint has been configured
		mockServer.expect(once(), requestTo("http://localhost:8080/weather?q=Galway&appid=123"))
				.andRespond(withSuccess(getTestWeatherResponse(), MediaType.APPLICATION_JSON));

		// When I call the endpoint with a valid request
		CurrentWeatherResponse weatherResponse = apiClient.getCurrentWeatherForCity("Galway");

		// Then the content returned from the endpoint was handled and parsed correctly by the client
		mockServer.verify();
		// Check that all properties are populated correctly
		// Base properties
		assert weatherResponse.getBase().equals("stations");
		assert weatherResponse.getVisibility() == 10000;
		assert weatherResponse.getDt() == 1613660145;
		assert weatherResponse.getTimezone() == 0;
		assert weatherResponse.getId() == 2964180;
		assert weatherResponse.getName().equals("Galway");
		assert weatherResponse.getCod() == 200;
		// Coordinates
		assert weatherResponse.getCoord().getLon() == -9.0489;
		assert weatherResponse.getCoord().getLat() == 53.2719;
		// Weather
		assert weatherResponse.getWeather().size() == 1;
		Weather weather = weatherResponse.getWeather().get(0);
		assert weather.getId() == 803;
		assert weather.getMain().equals("Clouds");
		assert weather.getDescription().equals("broken clouds");
		assert weather.getIcon().equals("04d");
		// Main
		assert weatherResponse.getMain().getTemp() == 281.06;
		assert weatherResponse.getMain().getFeels_like() == 273.79;
		assert weatherResponse.getMain().getTemp_min() == 280.93;
		assert weatherResponse.getMain().getTemp_max() == 281.15;
		assert weatherResponse.getMain().getPressure() == 998;
		assert weatherResponse.getMain().getHumidity() == 71;
		// Wind
		assert weatherResponse.getWind().getSpeed() == 8.23;
		assert weatherResponse.getWind().getDeg() == 250;
		// Clouds
		assert weatherResponse.getClouds().getAll() == 75;
		// Sys
		assert weatherResponse.getSys().getType() == 1;
		assert weatherResponse.getSys().getId() == 1569;
		assert weatherResponse.getSys().getCountry().equals("IE");
		assert weatherResponse.getSys().getSunrise() == 1613634478;
		assert weatherResponse.getSys().getSunset() == 1613670754;

	}

	@Test
	public void testBadRequestResponse() {
		// Given - A mock web service endpoint has been configured to respond with a badrequest
		mockServer.expect(once(), requestTo("http://localhost:8080/weather?q=&appid=123")).andRespond(withBadRequest());

		// Then - the client throws an exception
		Assertions.assertThrows(HttpClientErrorException.BadRequest.class, () -> {
			apiClient.getCurrentWeatherForCity("");
		});
	}
	
	@Test
	public void testInternalErrorResponse() {
		// Given - A mock web service endpoint has been configured to respond with a serverError
		mockServer.expect(once(), requestTo("http://localhost:8080/weather?q=&appid=123")).andRespond(withServerError());

		// Then - the client throws an exception
		Assertions.assertThrows(HttpServerErrorException.InternalServerError.class, () -> {
			apiClient.getCurrentWeatherForCity("");
		});
	}
	

	private final String getTestWeatherResponse() {
		String sampleJson = "";
		try {
			sampleJson = new String(
					Files.readAllBytes(Paths.get("test-resources/rest-responses/current-weather.json")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sampleJson;
	}
}
