package com.wipro.telefonica.service;

import com.wipro.telefonica.exception.EmployeeAlreadyExistException;
import com.wipro.telefonica.exception.EmployeeDoesntExistException;
import com.wipro.telefonica.model.Employee;

public interface EmployeeService {
	public Employee createEmp(Employee employee) throws EmployeeAlreadyExistException;
	public Employee searchEmp(long id) throws EmployeeDoesntExistException;
	public Employee deleteEmp(long id) throws EmployeeDoesntExistException;
	public Employee updateEmp(Employee employee) throws EmployeeDoesntExistException;
}
