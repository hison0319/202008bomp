package com.bomp.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MailController {
	@Autowired
	private JavaMailSender mailSender;
	
	@ResponseBody
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public boolean sendMail(String name, String fromEmail, String messageContext) {
		
		System.out.println(name+", "+fromEmail+", "+messageContext);
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setTo("hison0319test@gmail.com");
			messageHelper.setText("봄봄백과 문의 메일\n"+name+"님께서 메일을 보냈습니다.\n"+messageContext+"\n 보낸이 메일주소는 :"+fromEmail);
			messageHelper.setFrom(fromEmail);
			messageHelper.setSubject("봄봄백과");
			
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
