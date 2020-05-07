package com.capgemini.librarymanagementsystem_spring.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.librarymanagementsystem_spring.dto.LibraryResponse;
import com.capgemini.librarymanagementsystem_spring.exception.LMSException;

@RestControllerAdvice
public class MyRestcontrollerAdvice {
	@ExceptionHandler
	public LibraryResponse myExceptionHandler(LMSException lmsException) {
		LibraryResponse response = new LibraryResponse();
		response.setError(true);
		response.setMessage(lmsException.getMessage());
		return response;
	}
}
