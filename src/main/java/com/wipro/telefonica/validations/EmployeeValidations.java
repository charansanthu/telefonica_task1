package com.wipro.telefonica.validations;

import java.util.regex.Pattern;

import com.wipro.telefonica.model.Employee;

public class EmployeeValidations {
	public int validations(Employee employee) {
		if(employee.getEmployeeid()==0 || Long.valueOf(employee.getEmployeeid())==null) {
			return 0;
		}else if(employee.getName().length()<30&&employee.getName().length()>6
				&&Pattern.matches("[a-zA-Z]+", employee.getName())) {
			return 0;
		}else if(employee.getAddress().length()<50&&employee.getName().length()>6) {
			return 0;
		}else if(!employee.getSex().equals("Male")||!employee.getSex().equals("Female")||!employee.getSex().equals("Others")) {
			return 0;
		}else if(Pattern.matches("[a-zA-Z]+", employee.getJobtype())) {
			return 0;
		}else if(Pattern.matches("[a-zA-Z]+", employee.getDepartment())) {
			return 0;
		}else if(Pattern.matches("[a-zA-Z]+", employee.getLocation())) {
			return 0;
		}else if(Pattern.matches("[a-zA-Z]+", employee.getStatus())) {
			return 0;
		}else if(Pattern.matches("[a-zA-Z]+", employee.getDesignation())) {
			return 0;
		}
		return 1;
	}
}
