package com.alten.springbootrestdatajpa.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	
	private int empId;
	
	@NotNull(message = "Firstname should not be blank!")
	private String firstName;
	
	@NotNull(message = "Lastname should not be blank!")
	private String lastName;
	
	@NotNull(message = "Salary should not be blank!")
	@DecimalMin(value = "1.0", message = "provide till 2 decimal places")
	@DecimalMax(value = "100000.0", message = "provide till 9 decimal places")
	private BigDecimal salary;
	
	@NotNull(message = "Dept Id should not be blank!")
	private int deptId;
}
