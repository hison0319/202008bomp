package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.CommentLikeVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class CommentLikeServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private CommentLikeService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
//	@Test @Ignore
//	public void testRegister() {
//		CommentLikeVO commentLike = new CommentLikeVO();
//		commentLike.setCommentId(12);
//		commentLike.setMemberId(1);	
//		
//		service.register(commentLike);
//		log.info("!!!!!INSERT BNO : "+commentLike.getCommentLikeId());
//	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(commentLike -> log.info(commentLike));
	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(2));
	}
//	@Test @Ignore
//	public void testDelete() {
//		log.info("!!!!!REMOVE RESULT : "+service.remove(2));
//	}
	@Test @Ignore
	public void testUpdate() {
		CommentLikeVO commentLike = service.get(4);
		if(commentLike == null) {
			return;
		}
		System.out.println("----------------------"+commentLike);
		commentLike.setCommentId(12);
		commentLike.setMemberId(2);	
		log.info("!!!!!MODIFY RESULT : "+service.modify(commentLike));
		if(service.modify(commentLike)) {
			System.out.println("----------------------"+service.get(4));			
		}
	}
}
