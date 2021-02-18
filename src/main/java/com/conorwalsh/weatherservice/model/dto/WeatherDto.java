package com.conorwalsh.weatherservice.model.dto;

import java.util.List;

public class WeatherDto {
	
	private String cityName;
	private String country;
	private double latitude;
	private double longitude;
	private int visibility;
	
	private List<WeatherDescriptionDto> weatherDescription;
	private WeatherDetailDto weatherDetail;
	private WindDto windDetail;
	
	public WeatherDto() {
		
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<WeatherDescriptionDto> getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(List<WeatherDescriptionDto> weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public WeatherDetailDto getWeatherDetail() {
		return weatherDetail;
	}

	public void setWeatherDetail(WeatherDetailDto weatherDetail) {
		this.weatherDetail = weatherDetail;
	}

	public WindDto getWindDetail() {
		return windDetail;
	}

	public void setWindDetail(WindDto windDetail) {
		this.windDetail = windDetail;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
}
