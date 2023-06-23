package com.wipro.telefonica.validations;

import java.util.regex.Pattern;

import com.wipro.telefonica.model.Employee;

public class EmployeeValidations {
	public String validations(Employee employee) {
		Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		if(employee.getEmployeeid()==0 || Long.valueOf(employee.getEmployeeid())==null) {
			return "Invalid id";
		}else if((employee.getName().length()<30&&employee.getName().length()>6
				&&!Pattern.matches("[a-z A-Z]+", employee.getName()))
				||special.matcher(employee.getName()).find()) {
			return "Invalid name";
		}else if((employee.getAddress().length()>50&&employee.getAddress().length()<6)
				||special.matcher(employee.getAddress()).find()) {
			return "Invalid address";
		}else if((!employee.getSex().equals("Male")&&!employee.getSex().equals("Female")&&!employee.getSex().equals("Others"))
			||special.matcher(employee.getSex()).find()) {
			return "Invalid gender";
		}else if((!Pattern.matches("[a-z A-Z]+", employee.getJobtype()))
				||special.matcher(employee.getJobtype()).find()) {
			return "Invalid Jobtype";
		}else if((!Pattern.matches("[a-z A-Z]+", employee.getDepartment()))
				||special.matcher(employee.getDepartment()).find()) {
			return "Invalid department";
		}else if((!Pattern.matches("[a-z A-Z]+", employee.getLocation()))
			||special.matcher(employee.getLocation()).find()) {
			return "Invalid location";
		}else if((!Pattern.matches("[a-z A-Z]+", employee.getStatus()))
			||special.matcher(employee.getStatus()).find()) {
			return "Invalid status";
		}else if((!Pattern.matches("[a-z A-Z]+", employee.getDesignation()))
				||special.matcher(employee.getDesignation()).find()) {
			return "Invalid designation";
		}
		return "1";
	}
}
