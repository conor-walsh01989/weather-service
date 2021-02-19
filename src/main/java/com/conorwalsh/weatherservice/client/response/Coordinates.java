package com.conorwalsh.weatherservice.client.response;

/**
 * Coordinates class used to store coordinate information found in the response from the public API
 */
public class Coordinates {
	
	private double lon;
	private double lat;
	
	
	public Coordinates() {
	
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	

}
