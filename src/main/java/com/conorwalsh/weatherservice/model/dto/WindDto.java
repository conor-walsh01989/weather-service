package com.conorwalsh.weatherservice.model.dto;

public class WindDto {

	private double speed;
	private double degree;
	
	public WindDto() {
		
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}
	
}
