package com.amido.hr.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amido.hr.demo.entity.Employee;
import com.amido.hr.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@RequestMapping(path="/employees", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> emps = empRepo.findAll();
		
		if(emps == null || emps.size() == 0){
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
			        .body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK)
		        .body(emps);
		
	}

	
	@RequestMapping(path="/employees/{empId}" , method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String empId){
		Employee emp = empRepo.findById(empId);
		
		if(emp == null){
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
			        .body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK)
		        .body(emp);
	}

	
	//	
//	@RequestMapping(path="/{depId}/employees" , method=RequestMethod.GET)
//	@ResponseBody
//	public Employee getEmployeeByDepartment(String depId){
//		throw new UnsupportedOperationException("Method not implemented");
//	}
//	
//	@RequestMapping(path="/employee", method=RequestMethod.POST)
//	public Employee createEmployeeByDepartment(Employee emp){
//		throw new UnsupportedOperationException("Method not implemented");
//	}
//	
//	@RequestMapping(path="/employee/{id}", method=RequestMethod.DELETE)
//	public Employee createEmployeeByDepartment(String empId){
//		return null;
//	}
	public EmployeeRepository getEmpRepo() {
		return empRepo;
	}

	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}
	
}
