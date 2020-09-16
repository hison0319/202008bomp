package com.bomp.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bomp.domain.AlertVO;
import com.bomp.domain.BoardVO;
import com.bomp.domain.MemberVO;
import com.bomp.service.AlertService;
import com.bomp.service.BoardService;

import lombok.Setter;

@Controller
@RequestMapping("/complain")
public class ComplainController {
	
	@Setter(onMethod_ = { @Autowired })
	private AlertService alertService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@RequestMapping(value = "/message", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String complainMessage(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int messageId = Integer.parseInt((String) parameters.get("messageId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
		
		AlertVO alert = alertService.alertRegSet(memberId, "", 1, messageId, 9);
				
		alertService.register(alert);
		return "1";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String complainBoard(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int boardId = Integer.parseInt((String) parameters.get("boardId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
		
		AlertVO alert = alertService.alertRegSet(memberId, "", 1, boardId, 0);
				
		alertService.register(alert);
		return "1";
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String complainComment(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int commentId = Integer.parseInt((String) parameters.get("commentId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
		
	    AlertVO alert = alertService.alertRegSet(memberId, "", 1, commentId, 1);
		
		alertService.register(alert);
		return "1";
	}
	
	@RequestMapping(value = "/recomment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String complainRecomment(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int recommentId = Integer.parseInt((String) parameters.get("recommentId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
		
	    AlertVO alert = alertService.alertRegSet(memberId, "", 1, recommentId, 2);
		
		alertService.register(alert);
		return "1";
	}
}
