package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.CommentVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class CommentServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private CommentService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
//	@Test @Ignore
//	public void testRegister() {
//		CommentVO comment = new CommentVO();
//		comment.setBoardId(12);
//		comment.setCommentText("test_service");
//		comment.setMemberId(1);
//		
//		service.register(comment);
//		log.info("!!!!!INSERT BNO : "+comment.getCommentId());
//	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(comment -> log.info(comment));
	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(35));
	}
//	@Test @Ignore
//	public void testDelete() {
//		log.info("!!!!!REMOVE RESULT : "+service.remove(33));
//	}
	@Test @Ignore
	public void testUpdate() {
		CommentVO comment = service.get(35);
		if(comment == null) {
			return;
		}
		System.out.println("----------------------"+comment);
		comment.setMemberId(3);	
		log.info("!!!!!MODIFY RESULT : "+service.modify(comment));
		if(service.modify(comment)) {
			System.out.println("----------------------"+service.get(35));			
		}
	}
}
