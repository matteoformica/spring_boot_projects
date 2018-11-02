package com.amido.hr.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amido.hr.demo.entities.ErrorDetails;

@ControllerAdvice
@RestController
public class QuoteExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleNoHandlerFoundException(ex, headers, status, request);
	}

	@ExceptionHandler(QuoteNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleQuoteNotFoundException(QuoteNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		errorDetails.setHttpStatus(HttpStatus.NOT_FOUND);
		errorDetails.setType("error");
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(QuoteValidationException.class)
	public final ResponseEntity<ErrorDetails> handleQuoteValidationException(QuoteValidationException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		errorDetails.setHttpStatus(HttpStatus.NOT_FOUND);
		errorDetails.setType("error");
		return new ResponseEntity<>(errorDetails, HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	      request.getDescription(false));
	  errorDetails.setHttpStatus(HttpStatus.BAD_REQUEST);
	  errorDetails.setType("error");
	  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
