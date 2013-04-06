package com.expedia.weatherapp.so;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrentObservation {

	private DisplayLocation display_location;
	private String temp_f;
	
	public DisplayLocation getDisplay_location() {
		return display_location;
	}

	public void setDisplay_location(DisplayLocation display_location) {
		this.display_location = display_location;
	}

	public String getTemp_f() {
		return temp_f;
	}

	public void setTemp_f(String temp_f) {
		this.temp_f = temp_f;
	}

	
	
}
