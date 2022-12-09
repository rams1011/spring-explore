package com.jjtech.spring.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeRepositoryTest {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	void testFindAll() {
		employeeRepository.count();
	}

}
