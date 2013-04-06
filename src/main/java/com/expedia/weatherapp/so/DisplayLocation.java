package com.expedia.weatherapp.so;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DisplayLocation {

	private String full;
	private String city;
	private String state;
	
	public String getFull() {
		return full;
	}

	public void setFull(String full) {
		this.full = full;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
