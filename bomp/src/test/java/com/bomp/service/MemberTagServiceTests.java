package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.MemberTagVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class MemberTagServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private MemberTagService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
//	@Test @Ignore
//	public void testRegister() {
//		MemberTagVO memberTag = new MemberTagVO();
//		memberTag.setMemberId(22);
//		memberTag.setTag("색상");
//		
//		service.register(memberTag);
//		log.info("!!!!!INSERT BNO : "+memberTag.getMemberTagId());
//	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(memberTag -> log.info(memberTag));
	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(4));
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!REMOVE RESULT : "+service.remove(2));
	}
	@Test @Ignore
	public void testUpdate() {
		MemberTagVO memberTag = service.get(4);
		if(memberTag == null) {
			return;
		}
		System.out.println("----------------------"+memberTag);
		memberTag.setTag("동물");	
		log.info("!!!!!MODIFY RESULT : "+service.modify(memberTag));
		if(service.modify(memberTag)) {
			System.out.println("----------------------"+service.get(4));			
		}
	}
}
