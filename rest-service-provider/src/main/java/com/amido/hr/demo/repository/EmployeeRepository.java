package com.amido.hr.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.amido.hr.demo.entity.Employee;

@Repository
public class EmployeeRepository {

	public List<Employee> findAll() {
		List<Employee> employeesList = new ArrayList<Employee>();

		Employee e1 = new Employee("1");
		Employee e2 = new Employee("2");
		Employee e3 = new Employee("3");
		employeesList.add(e1);
		employeesList.add(e2);
		employeesList.add(e3);
		return employeesList;
	}
	
	public Employee findById(String id) {
		Employee e1 = new Employee("1");
		return e1;
	}
	
	
	

}
