package com.conorwalsh.weatherservice.utilities;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.conorwalsh.weatherservice.client.response.CurrentWeatherResponse;
import com.conorwalsh.weatherservice.client.response.Weather;
import com.conorwalsh.weatherservice.model.dto.WeatherDescriptionDto;
import com.conorwalsh.weatherservice.model.dto.WeatherDetailDto;
import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.model.dto.WindDto;

public class DtoMapper implements Converter<CurrentWeatherResponse, WeatherDto> {

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

		WeatherDetailDto detail = new WeatherDetailDto();
		if (currentWeatherResponse.getMain() != null) {
			detail.setHumidity(currentWeatherResponse.getMain().getHumidity());
			detail.setPressure(currentWeatherResponse.getMain().getPressure());
			detail.setTemperature(currentWeatherResponse.getMain().getTemp());
			detail.setTemperatureMax(currentWeatherResponse.getMain().getTemp_max());
			detail.setTemperatureMin(currentWeatherResponse.getMain().getTemp_min());
			weatherDto.setWeatherDetail(detail);
		}

		if (currentWeatherResponse.getWind() != null) {
			WindDto windDto = new WindDto();
			windDto.setDegree(currentWeatherResponse.getWind().getDeg());
			windDto.setSpeed(currentWeatherResponse.getWind().getSpeed());
			weatherDto.setWindDetail(windDto);
		}
		if (currentWeatherResponse.getWeather() != null) {
			List<WeatherDescriptionDto> weatherDescriptionList = new ArrayList<WeatherDescriptionDto>();
			for (Weather desc : currentWeatherResponse.getWeather()) {
				WeatherDescriptionDto weatherDescription = new WeatherDescriptionDto();
				weatherDescription.setMain(desc.getMain());
				weatherDescription.setDescription(desc.getDescription());
				weatherDescriptionList.add(weatherDescription);
			}
			weatherDto.setWeatherDescription(weatherDescriptionList);
		}
		return weatherDto;
	}
}
