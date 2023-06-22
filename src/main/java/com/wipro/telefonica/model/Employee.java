package com.wipro.telefonica.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {
	@Id
	private long employeeid;
	private String name;
	private String address;
	private String sex;
	private LocalDate dob = LocalDate.now();
	private LocalDate doj = LocalDate.now();
	private String jobtype;
	private String department;
	private String location;
	private String status;
	private String designation;
	public long getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(long employeeid) {
		this.employeeid = employeeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Employee(long employeeid, String name, String address, String sex, LocalDate dob, LocalDate doj,
			String jobtype, String department, String location, String status, String designation) {
		super();
		this.employeeid = employeeid;
		this.name = name;
		this.address = address;
		this.sex = sex;
		this.dob = dob;
		this.doj = doj;
		this.jobtype = jobtype;
		this.department = department;
		this.location = location;
		this.status = status;
		this.designation = designation;
	}
	public Employee() {
		super();
		//TODO Auto-generated constructor stub
	}
	
}
