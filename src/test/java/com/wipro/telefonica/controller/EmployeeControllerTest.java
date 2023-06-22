package com.wipro.telefonica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.telefonica.exception.EmployeeAlreadyExistException;
import com.wipro.telefonica.exception.EmployeeDoesntExistException;
import com.wipro.telefonica.exception.InvalidInputsException;
import com.wipro.telefonica.model.Employee;
import com.wipro.telefonica.repository.EmployeeRepository;
import com.wipro.telefonica.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	private EmployeeService employeeService;
	@Mock
	private EmployeeRepository employeeRepository;
	private Employee employee;
	Optional<Employee> optional;
	@BeforeEach
	public void setup() throws Exception{
		MockitoAnnotations.openMocks(this);
//		employee = new Employee(204,"charan","akp","M",LocalDate.now(),LocalDate.now(),"fulltime","IT","Hyd","billable","JavaFullStack");
		employee = new Employee();
		employee.setAddress("vzg");
		employee.setDepartment("developer");
		employee.setDesignation("se");
		employee.setDob(LocalDate.now());
		employee.setDoj(LocalDate.now());
		employee.setEmployeeid(124);
		employee.setJobtype("software");
		employee.setLocation("vzg");
		employee.setName("charan");
		employee.setSex("M");
		employee.setStatus("B");
		optional=Optional.of(employee);
	}
	@AfterEach
	public void tearDown() throws Exception{
		employeeRepository.deleteAll();
	}
	@Test
	public void createEmp() throws JsonProcessingException, Exception {
		Mockito.when(employeeService.createEmp(employee)).thenReturn(employee);
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/employee/createEmp")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	public void createEmpException() throws JsonProcessingException, Exception {
//		Mockito.when(employeeService.createEmp(employee)).thenThrow(EmployeeDoesntExistException.class);
		doThrow(new EmployeeAlreadyExistException()).when(employeeService).createEmp(any());
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/employee/createEmp")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}
	@Test
	public void searchEmployee() throws JsonProcessingException, Exception {
		Mockito.when(employeeService.searchEmp(employee.getEmployeeid())).thenReturn(employee);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/searchEmpWithId/{employeeid}",employee.getEmployeeid())
				.content(objectMapper.writeValueAsString(employee.getEmployeeid()))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	public void searchEmployeeException() throws JsonProcessingException, Exception {
//		Mockito.when(employeeService.searchEmp(employee.getEmployeeid())).thenReturn(employee);
		doThrow(new EmployeeDoesntExistException()).when(employeeService).searchEmp(anyLong());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/searchEmpWithId/{employeeid}",employee.getEmployeeid())
				.content(objectMapper.writeValueAsString(employee.getEmployeeid()))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}
	@Test
	public void deleteEmployee() throws JsonProcessingException, Exception {
		Mockito.when(employeeService.deleteEmp(employee.getEmployeeid())).thenReturn(employee);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/employee/deleteEmpWithId/{employeeid}",employee.getEmployeeid())
				.content(objectMapper.writeValueAsString(employee.getEmployeeid()))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	public void deleteEmployeeException() throws JsonProcessingException, Exception {
//		Mockito.when(employeeService.deleteEmp(employee.getEmployeeid())).thenReturn(employee);
		doThrow(new EmployeeDoesntExistException()).when(employeeService).deleteEmp(anyLong());
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/employee/deleteEmpWithId/{employeeid}",employee.getEmployeeid())
				.content(objectMapper.writeValueAsString(employee.getEmployeeid()))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}
	@Test
	public void updateEmpDetails() throws JsonProcessingException, Exception {
		Mockito.when(employeeService.updateEmp(employee)).thenReturn(employee);
		this.mockMvc.perform(MockMvcRequestBuilders.put("/employee/updateEmpDetails")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	
	}
	@Test
	public void updateEmpDetailsException() throws JsonProcessingException, Exception {
//		Mockito.when(employeeService.updateEmp(employee)).thenReturn(employee);
		doThrow(new EmployeeDoesntExistException()).when(employeeService).updateEmp(any());
		this.mockMvc.perform(MockMvcRequestBuilders.put("/employee/updateEmpDetails")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	
	}
}
