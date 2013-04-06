package com.expedia.weatherapp.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class InputForm {

	@Digits(integer=10, fraction=0, message = "Please enter only digits.")
    @Size(min=5,max=5,message="Invalid Zip Code.")
	private String zipCode;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
