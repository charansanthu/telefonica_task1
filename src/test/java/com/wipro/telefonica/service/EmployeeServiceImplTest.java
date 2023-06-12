 package com.wipro.telefonica.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import  static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import com.wipro.telefonica.exception.EmployeeAlreadyExistException;
import com.wipro.telefonica.exception.EmployeeDoesntExistException;
import com.wipro.telefonica.model.Employee;
import com.wipro.telefonica.repository.EmployeeRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	private Employee employee;
	Optional<Employee> optional;
	@BeforeEach
	public void setup() throws Exception{
		MockitoAnnotations.openMocks(this);
		employee = new Employee(204,"charan","akp","M",LocalDate.now(),LocalDate.now(),"fulltime","IT","Hyd","billable","JavaFullStack");
		optional=Optional.of(employee);
	}
	@AfterEach
	public void tearDown() throws Exception{
		employeeRepository.deleteAll();
	}
	@Test
	public void createEmpException() {
		Mockito.when(employeeRepository.findById(anyLong())).thenReturn(optional);
		assertThrows(EmployeeAlreadyExistException.class, ()->employeeServiceImpl.createEmp(employee));
	}
	@Test
	public void createEmp() throws EmployeeAlreadyExistException {
//		Mockito.when(employeeRepository.findById(anyLong())).thenReturn(null);
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(true, employeeServiceImpl.createEmp(employee)!=null);
	}
	@Test
	public void searchEmp() throws EmployeeDoesntExistException {
		Mockito.when(employeeRepository.findById(anyLong())).thenReturn(optional);
		assertEquals(employee.getName(), employeeServiceImpl.searchEmp(employee.getEmployeeid()).getName());
	}
	@Test
	public void searchEmpException() {
		assertThrows(EmployeeDoesntExistException.class,()-> employeeServiceImpl.searchEmp(employee.getEmployeeid()));
	}
	@Test
	public void deleteEmp() throws EmployeeDoesntExistException {
		Mockito.when(employeeRepository.findById(anyLong())).thenReturn(optional);
		assertEquals(employee.getName(), employeeServiceImpl.deleteEmp(employee.getEmployeeid()).getName());
	}
	@Test
	public void deleteEmpException() {
		assertThrows(EmployeeDoesntExistException.class,()-> employeeServiceImpl.deleteEmp(employee.getEmployeeid()));
	}
	@Test
	public void updateEmp() throws EmployeeDoesntExistException {
		Mockito.when(employeeRepository.findById(anyLong())).thenReturn(optional);
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee.getName(), employeeServiceImpl.updateEmp(employee).getName());
	}
	@Test
	public void updateEmpException() {
		assertThrows(EmployeeDoesntExistException.class,()-> employeeServiceImpl.updateEmp(employee));
	}
}
