package com.bomp.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bomp.domain.MemberVO;
import com.bomp.domain.TimeFmtDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class TimeFmtTest {
	
	@Setter(onMethod_ = {@Autowired})
	private MemberService service;
	
	@Test
	public void timeFmtTest() {
		
		MemberVO member1 = new MemberVO();
		MemberVO member2 = new MemberVO();
		member1 = service.get(46);
		member2 = service.get(32);
		Date memberRegDate1 = member1.getRegDate();
		Date memberRegDate2 = member2.getRegDate();
		
		TimeFmtDTO tfd1 = new TimeFmtDTO(memberRegDate1);
		System.out.println(tfd1.getTimeStr());
		TimeFmtDTO tfd2 = new TimeFmtDTO(memberRegDate2);
		System.out.println(tfd2.getTimeStr());
	}
}
