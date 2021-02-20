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

/**
 * Rest interface of the application
 */
@RestController
@RequestMapping(path = {"/api/v1/weather"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceController.class);

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(path = "/city",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherDto findWeatherForCity(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "stateCode", required = false) String stateCode,
			@RequestParam(name = "countryCode", required = false) String countryCode) {
		logger.debug("City request received with paramater(s): city:"+city+ " ,stateCode:" + stateCode+" countryCode:"+countryCode);
		return weatherService.findWeatherForCity(city);
	}
	
	@RequestMapping(path = "/location",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherDto findWeatherForLatLong(@RequestParam(name = "lat", required = true) double lat, @RequestParam(name = "long", required = true)double lon) {
		logger.debug("Location request received with paramater(s): lat:"+lat+ " ,lon:"+lon);
		return weatherService.findWeatherByLatLong(lat, lon);
	}
	
	@RequestMapping(path = "/zipCode",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherDto findWeatherForLatLong(@RequestParam(name = "zipCode", required = true) String zipCode, @RequestParam(name = "countryCode", required = false)String countryCode) {
		logger.debug("Location request received with paramater(s): zipCode:"+zipCode+ " ,countryCode:"+countryCode);
		return weatherService.findWeatherByZipCodeAndCountryCode(zipCode, countryCode);
	}
	

}
