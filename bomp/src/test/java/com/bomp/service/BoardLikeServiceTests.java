package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.BoardLikeVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class BoardLikeServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private BoardLikeService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
//	@Test @Ignore
//	public void testRegister() {
//		BoardLikeVO boardLike = new BoardLikeVO();
//		boardLike.setBoardId(12);
//		boardLike.setMemberId(1);	
//		
//		service.register(boardLike);
//		log.info("!!!!!INSERT BNO : "+boardLike.getBoardLikeId());
//	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(boardLike -> log.info(boardLike));
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
		BoardLikeVO boardLike = service.get(4);
		if(boardLike == null) {
			return;
		}
		System.out.println("----------------------"+boardLike);
		boardLike.setBoardId(12);
		boardLike.setMemberId(2);	
		log.info("!!!!!MODIFY RESULT : "+service.modify(boardLike));
		if(service.modify(boardLike)) {
			System.out.println("----------------------"+service.get(4));			
		}
	}
}
