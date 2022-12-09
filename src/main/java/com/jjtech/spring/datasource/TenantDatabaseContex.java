package com.jjtech.spring.datasource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantDatabaseContex {
	
	String clientGroup;
	
	String tenantId;

}
