package com.bomp.persistence;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bomp.domain.AlertVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class AlertMapperTests {
	@Setter(onMethod_ = @Autowired)
	private AlertMapper mapper;
//	@Test @Ignore
//	public void testGetList() {
//		mapper.getList().forEach(alert -> log.info(alert));
//	}
	
	@Test @Ignore
	public void testInsert() {
		AlertVO alert = new AlertVO();
		alert.setMemberId(1);
		alert.setAlertComment("hello~");
		mapper.insert(alert);
		
		log.info(alert);
	}
//	@Test @Ignore
//	public void testInsertSelectKey() {
//		AlertVO alert = new AlertVO();
//		alert.setMemberId(1);
//		alert.setAlertComment("hello~");
//		mapper.insertSelectKey(alert);
//		
//		log.info(alert);
//	}
	@Test @Ignore
	public void testRead() {
		AlertVO alert = mapper.read(8);
		
		log.info(alert);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(8));
	}
//	@Test @Ignore
//	public void testUpdate() {
//		log.info("!!!!!UPDATE BOARD : "+mapper.read(2));
//		AlertVO alert = mapper.read(2);
//		alert.setAlertComment("hello~world");
//		
//		int count = mapper.update(alert);
//		log.info("!!!!!UPDATE COUNT : "+count);
//		log.info("!!!!!UPDATE BOARD : "+mapper.read(2));		
//	}
}
