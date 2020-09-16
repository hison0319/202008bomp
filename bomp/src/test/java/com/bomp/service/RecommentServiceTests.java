package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.RecommentVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class RecommentServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private RecommentService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
//	@Test @Ignore
//	public void testRegister() {
//		RecommentVO recomment = new RecommentVO();
//		recomment.setCommentId(3);
//		recomment.setRecommentText("test_service");
//		recomment.setMemberId(1);
//		
//		service.register(recomment);
//		log.info("!!!!!INSERT BNO : "+recomment.getRecommentId());
//	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(recomment -> log.info(recomment));
	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(2));
	}
//	@Test @Ignore
//	public void testDelete() {
//		log.info("!!!!!REMOVE RESULT : "+service.remove(2));
//	}
	@Test
	public void testUpdate() {
		RecommentVO recomment = service.get(4);
		if(recomment == null) {
			return;
		}
		System.out.println("----------------------"+recomment);
		recomment.setRecommentText("test_update_service");	
		log.info("!!!!!MODIFY RESULT : "+service.modify(recomment));
		if(service.modify(recomment)) {
			System.out.println("----------------------"+service.get(4));			
		}
	}
}
