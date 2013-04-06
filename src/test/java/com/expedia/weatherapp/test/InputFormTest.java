package com.expedia.weatherapp.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.expedia.weatherapp.config.ViewConfiguration;
import com.expedia.weatherapp.form.InputForm;
import com.expedia.weatherapp.test.config.WebContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ViewConfiguration.class}, loader = WebContextLoader.class)
public class InputFormTest {
	
	@Autowired
	private Validator validator;
	@Test
	public void validateErrorMessageForAlphaNumericZipCodeForm() {
		InputForm inputForm = new InputForm();
		inputForm.setZipCode("0qwer");
		
		Errors errors  = new BeanPropertyBindingResult(inputForm, "zipCode");
        ValidationUtils.invokeValidator(validator, inputForm, errors);
        
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals("Please enter only digits.", errors.getFieldError("zipCode").getDefaultMessage());
        
	}
	
	@Test
	public void validateErrorMessageInvalidZipCodeZipCodeForm() {
		InputForm inputForm = new InputForm();
		inputForm.setZipCode("1122");
		
		Errors errors  = new BeanPropertyBindingResult(inputForm, "zipCode");
        ValidationUtils.invokeValidator(validator, inputForm, errors);
        
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(1, errors.getFieldErrorCount("zipCode"));
        Assert.assertEquals("Invalid Zip Code.", errors.getFieldError("zipCode").getDefaultMessage());
        
	}
	
	@Test
	public void validateErrorsForBlankZipCode() {
		InputForm inputForm = new InputForm();
		inputForm.setZipCode("");
		
		Errors errors  = new BeanPropertyBindingResult(inputForm, "zipCode");
        ValidationUtils.invokeValidator(validator, inputForm, errors);
        
        Assert.assertTrue(errors.hasErrors());
        Assert.assertEquals(2, errors.getFieldErrorCount("zipCode"));
        
	}
	
	@Test
	public void validateErrorMessageForValidZipCode() {
		InputForm inputForm = new InputForm();
		inputForm.setZipCode("91117");
		
		Errors errors  = new BeanPropertyBindingResult(inputForm, "zipCode");
        ValidationUtils.invokeValidator(validator, inputForm, errors);
        
        Assert.assertFalse(errors.hasErrors());
        Assert.assertEquals(0, errors.getFieldErrorCount("zipCode"));
        
	}
}
