package com.conorwalsh.weatherservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.service.WeatherService;

import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping(path = {"/api/v1/weather"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceController.class);

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherDto findWeatherForCity(@RequestParam("city") String city) {
		return weatherService.findWeatherForCity(city);
	}
	

}
