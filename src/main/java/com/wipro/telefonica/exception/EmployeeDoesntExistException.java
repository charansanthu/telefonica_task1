package com.wipro.telefonica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="employee doesnt exist")
public class EmployeeDoesntExistException extends Exception{

}
