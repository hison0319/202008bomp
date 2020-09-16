package com.bomp.controller;

import java.util.ArrayList;
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

import com.bomp.domain.BoardVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MemberVO;
import com.bomp.service.BoardService;
import com.bomp.service.BoardViewService;
import com.bomp.service.MemberService;

import lombok.Setter;

@Controller
@RequestMapping("/board/member")
public class BoardMemberController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardViewService boardViewService;
	
	@Setter(onMethod_ = { @Autowired })
	private MemberService memberService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardListMember(Model m, String arrMethod, String bLMemberId) {
		m.addAttribute("arrMethod",arrMethod);
		if (bLMemberId == null || bLMemberId.equals("")) {
			return "/error/error_page";
		}
		int memberId = Integer.parseInt(bLMemberId);
		m.addAttribute("bLMember",memberService.get(memberId));
		m.addAttribute("bLMemberId",memberId);
		return "/board/board_list_member";			
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String boardList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (parameters.get("bLMemberId") == null || parameters.get("bLMemberId") == "") {
			return "/error/error_page";
		}
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		int pageNum = (parameters.get("pageNum")!="" || parameters.get("pageNum")!=null)?Integer.parseInt((String) parameters.get("pageNum")):1;
		String arrMethod = (parameters.get("arrMethod")!="")?(String)parameters.get("arrMethod"):"p";
		arrMethod = arrMethod==null?"p":arrMethod;
		int bLMemberId = Integer.parseInt((String)parameters.get("bLMemberId"));
		// 게시글 리스트
		Criteria cri = new Criteria(pageNum, 5);
		String boardListDownEndToggle = "t";
		List<BoardVO> boardList = new ArrayList<>();	
		if(memberS != null) {
			if(arrMethod.equals("p")) {
				boardList = boardService.getListMemberPopular(bLMemberId, cri);
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			} else {
				boardList = boardService.getListMemberUDate(bLMemberId, cri);
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			}
		}else {
			if(arrMethod.equals("p")) {
				boardList = boardService.getListMemberPopular(bLMemberId, cri);
				return boardViewService.boardSetView(boardList, 0, boardListDownEndToggle);
			} else {
				boardList = boardService.getListMemberUDate(bLMemberId, cri);
				return boardViewService.boardSetView(boardList, 0, boardListDownEndToggle);
			}
		}
	}
}
