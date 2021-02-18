package com.conorwalsh.weatherservice.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.conorwalsh.weatherservice.client.response.Coordinates;
import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;
import com.conorwalsh.weatherservice.client.response.Weather;
import com.conorwalsh.weatherservice.converters.WeatherDtoConverter;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;

class WeatherDtoConverterTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testWeatherResponseToDtoConversion() {
		//Given - Source object has been populated
		CurrentWeatherResponse sampleResponse = new CurrentWeatherResponse();
		sampleResponse.setName("London");
		Coordinates coord = new Coordinates();
		coord.setLat(44);
		coord.setLon(22.55);
		sampleResponse.setCoord(coord);
		List<Weather> detailList = new ArrayList<Weather>();
		Weather weatherDetail = new Weather();
		weatherDetail.setId(1);
		weatherDetail.setDescription("Cloudy today");
		weatherDetail.setMain("Cloudy");
		detailList.add(weatherDetail);
		sampleResponse.setWeather(detailList);
	
		//When - Source object is mapped to target
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(new WeatherDtoConverter());
		WeatherDto weather = mapper.map(sampleResponse, WeatherDto.class);
	
		//Then - Properties are as expected
		assert weather.getCityName().equals("London");	
		assert weather.getLatitude() == 44;
		assert weather.getLongitude() == 22.55;
		assert weather.getWeatherDescription().size() == 1;
		assert weather.getWeatherDescription().get(0).getDescription().equals("Cloudy today");
		assert weather.getWeatherDescription().get(0).getMain().equals("Cloudy");
	}
	
}
