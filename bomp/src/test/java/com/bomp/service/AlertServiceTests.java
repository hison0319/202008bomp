package com.bomp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bomp.domain.AlertVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.bomp.config.RootConfig.class})
@Log4j
public class AlertServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private AlertService service;
	@Test @Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
//	@Test @Ignore
//	public void testRegister() {
//		AlertVO alert = new AlertVO();
//		alert.setMemberId(1);
//		alert.setAlertComment("hello~world");
//		
//		service.register(alert);
//		log.info("!!!!!INSERT BNO : "+alert.getAlertId());
//	}
//	@Test @Ignore
//	public void testGetList() {
//		service.getList().forEach(alert -> log.info(alert));
//	}
	@Test @Ignore
	public void testGet() {
		log.info(service.get(2));
	}
	@Test @Ignore
	public void testDelete() {
		log.info("!!!!!REMOVE RESULT : "+service.remove(4));
	}
//	@Test @Ignore
//	public void testUpdate() {
//		AlertVO alert = service.get(2);
//		if(alert == null) {
//			return;
//		}
//		System.out.println("----------------------"+alert);
//		alert.setAlertComment("hello~world!!!");
//		log.info("!!!!!MODIFY RESULT : "+service.modify(alert));
//		if(service.modify(alert)) {
//			System.out.println("----------------------"+service.get(2));			
//		}
//	}
	
	@Test
	public void testStr() {
		String str1 = "weweqrwereqwrerw@@@!@@@@dsfwerqwe@@@@!@@@@@@@!13";
		String str2 = "tr24454353gssfggfd$@#!342rrrr144$@#!12";
		String str3 = "%$^#$$%$#^#$^$#^#$@!@#$#%#^$^##$@!54";
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		String[] strArr1 = str1.split("@@@!");
		String str = str1.replace(strArr1[strArr1.length-1], "");
		
		System.out.println(str);
		System.out.println(strArr1[strArr1.length-1]);
	
	}
}
