package com.amido.events.eventsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amido.events.eventsapp.entity.EventDetails;

import com.amido.events.eventsapp.service.EventSearchService;



@RestController
public class EventsController {

	@Autowired
	private EventSearchService eventSearchService ;
	
	
	@RequestMapping(path="/search/{location}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EventDetails>> getSearchResultsByLocation(@PathVariable String location){
		List<EventDetails> events = eventSearchService.searchEventsByLocation(location);
		
		if(events.size()==0){
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			      .body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK)
		        .body(events);
	}
}
