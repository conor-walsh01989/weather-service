package com.conorwalsh.weatherservice.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPopulationWithPopulatedStrings() {
		assert StringUtils.isPopulated("abc");
		assert StringUtils.isPopulated("a");
		assert StringUtils.isPopulated("               c");
	}
	
	@Test
	void testPopulcationWithNullAndEmptyStrings() {
		assert !StringUtils.isPopulated("");
		assert !StringUtils.isPopulated(null);
		assert !StringUtils.isPopulated("                    ");
	}

}
