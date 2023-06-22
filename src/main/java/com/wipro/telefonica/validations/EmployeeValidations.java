package com.wipro.telefonica.validations;

import java.util.regex.Pattern;

import com.wipro.telefonica.model.Employee;

public class EmployeeValidations {
	public String validations(Employee employee) {
		if(employee.getEmployeeid()==0 || Long.valueOf(employee.getEmployeeid())==null) {
			return "Invalid id";
		}else if(employee.getName().length()<30&&employee.getName().length()>6
				&&!Pattern.matches("[a-z A-Z]+", employee.getName())) {
			return "Invalid name";
		}else if(employee.getAddress().length()>50&&employee.getName().length()<6) {
			return "Invalid address";
		}else if(!employee.getSex().equals("Male")&&!employee.getSex().equals("Female")&&!employee.getSex().equals("Others")) {
			return "Invalid gender";
		}else if(!Pattern.matches("[a-z A-Z]+", employee.getJobtype())) {
			return "Invalid Jobtype";
		}else if(!Pattern.matches("[a-z A-Z]+", employee.getDepartment())) {
			return "Invalid department";
		}else if(!Pattern.matches("[a-z A-Z]+", employee.getLocation())) {
			return "Invalid location";
		}else if(!Pattern.matches("[a-z A-Z]+", employee.getStatus())) {
			return "Invalid status";
		}else if(!Pattern.matches("[a-z A-Z]+", employee.getDesignation())) {
			return "Invalid designation";
		}
		return "1";
	}
}
