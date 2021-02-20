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

/**
 * Implementation of WeatherService interface. 
 */
@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	ModelMapper modelMapper;
	
	private APIClient apiClient;

	Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	/**
	 * Constructor. Uses defined properties to create apiClient object
	 */
	public WeatherServiceImpl(@Value("${weather.api.baseUrl}") String baseUrl, @Value("${weather.api.key}") String apiKey) {
		apiClient = new APIClient(baseUrl, apiKey);
	}
	
	/**
	 * Uses api client to retrieve weather information from public API. Converts response to dto object
	 */
	@Override
	public WeatherDto findWeatherForCity(String city) {
		return modelMapper.map(apiClient.getCurrentWeatherForCity(city), WeatherDto.class);
	}


	@Override
	public WeatherDto findWeatherForCityAndStateCode(String city, String stateCode) {
		return modelMapper.map(apiClient.getCurrentWeatherForCityAndStateCode(city, stateCode), WeatherDto.class);
	}

	@Override
	public WeatherDto findWeatherForCityAndStateCodeAndCountryCode(String city, String stateCode, String countryCode) {
		return modelMapper.map(apiClient.getWeatherForCityAndStateCodeAndCountryCode(city, stateCode, countryCode), WeatherDto.class);
	}
	
	
	@Override
	public WeatherDto findWeatherByLatLong(double lat, double lon) {
		return modelMapper.map(apiClient.getCurrentWeatherForLatLong(lat,lon), WeatherDto.class);
	}


	@Override
	public WeatherDto findWeatherByZipCode(String zipCode) {
		return modelMapper.map(apiClient.getCurrentWeatherForZipCode(zipCode), WeatherDto.class);
	}

	@Override
	public WeatherDto findWeatherByZipCodeAndCountryCode(String zipCode, String countryCode) {
		return modelMapper.map(apiClient.getCurrentWeatherForZipCodeAndCountryCode(zipCode,countryCode), WeatherDto.class);
	}

	
}
