package com.alten.springbootrestdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String firstName;
	private String lastName;
	private float salary;
	private int deptId;
}
