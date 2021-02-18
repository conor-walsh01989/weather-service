package com.conorwalsh.weatherservice.model.dto;

public class WeatherDescriptionDto {

	private String main;
	private String description;
	
	public WeatherDescriptionDto() {
		
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
