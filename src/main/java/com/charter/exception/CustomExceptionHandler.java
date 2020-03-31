package com.charter.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler  {
	
	@Override
	protected ResponseEntity<Object>  handleMethodArgumentNotValid(MethodArgumentNotValidException e ,
			HttpHeaders headers, HttpStatus status, WebRequest request)  {
		
		 List<String> details = new ArrayList<>();
	        for(ObjectError error : e.getBindingResult().getAllErrors()) {
	           details.add(error.getDefaultMessage());
	        }
	        ValidationErrorResponse error = new ValidationErrorResponse(details);
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
