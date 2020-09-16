package com.bomp.persistence;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bomp.domain.BoardTagVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class BoardTagMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardTagMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(boardTag -> log.info(boardTag));
	}
	
	@Test @Ignore
	public void testInsert() {
		BoardTagVO boardTag = new BoardTagVO();
		boardTag.setBoardId(1);
		boardTag.setTag1("노란색");
		boardTag.setTag2("노랑색");
		boardTag.setTag3("누렁색");
		boardTag.setTag4("");
		boardTag.setTag5("");
		mapper.insert(boardTag);
		
		log.info(boardTag);
	}
	@Test @Ignore
	public void testInsertSelectKey() {
		BoardTagVO boardTag = new BoardTagVO();
		boardTag.setBoardId(24);
		boardTag.setTag1("바다");
		boardTag.setTag2("해변");
		boardTag.setTag3("");
		boardTag.setTag4("");
		boardTag.setTag5("");
		mapper.insertSelectKey(boardTag);
		
		log.info(boardTag);
	}
	@Test @Ignore
	public void testRead() {
		BoardTagVO boardTag = mapper.read(3);
		
		log.info(boardTag);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(1));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(5));
		BoardTagVO boardTag = mapper.read(5);
		boardTag.setTag3("beach");
		boardTag.setTag4("");
		boardTag.setTag5("");
		
		int count = mapper.update(boardTag);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(5));		
	}
}
