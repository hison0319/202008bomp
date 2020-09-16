package com.bomp.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardTagServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private BoardTagService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	@Test @Ignore
	public void testRegister() {
		BoardTagVO boardTag = new BoardTagVO();
		boardTag.setBoardId(12);
		boardTag.setTag1("동물");	
		boardTag.setTag2("자연");	
		boardTag.setTag3("바다");	
		boardTag.setTag4("");	
		boardTag.setTag5("");	
		
		service.register(boardTag);
		log.info("!!!!!INSERT BNO : "+boardTag.getBoardTagId());
	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(boardTag -> log.info(boardTag));
	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(2));
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!REMOVE RESULT : "+service.remove(2));
	}
	@Test @Ignore
	public void testUpdate() {
		BoardTagVO boardTag = service.get(4);
		if(boardTag == null) {
			return;
		}
		System.out.println("----------------------"+boardTag);
		boardTag.setBoardId(12);
		boardTag.setTag1("동물");	
		boardTag.setTag2("");	
		boardTag.setTag3("");	
		boardTag.setTag4("");	
		boardTag.setTag5("");		
		log.info("!!!!!MODIFY RESULT : "+service.modify(boardTag));
		if(service.modify(boardTag)) {
			System.out.println("----------------------"+service.get(4));			
		}
	}
}
