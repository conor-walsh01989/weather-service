package com.conorwalsh.weatherservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.conorwalsh.weatherservice.converters.WeatherDtoConverter;

@SpringBootApplication
public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		//Custom converter to handle property mappings	
		mapper.addConverter(new WeatherDtoConverter());
		return mapper;
	}

}
