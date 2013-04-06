package com.expedia.weatherapp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.expedia.weatherapp.service.WeatherService;
import com.expedia.weatherapp.so.ServiceResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${api.url}")
	private String serviceURL;
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceResponse getWeather(String zipcode) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("zipcode", zipcode);
		params.put("api.key", apiKey);
		
		ServiceResponse response = restTemplate.getForObject(serviceURL, ServiceResponse.class, params);
		return response;
	}

}
