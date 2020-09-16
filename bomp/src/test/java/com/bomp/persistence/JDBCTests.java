package com.bomp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testConnection() {
		try(Connection con =  DriverManager.getConnection(
				"jdbc:log4jdbc:mysql://localhost:3306/localhost?serverTimezone=Asia/Seoul&characterEncoding=utf8",
				"root",
				"mysql"
				)) {
			log.info(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
