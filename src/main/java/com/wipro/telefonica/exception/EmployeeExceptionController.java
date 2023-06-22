package com.wipro.telefonica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EmployeeExceptionController {
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	@ExceptionHandler(EmployeeAlreadyExistException.class)
	public ExceptionResponse handleEmployeeAlreadyExistException(HttpServletRequest httpServletRequest,EmployeeAlreadyExistException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setUrl(httpServletRequest.getRequestURI());
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	@ExceptionHandler(EmployeeDoesntExistException.class)
	public ExceptionResponse handleEmployeeDoesntExistException(HttpServletRequest httpServletRequest,EmployeeDoesntExistException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setUrl(httpServletRequest.getRequestURI());
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	@ExceptionHandler(InvalidInputsException.class)
	public ExceptionResponse handleInvalidInputsException(HttpServletRequest httpServletRequest,InvalidInputsException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setUrl(httpServletRequest.getRequestURI());
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}
	@ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> handleException(JsonParseException ex) {
//        LOG.error("Error parse json " +  ex.getMessage());
		ResponseEntity responseEntity = new ResponseEntity<>("invalid parameters", HttpStatus.CONFLICT);
		return responseEntity;
    }
}
