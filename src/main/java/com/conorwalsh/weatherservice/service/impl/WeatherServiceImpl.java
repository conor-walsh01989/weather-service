package com.conorwalsh.weatherservice.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.conorwalsh.weatherservice.client.APIClient;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	ModelMapper modelMapper;
	
	private APIClient apiClient;

	Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	public WeatherServiceImpl(@Value("${weather.api.baseUrl}") String baseUrl, @Value("${weather.api.key}") String apiKey) {
		apiClient = new APIClient(baseUrl, apiKey);
	}
	
	public WeatherDto findWeatherForCity(String city) {
		return modelMapper.map(apiClient.getCurrentWeatherForCity(city), WeatherDto.class);
	}

}
