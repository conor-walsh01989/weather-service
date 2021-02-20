package com.conorwalsh.weatherservice.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.HttpClientErrorException;

import com.conorwalsh.weatherservice.model.dto.WeatherDto;
import com.conorwalsh.weatherservice.service.WeatherService;
import com.conorwalsh.weatherservice.test.utilities.TestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherServiceController.class)
class WeatherServiceControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private WeatherService weatherService;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test happy path with exected request and response
	 */
	@Test
	public void testGetWeatherForCity() throws Exception {
		WeatherDto weatherDto = new WeatherDto();
		weatherDto.setCityName("New York");
		weatherDto.setCountry("USA");
		given(weatherService.findWeatherForCity("New York")).willReturn(weatherDto);
		ResultActions actions = mvc
				.perform(get("/api/v1/weather?city=New York").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		MvcResult result = actions.andReturn();
		int status = result.getResponse().getStatus();
		assert 200 == status;
		WeatherDto responseWeatherDto = TestUtils.createObjectFromJsonString(result.getResponse().getContentAsString(), WeatherDto.class);
		assert responseWeatherDto.getCityName().equals("New York");
		assert responseWeatherDto.getCountry().equals("USA");
	}
	
	/**
	 * Test exception handling with bad request
	 */
	@Test
	public void testGetWeatherForCityWithNoParamater() throws Exception {
		HttpClientErrorException badRequestException = HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Bad Request", null, null, null);
		given(weatherService.findWeatherForCity("")).willThrow(badRequestException);
		ResultActions actions = mvc
				.perform(get("/api/v1/weather?city=").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));
		MvcResult result = actions.andReturn();
		int status = result.getResponse().getStatus();
		assert 400 == status;
	}
	
	/**
	 * Test exception handling with not found
	 */
	@Test
	public void testGetWeatherForCityWithNotFound() throws Exception {
		HttpClientErrorException notFoundException = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "NOT_FOUND", null, null, null);
		given(weatherService.findWeatherForCity("New York")).willThrow(notFoundException);
		ResultActions actions = mvc
				.perform(get("/api/v1/weather?city=New York").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
		MvcResult result = actions.andReturn();
		int status = result.getResponse().getStatus();
		assert 404 == status;
	}

	/**
	 * Test exception handling unexpected exception
	 */
	@Test
	public void testGetWeatherForCityWithUnexpectedException() throws Exception {
		NullPointerException nullPointerException = new NullPointerException();
		given(weatherService.findWeatherForCity("New York")).willThrow(nullPointerException);
		ResultActions actions = mvc
				.perform(get("/api/v1/weather?city=New York").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(500));
		MvcResult result = actions.andReturn();
		int status = result.getResponse().getStatus();
		assert 500 == status;
	}

}
