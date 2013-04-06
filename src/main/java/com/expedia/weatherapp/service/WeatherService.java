package com.expedia.weatherapp.service;


public interface WeatherService {
	
	public <T> T getWeather(String zipcode);

}
