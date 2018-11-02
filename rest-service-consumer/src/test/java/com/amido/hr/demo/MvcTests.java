package com.amido.hr.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.amido.hr.demo.controller.QuoteController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MvcTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	QuoteController quoteController;

	@Test
	public void testRandomQuoteWithReader() throws Exception {
		mockMvc.perform(get("/myquote/random/PIPPO")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.type").value("success"))
			      .andExpect(jsonPath("$.value.updatedBy").value("PIPPO"))
                  .andDo(print());
	}
	
	@Test
	public void testRandomQuoteWithoutReader() throws Exception {	
		mockMvc.perform(get("/myquote/random")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.type").value("success"))
			      .andExpect(jsonPath("$.value.updatedBy").value("MF"))
                  .andDo(print());
	}
	
	@Test
	public void testQuoteByIdWithoutReader() throws Exception {	
		mockMvc.perform(get("/myquote/2/BELLO")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.type").value("success"))
			      .andExpect(jsonPath("$.value.id").value(2))
			      .andExpect(jsonPath("$.value.updatedBy").value("BELLO"))
                  .andDo(print());
	}
	
	@Test
	public void testQuoteByIdWithReader() throws Exception {	
		mockMvc.perform(get("/myquote/2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.type").value("success"))
			      .andExpect(jsonPath("$.value.id").value(2))
			      .andExpect(jsonPath("$.value.updatedBy").value("MF"))
                  .andDo(print());
	}
	
	@Test
	public void testQuoteByIdNonExistent() throws Exception {	
		mockMvc.perform(get("/myquote/123123")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(jsonPath("$.type").value("error"))
			      .andExpect(jsonPath("$.value.quote").doesNotExist())
                  .andDo(print());
	}
	
	@Test
	public void testAddNewQuote() throws Exception {	
		mockMvc.perform(post("/myquote/addQuote")
				  .content("{\"type\": \"success\",\"value\": {\"id\": 123,\"quote\": \"Working with Spring Boot is like pair-programming with the Spring developers.\",\"updatedBy\": \"MF\"}}\")")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated())
			      .andExpect(jsonPath("$.type").value("success"))
			      .andExpect(jsonPath("$.value.id").value(123))
			      .andExpect(jsonPath("$.value.updatedBy").value("MF"))
                  .andDo(print());
	}
	
}
