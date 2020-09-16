package com.bomp.persistence;

import java.text.SimpleDateFormat;

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
public class MemberMapperTests {
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(member -> log.info(member));
	}
	
	@Test @Ignore
	public void testInsert() {
		MemberVO member = new MemberVO();
		member.setMemberKakao("test!0001");
		member.setMemberNick("test");
		
		mapper.insert(member);
		
		log.info(member);
	}
	@Test @Ignore
	public void testInsertSelectKey() {
		MemberVO member = new MemberVO();
		member.setMemberKakao("test3!0003");
		member.setMemberNick("test3");
		
		mapper.insertSelectKey(member);
		System.out.println("\n!!!!!"+member.getMemberId());
		log.info(member);
	}
	@Test @Ignore
	public void testRead() {
		MemberVO member = mapper.read(46);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		log.info(member);
		
		System.out.println(sdf.format(member.getRegDate()));
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(3));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(21));
		MemberVO member = mapper.read(21);
		member.setMemberKakao("test2!0002");
		member.setMemberNick("test2");
		int count = mapper.update(member);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(21));		
	}
}
