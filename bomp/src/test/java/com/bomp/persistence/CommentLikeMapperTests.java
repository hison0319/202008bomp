package com.bomp.persistence;

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
public class CommentLikeMapperTests {
	@Setter(onMethod_ = @Autowired)
	private CommentLikeMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(commentLike -> log.info(commentLike));
	}
	
//	@Test @Ignore
//	public void testInsert() {
//		CommentLikeVO commentLike = new CommentLikeVO();
//		commentLike.setCommentId(3);
//		commentLike.setMemberId(2);		
//		mapper.insert(commentLike);
//		
//		log.info(commentLike);
//	}
//	@Test @Ignore
//	public void testInsertSelectKey() {
//		CommentLikeVO commentLike = new CommentLikeVO();
//		commentLike.setCommentId(5);
//		commentLike.setMemberId(1);
//		mapper.insertSelectKey(commentLike);
//		
//		log.info(commentLike);
//	}
	@Test @Ignore
	public void testRead() {
		CommentLikeVO commentLike = mapper.read(23);
		
		log.info(commentLike);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(23));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(21));
		CommentLikeVO commentLike = mapper.read(21);
		commentLike.setMemberId(1);
		
		int count = mapper.update(commentLike);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(21));		
	}
}
