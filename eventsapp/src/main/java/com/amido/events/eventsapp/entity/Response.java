package com.amido.events.eventsapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	
	private EventRoot events;
	private int total_items;
	
	public EventRoot getEvents() {
		return events;
	}

	public void setEvents(EventRoot events) {
		this.events = events;
	}

	public int getTotal_items() {
		return total_items;
	}

	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}

	

	
	
	
	
}
