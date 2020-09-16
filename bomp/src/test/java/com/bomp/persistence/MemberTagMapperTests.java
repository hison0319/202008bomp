package com.bomp.persistence;

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
public class MemberTagMapperTests {
	@Setter(onMethod_ = @Autowired)
	private MemberTagMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(memberTag -> log.info(memberTag));
	}
	
//	@Test @Ignore
//	public void testInsert() {
//		MemberTagVO memberTag = new MemberTagVO();
//		memberTag.setMemberId(1);
//		memberTag.setTag("동물");
//		mapper.insert(memberTag);
//		
//		log.info(memberTag);
//	}
//	@Test @Ignore
//	public void testInsertSelectKey() {
//		MemberTagVO memberTag = new MemberTagVO();
//		memberTag.setMemberId(1);
//		memberTag.setTag("색깔");
//		mapper.insertSelectKey(memberTag);
//		
//		log.info(memberTag);
//	}
	@Test @Ignore
	public void testRead() {
		MemberTagVO memberTag = mapper.read(3);
		
		log.info(memberTag);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(3));
	}
	@Test @Ignore
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(1));
		MemberTagVO memberTag = mapper.read(1);
		memberTag.setTag("물고기");
		int count = mapper.update(memberTag);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(1));		
	}
}
