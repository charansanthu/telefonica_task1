package com.wipro.telefonica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.telefonica.exception.EmployeeDoesntExistException;
import com.wipro.telefonica.model.Employee;
import com.wipro.telefonica.repository.EmployeeRepository;
import com.wipro.telefonica.service.EmployeeService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@InjectMocks
	private EmployeeController controller;
	@Mock
	private EmployeeRepository employeeRepository;
	@MockBean
	private EmployeeService employeeService;
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
	public void createEmp() throws JsonProcessingException, Exception {
		Mockito.when(employeeService.createEmp(employee)).thenReturn(employee);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/employee/createEmp")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
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
	public void deleteEmployee() throws JsonProcessingException, Exception {
		Mockito.when(employeeService.deleteEmp(employee.getEmployeeid())).thenReturn(employee);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/employee/deleteEmpWithId/{employeeid}",employee.getEmployeeid())
				.content(objectMapper.writeValueAsString(employee.getEmployeeid()))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	public void updateEmpDetails() throws JsonProcessingException, Exception {
		Mockito.when(employeeService.updateEmp(employee)).thenReturn(employee);
		this.mockMvc.perform(MockMvcRequestBuilders.put("/employee/updateEmpDetails")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	
	}
//	@Test
	public void createEmpException() throws EmployeeDoesntExistException, Exception {
//		Mockito.when(employeeService.createEmp(any())).thenThrow(EmployeeDoesntExistException.class);
//		doThrow(new EmployeeDoesntExistException()).when(employeeService).createEmp(employee);
		doThrow(new EmployeeDoesntExistException()).when(employeeService).createEmp(any());
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/employee/createEmp")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}
}
