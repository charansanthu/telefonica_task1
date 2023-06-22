package com.wipro.telefonica.controller;

import com.wipro.telefonica.exception.EmployeeAlreadyExistException;
import com.wipro.telefonica.exception.EmployeeDoesntExistException;
import com.wipro.telefonica.exception.InvalidInputsException;
import com.wipro.telefonica.model.Employee;
import com.wipro.telefonica.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/employee",produces = "application/xml")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	private ResponseEntity responseEntity;

	@GetMapping("/get")
    public ResponseEntity<List<Employee>> get() throws EmployeeDoesntExistException
    {
		try {
        List<Employee> model = employeeService.getallemployees();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<List<Employee>>(model, headers, HttpStatus.OK);
		}catch(EmployeeDoesntExistException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
    }
	@PostMapping("/createEmp")
	public ResponseEntity<?> createEmp(@RequestBody Employee employee) throws EmployeeAlreadyExistException{
		try {
			Employee employee2=employeeService.createEmp(employee);
			responseEntity = new ResponseEntity<Employee>(employee2, HttpStatus.OK);
		}catch(EmployeeAlreadyExistException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}catch(InvalidInputsException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	@GetMapping("/searchEmpWithId/{employeeid}")
	public ResponseEntity<?> searchEmployee(@PathVariable long  employeeid) throws EmployeeDoesntExistException{
		try {
			Employee employee=employeeService.searchEmp(employeeid);
			responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}catch(EmployeeDoesntExistException e) {
			responseEntity = new ResponseEntity<>("Employee Doesn't exist", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	@DeleteMapping("/deleteEmpWithId/{employeeid}")
	public ResponseEntity<?> deleteEmployee(@PathVariable long employeeid) throws EmployeeDoesntExistException{
		try {
			Employee employee=employeeService.deleteEmp(employeeid);
			responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}catch(EmployeeDoesntExistException e) {
			responseEntity = new ResponseEntity<>("Employee Doesn't exist", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	@PutMapping("/updateEmpDetails")
	public ResponseEntity<?> updateEmpDetails(@RequestBody Employee employee) throws EmployeeDoesntExistException, InvalidInputsException{
		try {
			Employee employee1=employeeService.updateEmp(employee);
			responseEntity = new ResponseEntity<Employee>(employee1, HttpStatus.OK);
		}catch(EmployeeDoesntExistException e) {
			responseEntity = new ResponseEntity<>("Employee Doesn't exist", HttpStatus.CONFLICT);
		}catch(InvalidInputsException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
}
