package com.conorwalsh.weatherservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.conorwalsh.weatherservice.client.response.Coordinates;
import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;
import com.conorwalsh.weatherservice.client.response.Weather;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;

@SpringBootTest
class WeatherServiceApplicationTests {

	//TODO Perhaps this object and test is better to be placed outside of the main WeatherServiceApplication class?
	@Autowired
	ModelMapper modelMapper;
	
	@Test
	void contextLoads() {
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
		WeatherDto weather = modelMapper.map(sampleResponse, WeatherDto.class);
	
		//Then - Properties are as expected
		assert weather.getCityName().equals("London");	
		assert weather.getLatitude() == 44;
		assert weather.getLongitude() == 22.55;
		assert weather.getWeatherDescription().size() == 1;
		assert weather.getWeatherDescription().get(0).getDescription().equals("Cloudy today");
		assert weather.getWeatherDescription().get(0).getMain().equals("Cloudy");
	}
	
	

}
