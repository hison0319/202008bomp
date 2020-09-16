package com.bomp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bomp.domain.AlertVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MemberTagVO;
import com.bomp.domain.MemberVO;
import com.bomp.domain.MessageVO;
import com.bomp.domain.TimeFmtDTO;
import com.bomp.service.AlertService;
import com.bomp.service.MemberService;
import com.bomp.service.MemberTagService;
import com.bomp.service.MessageService;
import com.google.gson.Gson;

import lombok.Setter;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;
	
	@Setter(onMethod_ = {@Autowired})
	private MemberTagService memberTagService;
	
	@Setter(onMethod_ = {@Autowired})
	private AlertService alertService;
	
	@Setter(onMethod_ = {@Autowired})
	private MessageService messageService;
		
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String boardList(Model m, HttpSession session, String memberId, String arrMethod) {	
		TimeFmtDTO tfd;
		m.addAttribute("arrMethod",arrMethod);
		//내 정보
		if(memberId == null || memberId.equals("")) {
			MemberVO memberS = (MemberVO)session.getAttribute("member");
			MemberVO member = memberService.get(memberS.getMemberId());
			m.addAttribute("member", member);
			tfd = new TimeFmtDTO(memberS.getRegDate());
			m.addAttribute("memberRegDateSting", tfd.getTimeStr());
			List<MemberTagVO> memberTagList = memberTagService.getListWithMemberId(member.getMemberId());
			String tagList = "";
			for(MemberTagVO mTL : memberTagList) {
				tagList += "#"+mTL.getTag()+" ";
			}
			m.addAttribute("tagList",tagList);
			return "member/member";
		} else { //남 정보
			MemberVO member = memberService.get(Integer.parseInt(memberId));
			if(member == null) {
				return "error/member_none_error";
			}
			m.addAttribute("member", member);
			tfd = new TimeFmtDTO(member.getRegDate());
			m.addAttribute("memberRegDateSting", tfd.getTimeStr());
			List<MemberTagVO> memberTagList = memberTagService.getListWithMemberId(member.getMemberId());
			String tagList = "";
			for(MemberTagVO mTL : memberTagList) {
				tagList += "#"+mTL.getTag()+" ";
			}
			m.addAttribute("tagList",tagList);
			return "member/member";
		}
	}
	
	@RequestMapping(value = "/alert", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String alertList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		Map<String, Object> retData = new HashMap<String, Object>();
		Gson gson = new Gson();
		String jsonStr;
		if(memberS == null) {
			retData.put("code", "NO");
			jsonStr = gson.toJson(retData);
			return jsonStr;
		}
		int pageNum = Integer.parseInt((String) parameters.get("pageNum"));
		String arrMethod = (parameters.get("arrMethod")!="")?(String)parameters.get("arrMethod"):"n";
		arrMethod = arrMethod==null?"n":arrMethod;
		// 알람 리스트
		Criteria cri = new Criteria(pageNum, 10);
		List<AlertVO> alertList = alertService.alertViewSet(memberS.getMemberId(), arrMethod, cri);
		retData.put("code", "OK");
		retData.put("alertList", alertList);
		// 시간 정보
		TimeFmtDTO tfd = new TimeFmtDTO();
		List<String> alertUDateList = new ArrayList<>();
		for (AlertVO aL : alertList) {
			alertUDateList.add(tfd.getTimeFmtSetDate(aL.getUDate()));
		}
		retData.put("alertUDateList", alertUDateList);
		jsonStr = gson.toJson(retData);
		return jsonStr;
	}
	
	@RequestMapping(value = "/tagReg", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String tagReg(@RequestParam Map<String, Object> parameters, HttpSession session) {
		MemberVO memberS = (MemberVO)session.getAttribute("member");
		int memberId = Integer.parseInt((String) parameters.get("memberId"));
		String memberTag = (String) parameters.get("memberTag");
		if(memberS.getMemberId() != memberId) {
			return "0";
		}
		memberTagService.register(memberId, memberTag);
		session.removeAttribute("member");
		session.setAttribute("member", memberService.get(memberId));
		return "1";
	}

	@RequestMapping(value = "/memberNickCheck", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String memberNickCheck(@RequestParam Map<String, Object> parameters) {
		String memberNick = (String) parameters.get("memberNick");
		if(memberService.checkMemberNick(memberNick)) {
			return "1";
		}
		return "0";
	}	
	
	@RequestMapping(value = "/memberNickModify", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String memberNickModify(int memberId, String memberNick, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		MemberVO member = new MemberVO();
		member.setMemberId(memberId);
		member.setMemberNick(memberNick);
		memberService.modifyMemberNick(member);
		session.removeAttribute("member");
		session.setAttribute("member", memberService.get(memberId));
		return "complete/comp_modify_memberNick";
	}

	@RequestMapping(value = "/messageSend", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String messageReg(@RequestParam Map<String, Object> parameters) {
		int fromMemberId = Integer.parseInt((String) parameters.get("fromMemberId"));
		int toMemberId = Integer.parseInt((String) parameters.get("toMemberId"));
		String messageContent = (String) parameters.get("messageContent");
		System.out.println(fromMemberId);
		System.out.println(toMemberId);
		System.out.println(messageContent);
		
		MessageVO message = new MessageVO(fromMemberId, toMemberId, messageContent);
		AlertVO alert = alertService.alertRegSet(toMemberId, messageContent, fromMemberId, 0, 8);
		messageService.register(message, alert);
		return "1";
	}
	
	@RequestMapping(value = "/getMessage", method = RequestMethod.GET)
	public String getMessage(Model m, String messageId) {
		MessageVO message = messageService.get(Integer.parseInt(messageId));
		m.addAttribute("message",message);
		return "member/message";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(Model m, int memberId){
		try {
			if(memberService.remove(memberId)) {
				return "/complete/comp_remove_discussion";
			} else {
				return "error/error_page";				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error_page";
		}
	}
}
