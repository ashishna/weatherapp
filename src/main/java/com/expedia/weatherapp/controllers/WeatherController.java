package com.expedia.weatherapp.controllers;

 import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.expedia.weatherapp.form.InputForm;
import com.expedia.weatherapp.service.WeatherService;
import com.expedia.weatherapp.so.ServiceResponse;

@Controller
@RequestMapping("/")
public class WeatherController {
   
	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav  = new ModelAndView("index");
		InputForm  inputForm = new InputForm();
		mav.addObject("inputForm",inputForm);
		mav.addObject("error","");
		return mav;
	}
	
	@RequestMapping(value="index",method = RequestMethod.POST)
	public ModelAndView index(@ModelAttribute("inputForm") @Valid InputForm  inputForm,  BindingResult result) {
		ModelAndView modelAndView  = new ModelAndView("index");
		if (result.hasErrors()) {
			modelAndView.addObject("error","");
            return  modelAndView;
        }
		
		ServiceResponse response = weatherService.getWeather(inputForm.getZipCode());
		if(null != response) {
			if(null  == response.getResponse().getError()) {
				modelAndView.addObject("temp",response.getCurrent_observation().getTemp_f());
				modelAndView.addObject("city",response.getCurrent_observation().getDisplay_location().getCity());
				modelAndView.addObject("state",response.getCurrent_observation().getDisplay_location().getState());	
			} else {
				modelAndView.addObject("error","Zip code not found.");
			}
			
		}
		return modelAndView;
	}
	
}
