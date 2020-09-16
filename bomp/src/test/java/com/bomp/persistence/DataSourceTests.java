package com.bomp.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class DataSourceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private DataSource dataSource;
	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlSessionFactory;
	@Test @Ignore
	public void testConnection() {
		try (Connection con = dataSource.getConnection()){
			log.info(con);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession();
			Connection con = session.getConnection();) {
			log.info(session);
			log.info(con);
		} catch (SQLException e) {
			fail(e.getMessage());
		}	
	}
}
