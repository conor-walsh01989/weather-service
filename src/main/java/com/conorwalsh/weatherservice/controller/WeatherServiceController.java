package com.conorwalsh.weatherservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.service.WeatherService;
import com.conorwalsh.weatherservice.utils.StringUtils;

import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Rest interface of the application
 */
@RestController
@RequestMapping(path = { "/api/v1/weather" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherServiceController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceController.class);

	@Autowired
	private WeatherService weatherService;

	/** 
	 * REST action for city based searches
	 */
	@RequestMapping(path = "/city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherDto findWeatherForCity(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name = "stateCode", required = false) String stateCode,
			@RequestParam(name = "countryCode", required = false) String countryCode) {
		logger.debug("City request received with paramater(s): city:" + city + " ,stateCode:" + stateCode
				+ " countryCode:" + countryCode);
		WeatherDto weatherDto = null;
		// Call the correct method based on supplied parameters
		if (StringUtils.isPopulated(city) && StringUtils.isPopulated(stateCode)
				&& StringUtils.isPopulated(countryCode)) {
			weatherDto = weatherService.findWeatherForCityAndStateCodeAndCountryCode(city, stateCode, countryCode);
		} else if (StringUtils.isPopulated(city) && StringUtils.isPopulated(stateCode)) {
			weatherDto = weatherService.findWeatherForCityAndStateCode(city, stateCode);
		}
		// City doesnt need to be checked as its marked as required=true
		else {
			weatherDto = weatherService.findWeatherForCity(city);
		}
		return weatherDto;
	}

	/** 
	 * REST action for coordinate based searches
	 */
	@RequestMapping(path = "/location", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherDto findWeatherForLatLong(@RequestParam(name = "lat", required = true) double lat,
			@RequestParam(name = "long", required = true) double lon) {
		logger.debug("Location request received with paramater(s): lat:" + lat + " ,lon:" + lon);
		return weatherService.findWeatherByLatLong(lat, lon);
	}

	/** 
	 * REST action for zipcode based searches
	 */
	@RequestMapping(path = "/zipCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherDto findWeatherForLatLong(@RequestParam(name = "zipCode", required = true) String zipCode,
			@RequestParam(name = "countryCode", required = false) String countryCode) {
		logger.debug(
				"Location request received with paramater(s): zipCode:" + zipCode + " ,countryCode:" + countryCode);
		WeatherDto weatherDto = null;
		// Call the correct method based on supplied parameters
		if (StringUtils.isPopulated(zipCode) && StringUtils.isPopulated(countryCode)) {
			weatherDto = weatherService.findWeatherByZipCodeAndCountryCode(zipCode, countryCode);
		} else {
			weatherDto = weatherService.findWeatherByZipCode(zipCode);
		}

		return weatherDto;
	}

}
