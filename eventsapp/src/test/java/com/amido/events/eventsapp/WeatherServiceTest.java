package com.amido.events.eventsapp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.amido.events.eventsapp.service.WeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {
	
	@Autowired
	WeatherService weatherService;
	
	@Test
	public void searchByLocationNotNullOrEmpty(){
		assertNotNull(weatherService.getWeatherByLocation("51.5100524", "-0.1228806"));
	}
	
	@Test
	public void searchByLocationIsExpectedLondon(){
		assertTrue(weatherService.getWeatherByLocation("51.5100524", "-0.1228806").getName().equalsIgnoreCase("London"));
	}
	
//	@Test
//	public void searchByLocationNotNullOrEmpty(){
//		assertTrue(weatherService.getWeatherByLocation("51.5100524", "-0.1228806").getWeather().get(0).getMain().equals("Clear"));
//	}
}
