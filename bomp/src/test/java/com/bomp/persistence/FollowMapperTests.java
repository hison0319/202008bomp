package com.bomp.persistence;

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
public class FollowMapperTests {
	@Setter(onMethod_ = @Autowired)
	private FollowMapper mapper;
	@Test @Ignore
	public void testGetList() {
		mapper.getList().forEach(follow -> log.info(follow));
	}
	
	@Test @Ignore
	public void testInsert() {
		FollowVO follow = new FollowVO();
		follow.setMemberId(1);
		follow.setFollowingId(2);
		mapper.insert(follow);
		
		log.info(follow);
	}
	@Test @Ignore
	public void testInsertSelectKey() {
		FollowVO follow = new FollowVO();
		follow.setMemberId(1);
		follow.setFollowingId(3);
		mapper.insertSelectKey(follow);
		
		log.info(follow);
	}
	@Test @Ignore
	public void testRead() {
		FollowVO follow = mapper.read(8);
		
		log.info(follow);
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!DELETE COUNT : "+mapper.delete(8));
	}
	@Test
	public void testUpdate() {
		log.info("!!!!!UPDATE BOARD : "+mapper.read(2));
		FollowVO follow = mapper.read(2);
		follow.setFollowingId(3);
		
		int count = mapper.update(follow);
		log.info("!!!!!UPDATE COUNT : "+count);
		log.info("!!!!!UPDATE BOARD : "+mapper.read(2));		
	}
}
