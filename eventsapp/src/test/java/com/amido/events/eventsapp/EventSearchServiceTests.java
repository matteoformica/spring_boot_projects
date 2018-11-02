package com.amido.events.eventsapp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amido.events.eventsapp.exception.EventException;
import com.amido.events.eventsapp.service.EventSearchService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EventSearchServiceTests {

	@Autowired
	EventSearchService eventService;
	
	@Test
	public void searchByLocationNotNullOrEmpty(){
		assertTrue(eventService.searchEventsByLocation("London")!=null);
		assertTrue(eventService.searchEventsByLocation("London").size()>0);
	}

	@Test
	public void searchByLocationCountry(){
		assertTrue(eventService.searchEventsByLocation("London").get(0).getCountry_abbr()!=null);
		assertTrue(eventService.searchEventsByLocation("London").get(0).getCountry_abbr().equals("GBR"));
	}

	
	@Test
	public void searchByLocationNull(){
		assertTrue(eventService.searchEventsByLocation("XXX").size() == 0);
	}
	
	@Test
	public void searchByLocationException(){
		//assertTrue(eventService!=null);
	}
	
	
	
	
}
