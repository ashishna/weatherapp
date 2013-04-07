package com.expedia.weatherapp.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.expedia.weatherapp.config.RootConfiguration;
import com.expedia.weatherapp.config.ViewConfiguration;
import com.expedia.weatherapp.service.WeatherService;
import com.expedia.weatherapp.so.ServiceResponse;
import com.expedia.weatherapp.test.config.WebContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfiguration.class , ViewConfiguration.class}, loader = WebContextLoader.class)
public class WeatherServiceTest {

	@Autowired
	private WeatherService weatherService;
	
	@Test
	public void testResponseForAnyString() {
		ServiceResponse serviewResponse = null;
		serviewResponse = weatherService.getWeather(anyString());
		
		assertNotNull(serviewResponse);
	}
	
	@Test
	public void testResponseForNullZipCode() {
		ServiceResponse serviewResponse = null;
		serviewResponse = weatherService.getWeather(null);
		
		assertNotNull(serviewResponse);
		assertNotNull(serviewResponse.getResponse().getError());
		assertNotNull(serviewResponse.getResponse().getError().getDescription());
		assertEquals("you must supply a location query", serviewResponse.getResponse().getError().getDescription());
	}
	
	@Test
	public void testResponseForValidZipCode() {
		ServiceResponse serviewResponse = null;
		serviewResponse = weatherService.getWeather("94117");
		
		assertNotNull(serviewResponse);
		assertNull(serviewResponse.getResponse().getError());
		assertNotNull(serviewResponse.getCurrent_observation());
		assertNotNull(serviewResponse.getCurrent_observation().getDisplay_location());
		assertEquals("CA", serviewResponse.getCurrent_observation().getDisplay_location().getState());
		assertEquals("San Francisco", serviewResponse.getCurrent_observation().getDisplay_location().getCity());
		assertNotNull(serviewResponse.getCurrent_observation().getTemp_f());
	}
	
	@Test
	public void testResponseForInValidZipCode() {
		ServiceResponse serviewResponse = null;
		serviewResponse = weatherService.getWeather("00000");
		
		assertNotNull(serviewResponse);
		assertNotNull(serviewResponse.getResponse().getError());
		assertNotNull(serviewResponse.getResponse().getError().getDescription());
		assertEquals("No cities match your search query", serviewResponse.getResponse().getError().getDescription());
	}
}
