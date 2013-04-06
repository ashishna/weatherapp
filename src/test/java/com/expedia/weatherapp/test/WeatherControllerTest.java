package com.expedia.weatherapp.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.expedia.weatherapp.config.ViewConfiguration;
import com.expedia.weatherapp.test.config.WebContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ViewConfiguration.class}, loader = WebContextLoader.class)

public class WeatherControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.context).build();
	}
	
	@Test
	public void testDefaultIndexPageRendering()  throws Exception {
		mockMvc.perform(get("/index.html")).
				andExpect(view().name(containsString("index")))
				.andExpect(model().attributeExists("inputForm"))
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", Mockito.matches("")));
	}
	
	@Test
	public void testPostWithValiddZipCode()  throws Exception {
		mockMvc.perform(post("/index.html").param("zipCode", "91118"))
				.andExpect(view().name(containsString("index")))
				.andExpect(model().attributeExists("inputForm"))
				.andExpect(model().hasNoErrors());
	}
	
	@Test
	public void testPostWithBlankZipCode()  throws Exception {
		mockMvc.perform(post("/index.html").param("zipCode", ""))
				.andExpect(view().name(containsString("index")))
				.andExpect(model().attributeExists("inputForm"))
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attributeHasFieldErrors("inputForm", new String[]{"zipCode"}));
	}
	
	@Test
	public void testPostWithAlphaNumericZipCode()  throws Exception {
		mockMvc.perform(post("/index.html").param("zipCode", "123de"))
				.andExpect(view().name(containsString("index")))
				.andExpect(model().attributeExists("inputForm"))
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attributeHasFieldErrors("inputForm", new String[]{"zipCode"}));
	}
	
	@Test
	public void testPostWithNonNumericZipCode()  throws Exception {
		mockMvc.perform(post("/index.html").param("zipCode", "ddffde"))
				.andExpect(view().name(containsString("index")))
				.andExpect(model().attributeExists("inputForm"))
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attributeHasFieldErrors("inputForm", new String[]{"zipCode"}));
	}
	
	@After
	public void tearDown() {
		mockMvc = null;
	}
}
