package com.alten.springbootrestdatajpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.springbootrestdatajpa.model.Employee;
import com.alten.springbootrestdatajpa.repository.EmployeeRepository;

@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployee(){
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(e-> employees.add(e));
		
		System.out.println(employees);
		
		if(employees.size() == 0)
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.NOT_FOUND);
	
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
}

