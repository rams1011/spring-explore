package com.jjtech.spring.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {

	
	@Id
	@Column(name = "EMP_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "EMP_NAME")
	private String empName;

	@Column(name = "EMP_AGE")
	private Integer age;

	@Column(name = "EMP_LEVEL")
	private Integer level;
	
	@Column(name = "TENANT_ID")
	private String tenandId;
	
	
}
