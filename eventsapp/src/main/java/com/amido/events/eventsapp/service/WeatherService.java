package com.amido.events.eventsapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.amido.events.eventsapp.entity.Response;
import com.amido.events.eventsapp.entity.Weather;
import com.amido.events.eventsapp.entity.WeatherResponse;
import com.amido.events.eventsapp.exception.WeatherException;

@Service
public class WeatherService {

	@Value( "${weather.service.baseurl}" )
	private String serviceBaseUrl;
	
	@Value( "${weather.service.app_key}" )
	private String appKey;
	
	
	private final RestTemplate restTemplate;
	
	public WeatherService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	/**
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public WeatherResponse getWeatherByLocation(String latitude, String longitude){
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serviceBaseUrl)
				.queryParam("appid", appKey)
				.queryParam("lat", latitude)
				.queryParam("lon", longitude);
				
		WeatherResponse resp = this.restTemplate.getForObject(builder.toUriString(), WeatherResponse.class);	
		
		if(resp.getWeather()==null || resp.getWeather().size() == 0 ){
			throw new WeatherException("Weather not found for location : "+latitude+","+longitude);
		}
		
		//return the first item of the weather array
		return resp;
	}
	
	
}
