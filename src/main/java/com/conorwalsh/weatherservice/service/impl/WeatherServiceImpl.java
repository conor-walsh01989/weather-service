package com.conorwalsh.weatherservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.conorwalsh.weatherservice.client.APIClient;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Value("${weather.api.key}")
	private String apiKey;

	@Value("${weather.api.baseUrl")
	private String baseUrl;

	@Autowired
	ModelMapper modelMapper;
	
	public WeatherDto findWeatherForCity(String city) {
		APIClient apiClient = new APIClient(baseUrl, apiKey);
		return modelMapper.map(apiClient.getCurrentWeatherForCity(city), WeatherDto.class);
	}

}
