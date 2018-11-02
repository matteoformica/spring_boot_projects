package com.amido.hr.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amido.hr.demo.entities.Quote;
import com.amido.hr.demo.exception.QuoteNotFoundException;
import com.amido.hr.demo.exception.QuoteValidationException;
import com.amido.hr.demo.service.QuoteService;

@RestController
@RequestMapping(path="/myquote")
public class QuoteController {
	
	@Autowired
	private QuoteService quoteService;
	
	public QuoteService getQuoteService() {
		return quoteService;
	}

	public void setQuoteService(QuoteService quoteService) {
		this.quoteService = quoteService;
	}

	@RequestMapping(path="/random", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Quote> getRandomQuote(){
		Quote quote = quoteService.getRandomQuote();
		quote.getValue().setUpdatedBy("MF");
		
		return ResponseEntity.status(HttpStatus.OK)
		        .body(quote);
	}
	
	@RequestMapping(path="/random/{reader}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Quote> getRandomQuote( @PathVariable String reader){
		Quote quote = quoteService.getRandomQuote();
		quote.getValue().setUpdatedBy(reader);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(quote);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Quote> getQuoteById(@PathVariable Long id){
		Quote quote = quoteService.getQuoteById(id);
		
		if(!quote.getType().equalsIgnoreCase("success")){
			throw new QuoteNotFoundException("The quote has not been found: "+id);
		}
		
		quote.getValue().setUpdatedBy("MF");
		return ResponseEntity.status(HttpStatus.OK)
		        .body(quote);
	}
	
	@RequestMapping(path="/{id}/{reader}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Quote> getQuoteById(@PathVariable Long id, @PathVariable String reader){
		Quote quote = quoteService.getQuoteById(id);
		
		if(!quote.getType().equalsIgnoreCase("success")){
			throw new QuoteNotFoundException("The quote has not been found: "+id);
		}
		
		quote.getValue().setUpdatedBy(reader);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(quote);
	}
	
	@RequestMapping(path="/addQuote", method = RequestMethod.POST)
	public ResponseEntity<Quote> addNewQuote(@RequestBody Quote newQuote){
		
		if(newQuote.getValue()==null || newQuote.getValue().getId()==null){
			throw new QuoteValidationException("The quote has no ID!");
		}
		
		Quote quote = quoteService.getQuoteById(newQuote.getValue().getId());
		
		if(quote!=null && quote.getType().equalsIgnoreCase("success")){
			throw new QuoteValidationException("The quote with this ID already esists: "+quote.getValue().getId());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED)
		        .body(newQuote);
	}

	
}
