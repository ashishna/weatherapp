package com.expedia.weatherapp.config;


import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.expedia.weatherapp.service.WeatherService;
import com.expedia.weatherapp.service.impl.WeatherServiceImpl;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.expedia.weatherapp.controllers" })
public class ViewConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private Validator validator;
	
	@Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }

	 @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/views/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	    }

	@Override
	public Validator getValidator() {
		return validator;
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
	
	@Bean
	public static PropertyPlaceholderConfigurer properties() { //Load configurable properties files.
	  PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
	  Resource[] resources = new ClassPathResource[] { new ClassPathResource("com/expedia/config/application.properties") };
	  configurer.setLocations(resources);
	  configurer.setIgnoreUnresolvablePlaceholders( true );
	  return configurer;
	}
	
	@Bean
	public Validator validator() { //used for JSR303 based validations.
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setProviderClass(HibernateValidator.class);
		return validator;
	}
}
