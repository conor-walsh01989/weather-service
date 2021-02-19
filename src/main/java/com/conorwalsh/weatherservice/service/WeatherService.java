package com.conorwalsh.weatherservice.service;

import com.conorwalsh.weatherservice.model.dto.WeatherDto;

/**
 * Interface used to define service methods. 
 */
public interface WeatherService {
	
	public WeatherDto findWeatherForCity(String city);

}
