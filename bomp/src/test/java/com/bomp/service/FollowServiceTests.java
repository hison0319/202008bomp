package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.FollowVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class FollowServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private FollowService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	@Test @Ignore
	public void testRegister() {
		FollowVO follow = new FollowVO();
		follow.setMemberId(1);
		follow.setFollowingId(2);
		
		service.register(follow);
		log.info("!!!!!INSERT BNO : "+follow.getFollowId());
	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(follow -> log.info(follow));
	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(2));
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!REMOVE RESULT : "+service.remove(4));
	}
	@Test @Ignore
	public void testUpdate() {
		FollowVO follow = service.get(2);
		if(follow == null) {
			return;
		}
		System.out.println("----------------------"+follow);
		follow.setFollowingId(2);	
		log.info("!!!!!MODIFY RESULT : "+service.modify(follow));
		if(service.modify(follow)) {
			System.out.println("----------------------"+service.get(2));			
		}
	}
}
