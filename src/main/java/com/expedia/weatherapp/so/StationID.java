package com.expedia.weatherapp.so;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class StationID {

	private float temp_f;

	public float getTemp_f() {
		return temp_f;
	}

	public void setTemp_f(float temp_f) {
		this.temp_f = temp_f;
	}
	
	
}
