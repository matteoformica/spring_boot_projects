package com.amido.events.eventsapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDetails {
	
	private String country_name;
	private String description;
	private String country_abbr;
	private String venue_address;
	private String venue_name;
	private String latitude;
	private String longitude;
	
	public String getVenue_name() {
		return venue_name;
	}

	public String getVenue_address() {
		return venue_address;
	}

	public void setVenue_address(String venue_address) {
		this.venue_address = venue_address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry_abbr() {
		return country_abbr;
	}

	public void setCountry_abbr(String country_abbr) {
		this.country_abbr = country_abbr;
	}

	

}
