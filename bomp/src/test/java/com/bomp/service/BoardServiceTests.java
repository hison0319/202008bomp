package com.bomp.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	@Test @Ignore
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setCategoryName("토론방");
		board.setTitle("test_service_title");
		board.setMemberId(1);
		board.setContent("test_service_content");
		
		service.register(board);
		log.info("!!!!!INSERT BNO : "+board.getBoardId());
	}
//	@Test @Ignore
//	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
//	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(1));
	}
//	@Test @Ignore
//	public void testDelete() {
//		log.info("!!!!!REMOVE RESULT : "+service.remove(1));
//	}
	@Test @Ignore
	public void testUpdate() {
		BoardVO board = service.get(12);
		if(board == null) {
			return;
		}
		System.out.println("----------------------"+board);
		board.setTitle("update_title_with_service_and_java");
		board.setCategoryName("(인위적)사물");
//		log.info("!!!!!MODIFY RESULT : "+service.modify(board));
//		if(service.modify(board)) {
//			System.out.println("----------------------"+service.get(12));			
//		}
	}
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		List<BoardVO> list = service.getListLimitAndCategoryWithMemberNick(cri, "토론방");
		list.forEach(board -> log.info(board));
	}
	@Test @Ignore
	public void testCount() {
		System.out.println(service.getCountWithCategory("토론방"));
	}
}
