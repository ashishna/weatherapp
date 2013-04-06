package com.expedia.weatherapp.so;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//Top level data object for storing the converted response from weather service.
@JsonIgnoreProperties(ignoreUnknown=true)
public class ServiceResponse {

	private Response response;
	private CurrentObservation current_observation;
	
	
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public CurrentObservation getCurrent_observation() {
		return current_observation;
	}

	public void setCurrent_observation(CurrentObservation current_observation) {
		this.current_observation = current_observation;
	}

}
