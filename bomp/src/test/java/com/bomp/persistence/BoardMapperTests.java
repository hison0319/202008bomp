package com.bomp.persistence;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bomp.domain.BoardVO;
import com.bomp.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
//	@Test @Ignore
//	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
	
	@Test @Ignore
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("new_test_title");
		board.setCategoryName("자연");
		board.setMemberId(2);
		board.setContent("안녕하숨둥!");
		
		mapper.insert(board);
		
		log.info(board);
	}
	@Test @Ignore
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("new_test_title_with_selectKey");
		board.setContent("new_test_content_with_selectKey");
		board.setCategoryName("풍경");
		board.setMemberId(1);
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	@Test @Ignore
	public void testRead() {
		BoardVO board = mapper.read(12);
		
		log.info(board);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(18));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(25));
		BoardVO board = new BoardVO();
		board.setBoardId(25);
		board.setCategoryName("(인위적)사물");
		board.setContent("자동null불가?");
		board.setTitle("update_test_title");
		
		int count = mapper.update(board);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(25));		
	}
	@Test @Ignore
	public void testPaging() {
		Criteria cri = new Criteria();
		String categoryName = "토론방";
		List<BoardVO> list = mapper.getListLimitAndCategoryWithMemberNick(cri.getFirst(), cri.getAmount(), categoryName);
		list.forEach(board -> log.info(board));
	}
}
