package com.alten.springbootrestdatajpa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
		
		if(employees.size() == 0)
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.NOT_FOUND);
	
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			return new ResponseEntity<Employee>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee entity,UriComponentsBuilder uriComponentsBuilder,HttpServletRequest request) {
	
		Employee newEmployee = employeeRepository.save(entity);
		UriComponents uriComponents = uriComponentsBuilder
				.path(request.getRequestURI()+"/{id}")
				.buildAndExpand(newEmployee.getEmpId());
		
		
	  return ResponseEntity.created(uriComponents.toUri()).body(newEmployee);
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<Employee> updateProduct(@PathVariable("id") Integer id,
//			@Valid @RequestBody Product product ) throws ResourceNotFoundException {
//		Employee product2 = productService.getProductById(id)
//				.orElseThrow(()-> new ResourceNotFoundException("Product not found"));
//		product.setProductId(id);
//		Employee product3 =productService.createProduct(product);
//		
//		return ResponseEntity.ok(product3);
//	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Boolean>> removeEmployeeById(@PathVariable int id) {
		Map<String, Boolean> response = new HashMap();
		response.put("status",true);
		employeeRepository.deleteById(id);
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}
	
}

