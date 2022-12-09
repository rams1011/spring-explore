package com.jjtech.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjtech.spring.jpa.model.Employee;
import com.jjtech.spring.jpa.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> employees = new ArrayList<Employee>();
			log.info("All Employess"+ employeeRepository.count());
			employees = employeeRepository.findAll();
			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			log.info("Employee"+ employees.get(0).toString());
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/employees/{id}")
	@Transactional
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
		try {
			Optional<Employee> optional = employeeRepository.findById(id);
			if(optional.isPresent()) {
				Employee employee = employeeRepository.findById(id).get();
				employee.setAge(41);
				return new ResponseEntity<>(employee, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/employees/ro/{id}")
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public ResponseEntity<Employee> getEmployeeReadOnly(@PathVariable("id") long id) {
		try {
			Optional<Employee> optional = employeeRepository.findById(id);
			if(optional.isPresent()) {
				Employee employee = employeeRepository.findById(id).get();
				employee.setAge(39);
				return new ResponseEntity<>(employee, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
