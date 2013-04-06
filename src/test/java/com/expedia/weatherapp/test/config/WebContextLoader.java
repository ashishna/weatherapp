package com.expedia.weatherapp.test.config;


public class WebContextLoader extends GenericWebContextLoader{
	
	public WebContextLoader() {
		super("src/main/webapp", false);
	}
}
