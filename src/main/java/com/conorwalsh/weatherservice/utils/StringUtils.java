package com.conorwalsh.weatherservice.utils;

public class StringUtils {
	
	public static boolean isPopulated(String str) {
		boolean populated = false;
		if(str != null && !str.isBlank()) {
			populated = true;
		}
		return populated;
	}

}
