package com.jjtech.spring.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TenantAwareHikariDataSource extends HikariDataSource {

	
    public TenantAwareHikariDataSource(HikariConfig hikariConfig) {
		super(hikariConfig);
	}

	@Override
    public Connection getConnection() throws SQLException {
        Connection connection = super.getConnection();

        try (Statement sql = connection.createStatement()) {
            sql.execute("SET rls.tenant_id = '" + TenantDatabaseContextHolder.getTenantId() + "'");
        }

        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection connection = super.getConnection(username, password);

        try (Statement sql = connection.createStatement()) {
            sql.execute("SET rls.tenant_id = '" + TenantDatabaseContextHolder.getTenantId() + "'");
        }

        return connection;
    }

}