package com.amido.hr.demo;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amido.hr.demo.service.QuoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {
	
	@Autowired
	QuoteService quoteService;
	
	@Test
	public void randomQuoteNotNull(){
		assertTrue(quoteService.getRandomQuote()!=null);
	}
	
	@Test
	public void randomQuoteTypeisSuccess(){
		assertTrue(quoteService.getRandomQuote().getType().equals("success"));
	}

	@Test
	public void randomQuoteValueisNotEmpty(){
		assertTrue(quoteService.getRandomQuote().getValue()!=null);
	}

	@Test
	public void randomQuoteIdisPositiveInt(){
		assertTrue(quoteService.getRandomQuote().getValue().getId()>0);
	}
	
	@Test
	public void quoteIdisPositiveInt(){
		assertTrue(quoteService.getQuoteById(2L).getValue().getId().equals(2L));
	}
	
	@Test
	public void quoteIdisSuccess(){
		assertTrue(quoteService.getQuoteById(2L).getType().equals("success"));
	}
	
	@Test
	public void quoteIdNotExisitng(){
		assertTrue(quoteService.getQuoteById(123123123L).getValue().getId() == null);
		assertTrue(quoteService.getQuoteById(123123123L).getValue().getQuote().equals("None"));
		assertTrue(quoteService.getQuoteById(123123123L).getType().contains("not exist"));
	}
	
	
}
