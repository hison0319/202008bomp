package com.bomp.persistence;

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
public class BoardLikeMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardLikeMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(boardLike -> log.info(boardLike));
	}
	
//	@Test @Ignore
//	public void testInsert() {
//		BoardLikeVO boardLike = new BoardLikeVO();
//		boardLike.setBoardId(1);
//		boardLike.setMemberId(1);		
//		mapper.insert(boardLike);
//		
//		log.info(boardLike);
//	}
//	@Test @Ignore
//	public void testInsertSelectKey() {
//		BoardLikeVO boardLike = new BoardLikeVO();
//		boardLike.setBoardId(22);
//		boardLike.setMemberId(3);
//		mapper.insertSelectKey(boardLike);
//		
//		log.info(boardLike);
//	}
	@Test @Ignore
	public void testRead() {
		BoardLikeVO boardLike = mapper.read(12);
		
		log.info(boardLike);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(6));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(12));
		BoardLikeVO boardLike = mapper.read(12);
		boardLike.setMemberId(2);
		
		int count = mapper.update(boardLike);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(12));		
	}
	
	// regDate에 sysdate sql문을 삽입 안하면 바뀌지 않음!
	// null일 경우 일일이 잡아줘야 한다!!
	// key얻을 때 seq증가.
	// 트리거로 인해 cnt체크된 종속 튜플 삭제 후 삭제 가능.
	// 제약조건 외래키 종속삭제 경우는 튜플 자동 삭제.
}
