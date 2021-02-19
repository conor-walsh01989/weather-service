package com.conorwalsh.weatherservice.converters;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;
import com.conorwalsh.weatherservice.client.response.MainData;
import com.conorwalsh.weatherservice.client.response.Weather;
import com.conorwalsh.weatherservice.client.response.Wind;
import com.conorwalsh.weatherservice.model.dto.WeatherDescriptionDto;
import com.conorwalsh.weatherservice.model.dto.WeatherDetailDto;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.model.dto.WindDto;

public class WeatherDtoConverter implements Converter<CurrentWeatherResponse, WeatherDto> {

	/**
	 * Converts public API response to simplified dto
	 */
	@Override
	public WeatherDto convert(MappingContext<CurrentWeatherResponse, WeatherDto> context) {
		CurrentWeatherResponse currentWeatherResponse = context.getSource();
		WeatherDto weatherDto = new WeatherDto();
		weatherDto.setCityName(currentWeatherResponse.getName());
		if (currentWeatherResponse.getSys() != null) {
			weatherDto.setCountry(currentWeatherResponse.getSys().getCountry());
		}
		if (currentWeatherResponse.getCoord() != null) {
			weatherDto.setLongitude(currentWeatherResponse.getCoord().getLon());
			weatherDto.setLatitude(currentWeatherResponse.getCoord().getLat());
		}
		weatherDto.setVisibility(currentWeatherResponse.getVisibility());
		weatherDto.setWeatherDetail(buildWeatherDetailDtoFromMainDetail(currentWeatherResponse.getMain()));
		weatherDto.setWindDetail(buildWindDtoFromWind(currentWeatherResponse.getWind()));
		weatherDto.setWeatherDescription(buildWeatherDescriptionDtoListFromWeatherList(currentWeatherResponse.getWeather()));
		return weatherDto;
	}
	
	/**
	 * Creates detail Dto from returned data from public API
	 */
	private WeatherDetailDto buildWeatherDetailDtoFromMainDetail(MainData mainDetail) {
		WeatherDetailDto detail = new WeatherDetailDto();
		if (mainDetail != null) {
			detail.setHumidity(mainDetail.getHumidity());
			detail.setPressure(mainDetail.getPressure());
			detail.setTemperature(mainDetail.getTemp());
			detail.setTemperatureMax(mainDetail.getTemp_max());
			detail.setTemperatureMin(mainDetail.getTemp_min());
		}
		return detail;
	}
	
	/**
	 * Creates wind Dto from returned data from public API
	 */
	private WindDto buildWindDtoFromWind(Wind wind) {
		WindDto windDto = new WindDto();
		if (wind != null) {
			windDto.setDegree(wind.getDeg());
			windDto.setSpeed(wind.getSpeed());
		}
		return windDto;
	}
	
	/**
	 * Creates description list Dto from returned data from public API
	 */
	private List<WeatherDescriptionDto> buildWeatherDescriptionDtoListFromWeatherList(List<Weather> weatherList){
		List<WeatherDescriptionDto> weatherDescriptionList = new ArrayList<WeatherDescriptionDto>();
		if (weatherList != null) {	
			for (Weather desc : weatherList) {
				WeatherDescriptionDto weatherDescription = new WeatherDescriptionDto();
				weatherDescription.setMain(desc.getMain());
				weatherDescription.setDescription(desc.getDescription());
				weatherDescriptionList.add(weatherDescription);
			}
		}
		return weatherDescriptionList;
	}
}
