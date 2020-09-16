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

import com.bomp.domain.AlertVO;
import com.bomp.domain.BoardMarkVO;
import com.bomp.domain.BoardVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MemberVO;
import com.bomp.service.BoardMarkService;
import com.bomp.service.BoardService;
import com.bomp.service.BoardViewService;

import lombok.Setter;

@Controller
@RequestMapping("/board/mark")
public class BoardMarkController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardViewService boardViewService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardMarkService boardMarkService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardListMark(Model m, String arrMethod, String bLMemberId) {
		m.addAttribute("arrMethod",arrMethod);
		if (bLMemberId == null || bLMemberId.equals("")) {
			return "/error/error_page";
		}
		m.addAttribute("bLMemberId",bLMemberId);
		return "/board/board_list_mark";				
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String boardList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (parameters.get("bLMemberId") == null || parameters.get("bLMemberId").equals("")) {
			return "/error/error_page";
		}
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		int bLMemberId = Integer.parseInt((String)parameters.get("bLMemberId"));
		if (memberS.getMemberId() != bLMemberId) {
			return "/error/error_page";
		}
		int pageNum = (parameters.get("pageNum")!="" || parameters.get("pageNum")!=null)?Integer.parseInt((String) parameters.get("pageNum")):1;
		String arrMethod = (parameters.get("arrMethod")!="")?(String)parameters.get("arrMethod"):"p";
		arrMethod = arrMethod==null?"p":arrMethod;
		// 게시글 리스트
		Criteria cri = new Criteria(pageNum, 5);
		String boardListDownEndToggle = "t";
		List<BoardVO> boardList = new ArrayList<>();	
		if(arrMethod.equals("p")) {
			boardList = boardService.getListMarkPopular(bLMemberId, cri);
			return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
		} else {
			boardList = boardService.getListMarkUDate(bLMemberId, cri);
			return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
		}
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String boardMark(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int boardId = Integer.parseInt((String) parameters.get("boardId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
	    boolean marked = parameters.get("marked").equals("true");
	    if(!marked) {
	    	//북마크가 아닌 상태 -> 좋아요
	    	BoardMarkVO boardMark =new BoardMarkVO(boardId, memberId);
	    	try {
	    		boardMarkService.register(boardMark);
	    		return "1";
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return "0";
	    	}
	    } else {
	    	//북마크인 상태 -> 좋아요 취소
	    	try {
	    		boardMarkService.remove(boardMarkService.getMemberIdANDBoardId(memberId, boardId).getBoardMarkId());
				return "2";
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
	    }
	}
}
