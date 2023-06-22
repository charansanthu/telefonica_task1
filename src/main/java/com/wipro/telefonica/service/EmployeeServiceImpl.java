package com.wipro.telefonica.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.telefonica.exception.EmployeeAlreadyExistException;
import com.wipro.telefonica.exception.EmployeeDoesntExistException;
import com.wipro.telefonica.exception.InvalidInputsException;
import com.wipro.telefonica.model.Employee;
import com.wipro.telefonica.repository.EmployeeRepository;
import com.wipro.telefonica.validations.EmployeeValidations;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	private EmployeeValidations employeeValidations;
	@Override
	public Employee createEmp(Employee employee) throws EmployeeAlreadyExistException, InvalidInputsException {
		Optional<Employee> optional=employeeRepository.findById(employee.getEmployeeid());
		if(!optional.isEmpty()) {
			throw new EmployeeAlreadyExistException("Employee Already Exist");
		}if(employeeValidations.validations(employee)==0) {
			throw new InvalidInputsException("Invalid parameters");
		}
		return employeeRepository.save(employee);
	}
	@Override
	public Employee searchEmp(long id) throws EmployeeDoesntExistException {
		Optional<Employee> optional=employeeRepository.findById(id);
		if(!optional.isEmpty()) {
			return optional.get();
		}
		throw new EmployeeDoesntExistException();
	}
	@Override
	public Employee deleteEmp(long id) throws EmployeeDoesntExistException {
		Optional<Employee> optional=employeeRepository.findById(id);
		if(!optional.isEmpty()) {
			employeeRepository.delete(optional.get());
			return optional.get();
		}
		throw new EmployeeDoesntExistException();
	}
	@Override
	public Employee updateEmp(Employee employee) throws EmployeeDoesntExistException, InvalidInputsException {
		Optional<Employee> optional=employeeRepository.findById(employee.getEmployeeid());
		if(employeeValidations.validations(employee)==0) {
			throw new InvalidInputsException("Invalid parameters");
		}
		if(!optional.isEmpty()) {
			Employee e =optional.get();
			e.setAddress(employee.getAddress());
			e.setDepartment(employee.getDepartment());
			e.setDesignation(employee.getDesignation());
			e.setDob(employee.getDob());
			e.setJobtype(employee.getJobtype());
			e.setLocation(employee.getLocation());
			e.setName(employee.getName());
			e.setSex(employee.getSex());
			e.setStatus(employee.getStatus());
			return employeeRepository.save(e);
		}
		throw new EmployeeDoesntExistException();
	}

}
