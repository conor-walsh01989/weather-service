package com.conorwalsh.weatherservice;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;
import com.conorwalsh.weatherservice.client.response.Weather;
import com.conorwalsh.weatherservice.model.dto.WeatherDescriptionDto;
import com.conorwalsh.weatherservice.model.dto.WeatherDetailDto;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.model.dto.WindDto;
import com.conorwalsh.weatherservice.utilities.DtoMapper;

@SpringBootApplication
public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

	//Model mapper object. Purpose is to convert the object returned from public web service to simplified local object
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		//Custom converter to handle property mappings	
		mapper.addConverter(DtoMapper.getWeatherConverter());
		return mapper;
	}

}
