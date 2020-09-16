package com.bomp.persistence;

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
public class RecommentMapperTests {
	@Setter(onMethod_ = @Autowired)
	private RecommentMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(recomment -> log.info(recomment));
	}
	
//	@Test
//	public void testInsert() {
//		RecommentVO recomment = new RecommentVO();
//		recomment.setCommentId(25);
//		recomment.setRecommentText("testRecomment4444");
//		recomment.setMemberId(1);
//		mapper.insert(recomment);
//		
//		log.info(recomment);
//	}
//	@Test @Ignore
//	public void testInsertSelectKey() {
//		RecommentVO recomment = new RecommentVO();
//		recomment.setCommentId(3);
//		recomment.setRecommentText("testRecomment22");
//		recomment.setMemberId(2);
//		mapper.insertSelectKey(recomment);
//		
//		log.info(recomment);
//	}
	@Test @Ignore
	public void testRead() {
		RecommentVO recomment = mapper.read(3);
		
		log.info(recomment);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(3));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(1));
		RecommentVO recomment = mapper.read(1);
		recomment.setRecommentText("test입니다333333.");
		
		int count = mapper.update(recomment);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(1));		
	}
}
