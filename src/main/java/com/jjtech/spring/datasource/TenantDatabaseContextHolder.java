package com.jjtech.spring.datasource;

import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantDatabaseContextHolder {

	private static ThreadLocal<TenantDatabaseContex> CONTEXT = new ThreadLocal<>();

	public static void set(TenantDatabaseContex context) {
		Assert.notNull(context, "TenantDatabaseContex cannot be null");
		log.info("TenantDatabaseContex {}",context.toString());
		CONTEXT.set(context);
	}

	public static String getTenantDb() {

		if (CONTEXT.get() == null) {
			return null;
		}
		return CONTEXT.get().getClientGroup();
	}

	public static void clear() {
		CONTEXT.remove();
	}

	public static String getTenantId() {
		if (CONTEXT.get() == null) {
			return null;
		}
		return CONTEXT.get().getTenantId();
	}
}