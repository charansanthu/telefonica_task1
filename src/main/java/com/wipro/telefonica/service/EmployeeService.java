package com.wipro.telefonica.service;

import java.util.List;

import com.wipro.telefonica.exception.EmployeeAlreadyExistException;
import com.wipro.telefonica.exception.EmployeeDoesntExistException;
import com.wipro.telefonica.exception.InvalidInputsException;
import com.wipro.telefonica.model.Employee;

public interface EmployeeService {
	public Employee createEmp(Employee employee) throws EmployeeAlreadyExistException,InvalidInputsException;
	public Employee searchEmp(long id) throws EmployeeDoesntExistException;
	public Employee deleteEmp(long id) throws EmployeeDoesntExistException;
	public Employee updateEmp(Employee employee) throws EmployeeDoesntExistException, InvalidInputsException;
	public List<Employee> getallemployees() throws EmployeeDoesntExistException;
}
