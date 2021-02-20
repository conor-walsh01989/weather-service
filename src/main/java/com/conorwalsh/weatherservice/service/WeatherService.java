package com.conorwalsh.weatherservice.service;

import com.conorwalsh.weatherservice.model.dto.WeatherDto;

/**
 * Interface used to define service methods. 
 */
public interface WeatherService {
	
	public WeatherDto findWeatherForCity(String city);
	
	public WeatherDto findWeatherForCityAndStateCode(String city, String stateCode);
	
	public WeatherDto findWeatherForCityAndStateCodeAndCountryCode(String city, String stateCode, String country);
	
	public WeatherDto findWeatherByLatLong(double lat, double lon);

	public WeatherDto findWeatherByZipCode(String zipCode);
	
	public WeatherDto findWeatherByZipCodeAndCountryCode(String zipCode, String countryCode);

}
