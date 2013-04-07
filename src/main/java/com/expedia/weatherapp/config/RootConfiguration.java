package com.expedia.weatherapp.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.expedia.weatherapp.service.WeatherService;
import com.expedia.weatherapp.service.impl.WeatherServiceImpl;

@Configuration
@ComponentScan(basePackages = { "com.expedia.weatherapp.service" })
public class RootConfiguration {
	
	@Bean
	public static PropertyPlaceholderConfigurer properties() { //Load configurable properties files.
	  PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
	  Resource[] resources = new ClassPathResource[] { new ClassPathResource("com/expedia/config/application.properties") };
	  configurer.setLocations(resources);
	  configurer.setIgnoreUnresolvablePlaceholders( true );
	  return configurer;
	}
	
	@Bean
	public WeatherService weatherService() {
		WeatherService serviceImpl = new WeatherServiceImpl();
		return serviceImpl;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter()); //inject Jackson mapper for converting JSON response using Jackson API
		return restTemplate;
	}
	
}
