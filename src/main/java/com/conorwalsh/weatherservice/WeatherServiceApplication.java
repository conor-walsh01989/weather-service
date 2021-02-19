package com.conorwalsh.weatherservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.conorwalsh.weatherservice.converters.WeatherDtoConverter;

/**
 * Main application class used to launch the springboot application
 */
@SpringBootApplication
public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

	/**
	 * /Model mapper object. Purpose is to convert the object returned from public web service to simplified local dto object
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		//Custom converter to handle property mappings	
		mapper.addConverter(new WeatherDtoConverter());
		return mapper;
	}

}
