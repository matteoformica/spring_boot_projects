package com.amido.events.eventsapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRoot {

	private List<EventDetails> event;

	public List<EventDetails> getEvent() {
		return event;
	}

	public void setEvent(List<EventDetails> event) {
		this.event = event;
	}
	
}
