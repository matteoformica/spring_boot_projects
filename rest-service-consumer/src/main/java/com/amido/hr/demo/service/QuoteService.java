package com.amido.hr.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amido.hr.demo.entities.Quote;

@Service
public class QuoteService {
	
	@Value( "${quote.service.baseurl}" )
	private String quoteServiceBaseUrl;
	
	private final RestTemplate restTemplate;
	
	public QuoteService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public Quote getRandomQuote(){
		Quote randomQuote = this.restTemplate.getForObject(quoteServiceBaseUrl+"/random", Quote.class);	
		return randomQuote;
	}
	
	public Quote getQuoteById(Long quoteId){	
		Quote quote = this.restTemplate.getForObject(quoteServiceBaseUrl+"/{quoteId}", Quote.class, quoteId);
		return quote;
	}
	
}
