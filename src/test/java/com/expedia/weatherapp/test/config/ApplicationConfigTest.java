package com.expedia.weatherapp.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.expedia.weatherapp.config.RootConfiguration;
import com.expedia.weatherapp.config.ViewConfiguration;

@Configuration
@ContextConfiguration(classes = { ViewConfiguration.class, RootConfiguration.class })
public class ApplicationConfigTest {
	
	
}
