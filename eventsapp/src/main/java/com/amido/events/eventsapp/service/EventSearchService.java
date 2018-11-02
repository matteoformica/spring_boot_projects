package com.amido.events.eventsapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.amido.events.eventsapp.entity.EventDetails;
import com.amido.events.eventsapp.entity.Response;

@Service
public class EventSearchService {

	@Value( "${event.service.baseurl}" )
	private String eventServiceBaseUrl;
	
	@Value( "${event.service.app_key}" )
	private String appKey;
	
	
	private final RestTemplate restTemplate;
	
	public EventSearchService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public List<EventDetails> searchEventsByLocation(String location){
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
        //Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);
		//ResponseEntity<Response> resp = restTemplate.exchange(eventServiceBaseUrl+"?app_key=PHV3rqv2ScXgNDTJ&location="+location, HttpMethod.GET, entity, Response.class);
		
		// Query parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(eventServiceBaseUrl)
		.queryParam("app_key", appKey)
		.queryParam("location", location);
		
		Response resp = this.restTemplate.getForObject(builder.toUriString(), Response.class);	
		
		if(resp.getTotal_items() == 0){
			return Collections.EMPTY_LIST;	
		}
		
		return resp.getEvents().getEvent();
	}
	
	
	
}
