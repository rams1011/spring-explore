package com.jjtech.spring.config;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jjtech.spring.datasource.TenantAwareHikariDataSource;
import com.jjtech.spring.datasource.TenantDataSourceRouter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Configuration
@Slf4j
public class DatabaseConfiguration {
	
	List<String> clientGroups = Arrays.asList("clientgroup1","clientgroup2","clientgroup3");

	@Bean
	public DataSource clientDataSource() {
		Map<Object, Object> targetDataSources = new HashMap<>();
		TenantDataSourceRouter clientRoutingDatasource = new TenantDataSourceRouter();
		targetDataSources = clientGroups.stream().collect(Collectors.toMap(clientGroup->clientGroup, clientGroup->getProxyDataSource(clientGroup)));
		clientRoutingDatasource.setTargetDataSources(targetDataSources);
		clientRoutingDatasource.setDefaultTargetDataSource(targetDataSources.get("clientgroup1"));
		return clientRoutingDatasource;
	}

	
	private DataSource getProxyDataSource(String clientGroup) {
		return ProxyDataSourceBuilder.create(getHikariDataSource(clientGroup)).logQueryBySlf4j(SLF4JLogLevel.INFO).build();
	}
	
	private HikariDataSource getHikariDataSource(String clientGroup) {
		return new TenantAwareHikariDataSource(getHikariConfig(clientGroup));
	}
	
	private HikariConfig getHikariConfig(String clientGroup) {
		log.info("clientGroup ############# {}",clientGroup);
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/client");
		hikariConfig.setUsername("api_user");
		hikariConfig.setPassword("apiuser");
		hikariConfig.setSchema(clientGroup);
		hikariConfig.setDriverClassName("org.postgresql.Driver");
		hikariConfig.setPoolName(clientGroup);
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setConnectionTimeout(Duration.ofSeconds(30).toMillis());
		hikariConfig.setIdleTimeout(Duration.ofMinutes(2).toMillis());
		return hikariConfig;
	}

}
