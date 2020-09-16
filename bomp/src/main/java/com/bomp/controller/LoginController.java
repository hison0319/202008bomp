package com.bomp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bomp.domain.MemberVO;
import com.bomp.service.MemberService;
import com.bomp.util.KakaoAccessToken;
import com.bomp.util.KakaoUserInfo;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Setter;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;
	
	@RequestMapping(value = "/kakaologin", produces = "application/json", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String kakaoLogin(@RequestParam("code") String code, Model m, HttpSession session) {
		JsonNode accessToken;
		JsonNode jsonToken = KakaoAccessToken.getKakaoAccessToken(code);
		accessToken = jsonToken.get("access_token");

		JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(accessToken);
		String memberKakao = userInfo.path("id").asText();

		JsonNode properties = userInfo.path("properties");

		String memberNick = properties.path("nickname").asText();
		
		MemberVO member = new MemberVO();
		member.setMemberKakao(memberKakao);
		member.setMemberNick(memberNick);
		
		
		if(memberService.getWithKakao(memberKakao) == null) {
			memberService.register(member);			
			session.setAttribute("member", memberService.get(member.getMemberId()));
			return "redirect:/";			
		} else {
			session.setAttribute("member", memberService.getWithKakao(memberKakao));
			return "redirect:/";			
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("member");
		if(session.getAttribute("access_token") != null) {
			session.removeAttribute("access_token");
		}
		return "redirect:/";
	}
}
