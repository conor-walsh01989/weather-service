package com.conorwalsh.weatherservice.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.conorwalsh.weatherservice.client.APIClient;
import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Value("${weather.api.key}")
	private String apiKey;
	
	@Value("${weather.api.baseUrl")
	private String baseUrl;
	
	
	public WeatherDto findWeatherForCity(String city) {
		APIClient apiClient = new APIClient(baseUrl,apiKey);
		CurrentWeatherResponse weatherResponse =  apiClient.getCurrentWeatherForCity(city);
		return null;
	}
}
