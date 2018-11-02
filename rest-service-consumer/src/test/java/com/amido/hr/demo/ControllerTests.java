package com.amido.hr.demo;

import javax.validation.constraints.AssertTrue;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.assertj.AssertableReactiveWebApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.*;

import com.amido.hr.demo.controller.QuoteController;
import com.amido.hr.demo.entities.*;
import com.amido.hr.demo.exception.QuoteValidationException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTests {

	@Autowired
	QuoteController quoteController;
	
	@Test
	public void randomQuoteNotNull(){
		assertTrue(quoteController.getRandomQuote()!=null);
	}
	
	@Test
	public void randomQuoteUpdatedByNotNull(){
		assertTrue(quoteController.getRandomQuote().getBody().getValue().getUpdatedBy()!=null);
		assertTrue(quoteController.getRandomQuote().getBody().getValue().getUpdatedBy().equals("MF"));
	}
	
	@Test
	public void quoteByIdUpdated(){
		assertTrue(quoteController.getQuoteById(2L).getBody().getValue().getUpdatedBy().equals("MF"));
		assertTrue(quoteController.getRandomQuote().getBody().getValue().getUpdatedBy().equals("MF"));
	}
	
	@Test
	public void quoteByIdUpdatedWithCustomValue(){
		assertTrue(quoteController.getQuoteById(2L, "pino").getBody().getValue().getUpdatedBy().equals("pino"));
	}
	
	@Test(expected = QuoteValidationException.class)
	public void addQuoteExisting(){
		Quote q = new Quote();
		com.amido.hr.demo.entities.Value v = new com.amido.hr.demo.entities.Value();
		v.setId(1L);
		v.setUpdatedBy("MF");
		v.setQuote("test quote with existing id");
		q.setType("no");
		q.setValue(v);
	
		quoteController.addNewQuote(q);
	}
	
	@Test(expected = QuoteValidationException.class)
	public void addQuoteNoId(){
		Quote q = new Quote();
		com.amido.hr.demo.entities.Value v = new com.amido.hr.demo.entities.Value();
		v.setUpdatedBy("MF");
		v.setQuote("test quote with no id");
		q.setType("no");
		q.setValue(v);
	
		quoteController.addNewQuote(q);
	}

	@Test
	public void addQuoteNotExisting(){
		Quote q = new Quote();
		com.amido.hr.demo.entities.Value v = new com.amido.hr.demo.entities.Value();
		v.setId(1000L);
		v.setUpdatedBy("MF");
		v.setQuote("test quote with non existing id");
		q.setType("no");
		q.setValue(v);
	
		ResponseEntity<Quote> resp = quoteController.addNewQuote(q);
		assertEquals(resp.getStatusCode(), HttpStatus.CREATED);
	}

	public QuoteController getQuoteController() {
		return quoteController;
	}

	public void setQuoteController(QuoteController quoteController) {
		this.quoteController = quoteController;
	}
	
}
