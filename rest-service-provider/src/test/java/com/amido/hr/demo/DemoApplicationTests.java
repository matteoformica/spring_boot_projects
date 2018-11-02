package com.amido.hr.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.amido.hr.demo.controller.EmployeeController;
import com.amido.hr.demo.entity.Employee;
import com.amido.hr.demo.repository.EmployeeRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class DemoApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
    
	@MockBean 
	EmployeeRepository repo;
	
	@Test
	public void testEmployeesNotEmptyResults() throws Exception {
		Employee alex = new Employee("ID1");
		List<Employee> allEmployees = Arrays.asList(alex);
	    
		when(repo.findAll()).thenReturn(allEmployees);
		
		mockMvc.perform(get("/employees")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().json("[{\"empId\":\"ID1\"}]"))
			      .andExpect(jsonPath("$.empId").value("ID1"))
                  .andDo(print());
	}

	@Test
	public void testEmployeesEmptyResult() throws Exception {
	
		when(repo.findAll()).thenReturn(null);
		
		mockMvc.perform(get("/employees")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNoContent())
			      .andExpect(jsonPath("$").doesNotExist())
                  .andDo(print());
	}

	@Test
	public void testSingleEmployeeExisting() throws Exception {
		Employee emp = new Employee("first");
		
		when(repo.findById("first")).thenReturn(emp);
		
		mockMvc.perform(get("/employees/first")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().json("{\"empId\":\"first\"}"))
			      .andExpect(jsonPath("$.empId").value("first"))
                  .andDo(print());
	}

	@Test
	public void testSingleEmployeeNotExisting() throws Exception {
		
		when(repo.findById("first")).thenReturn(null);
		
		mockMvc.perform(get("/employees/ID3")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNoContent())
			      .andExpect(jsonPath("$").doesNotExist())
                  .andDo(print());
	}

//	@Test
//	public void testEmployeesByDeptEmptyOrNull() throws Exception {
//		mockMvc.perform(get("/d2/employees")
//			      .contentType(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(view().name("index"))
//                  .andDo(print());
//	}
}
