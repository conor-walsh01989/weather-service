package com.conorwalsh.weatherservice.client.response;

/**
 * Wind class used to store wind information found in the response from the public API
 */
public class Wind {
	
	private double speed;
	private double deg;
	
	public Wind() {
		
	}
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDeg() {
		return deg;
	}
	public void setDeg(double deg) {
		this.deg = deg;
	}
	
	

}
