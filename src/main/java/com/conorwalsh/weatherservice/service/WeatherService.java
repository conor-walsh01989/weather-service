package com.conorwalsh.weatherservice.service;

import com.conorwalsh.weatherservice.model.dto.WeatherDto;

public interface WeatherService {
	
	public WeatherDto findWeatherForCity(String city);

}
