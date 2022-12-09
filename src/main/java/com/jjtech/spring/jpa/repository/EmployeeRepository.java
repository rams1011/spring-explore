package com.jjtech.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jjtech.spring.jpa.model.Employee;

@SuppressWarnings("unused")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	@Query(value = "SELECT set_config('rls.tenant_id',:tenantId, false)")
	 public void setConfigTenant(String tenantId);

}
