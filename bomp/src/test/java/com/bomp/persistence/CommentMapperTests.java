package com.bomp.persistence;

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
public class CommentMapperTests {
	@Setter(onMethod_ = @Autowired)
	private CommentMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(comment -> log.info(comment));
	}
	
	@Test @Ignore
	public void testInsert() {
		CommentVO comment = new CommentVO();
		comment.setBoardId(12);
		comment.setCommentText("test입니다3333.");
		comment.setMemberId(1);
		mapper.insert(comment);
		
		log.info(comment);
	}
	@Test @Ignore
	public void testInsertSelectKey() {
		CommentVO comment = new CommentVO();
		comment.setBoardId(12);
		comment.setCommentText("test입니다22.");
		comment.setMemberId(2);
		mapper.insertSelectKey(comment);
		
		log.info(comment);
	}
	@Test @Ignore
	public void testRead() {
		CommentVO comment = mapper.read(23);
		
		log.info(comment);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(23));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(21));
		CommentVO comment = mapper.read(21);
		comment.setCommentText("test입니다333333.");
		
		int count = mapper.update(comment);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(21));		
	}
}
