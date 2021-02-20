package com.conorwalsh.weatherservice.test.utilities;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TestUtils class containing useful methods that can be used across all tests.
 */
public class TestUtils {

	/**
	 * Method takes a json string and a class. Parses the json and populates an instance of the class with its properties
	 */
	public static <T> T createObjectFromJsonString(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
