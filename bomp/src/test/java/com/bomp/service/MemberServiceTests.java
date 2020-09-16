package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class MemberServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private MemberService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	@Test @Ignore
	public void testRegister() {
		MemberVO member = new MemberVO();
		member.setMemberKakao("000000001");
		member.setMemberNick("hani22");
		
		service.register(member);
		log.info("!!!!!INSERT BNO : "+member.getMemberId());
	}
	@Test @Ignore
	public void testGetList() {
		service.getList().forEach(member -> log.info(member));
	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(22));
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!REMOVE RESULT : "+service.remove(24));
	}
	@Test @Ignore
	public void testUpdate() {
		MemberVO member = service.get(22);
		if(member == null) {
			return;
		}
		member.setMemberKakao("hanihani!12341234");
		member.setMemberNick("hanihani");
		System.out.println("----------------------"+member);
		log.info("!!!!!MODIFY RESULT : "+service.modify(member));
		if(service.modify(member)) {
			System.out.println("----------------------"+service.get(22));			
		}
	}
}
