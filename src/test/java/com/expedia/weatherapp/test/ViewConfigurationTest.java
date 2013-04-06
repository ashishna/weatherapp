package com.expedia.weatherapp.test;

import static junit.framework.Assert.assertNotNull;

import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;

import com.expedia.weatherapp.config.ViewConfiguration;
import com.expedia.weatherapp.service.WeatherService;
import com.expedia.weatherapp.test.config.WebContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ViewConfiguration.class},loader = WebContextLoader.class)

public class ViewConfigurationTest {
	
	@Autowired
	ViewResolver viewResolver;

	@Autowired
	Validator validator;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	WeatherService weatherService; 
	
	@Autowired
	RestTemplate restTemplate;
	
	@Test
	public void checkViewResolverNotNull() {
		assertNotNull(viewResolver);
	}

	@Test
	public void checkValidatorNotNull()  {
		assertNotNull(validator);
	}
	
	@Test
	public void checkRestTemplateNotNull() {
		assertNotNull(restTemplate);
	}
	
	@Test
	public void checkWeatherServiceNotNull() {
		assertNotNull(weatherService);
	}
}
