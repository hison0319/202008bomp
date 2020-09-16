package com.bomp.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.bomp.domain.BoardTagVO;
import com.bomp.domain.BoardVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MemberVO;
import com.bomp.domain.TimeFmtDTO;
import com.bomp.service.BoardService;
import com.bomp.service.BoardTagService;
import com.bomp.service.BoardViewService;

import lombok.Setter;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardTagService boardTagService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardViewService boardViewService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model m, String arrMethod) {
		m.addAttribute("arrMethod",arrMethod);
		return "/board/board_list";			
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String boardList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		int pageNum = (parameters.get("pageNum")!="")?Integer.parseInt((String) parameters.get("pageNum")):1;
		String arrMethod = (parameters.get("arrMethod")!="")?(String)parameters.get("arrMethod"):"p";
		arrMethod = arrMethod==null?"p":arrMethod;
		// 게시글 리스트
		Criteria cri = new Criteria(pageNum, 5);
		String boardListDownEndToggle = "f";
		List<BoardVO> boardList = new ArrayList<>();
		if(memberS != null) { //로그인이 되어있을 시
			//인기순 정렬
			if(arrMethod.equals("p")) {
				int totalTagCount = boardService.getCountAllTag(memberS.getMemberId());
				int totalTagPageCount = totalTagCount/5;
				boardList = boardService.getListAllTagPopular(memberS.getMemberId(), cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getListAllNoneTagPopular(memberS.getMemberId(), cri);
					return boardViewService.boardSetView(boardList, memberS.getMemberId(), "t");
				}
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			} else { //최신순 정렬
				int totalTagCount = boardService.getCountAllTag(memberS.getMemberId());
				int totalTagPageCount = totalTagCount/5;
				boardList = boardService.getListAllTagUDate(memberS.getMemberId(), cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getListAllNoneTagUDate(memberS.getMemberId(), cri);
					return boardViewService.boardSetView(boardList, memberS.getMemberId(), "t");
				}
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			}
		} else { //미 로그인 시
			if(arrMethod.equals("p")) { //인기순
				boardList = boardService.getListAllPopular(cri);
				return boardViewService.boardSetView(boardList, 0, "t");				
			} else { //최신순
				boardList = boardService.getListAllUDate(cri);
				return boardViewService.boardSetView(boardList, 0, "t");					
			}
		}
	}
	
	@RequestMapping(value = "/posting", method = RequestMethod.GET)
	public String postingForm(HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		} else {
			return "/board/posting";
		}
	}

	@RequestMapping(value = "/posting", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String posting(Model m, BoardVO board, @RequestParam(required = false) String boardTagStr,
			@RequestParam(required = false) MultipartFile imageFile, HttpSession session) {
		MemberVO memberS = new MemberVO();
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		} else {
			memberS = (MemberVO) session.getAttribute("member");
			try {
				return boardService.posting(board, memberS.getMemberId(), boardTagStr, imageFile);
			} catch (IllegalStateException e) {
				return "/error/error_page";
			} catch (IOException e) {
				return "/error/error_page";
			}
		}
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String postModify(Model m, int boardId) {
		TimeFmtDTO tfd;
		BoardVO board = boardService.get(boardId);
		m.addAttribute("board", board);
		BoardTagVO boardTag = boardTagService.get(board.getBoardId());
		String boardTagString = "";
		if(boardTag != null) {
			if(boardTag.getTag1() != null) {
				boardTagString += "#"+boardTag.getTag1()+" ";
			}
			if(boardTag.getTag2() != null) {
				boardTagString += "#"+boardTag.getTag2()+" ";
			}
			if(boardTag.getTag3() != null) {
				boardTagString += "#"+boardTag.getTag3()+" ";
			}
			if(boardTag.getTag4() != null) {
				boardTagString += "#"+boardTag.getTag4()+" ";
			}
			if(boardTag.getTag5() != null) {
				boardTagString += "#"+boardTag.getTag5()+" ";
			}
		}
		m.addAttribute("boardTagString", boardTagString);
		tfd = new TimeFmtDTO(board.getUDate());
		m.addAttribute("boardUDateString", tfd.getTimeStr());
		return "/board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String modify(Model m, BoardVO board, @RequestParam(required = false) String boardTagStr,
			@RequestParam(required = false) MultipartFile imageFile, HttpSession session) {
		MemberVO memberS = new MemberVO();
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		} else {
			memberS = (MemberVO) session.getAttribute("member");
			try {
				return boardService.modify(board, memberS.getMemberId(), boardTagStr, imageFile);
			} catch (IllegalStateException e) {
				return "/error/error_page";
			} catch (IOException e) {
				return "/error/error_page";
			}
		}
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeBoard(Model m, int boardId){
		try {
			if(boardService.remove(boardId)) {
				return "/complete/comp_remove_board";
			} else {
				return "error/error_page";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error_page";
		}
	}
	
	@RequestMapping(value = "/removeDiscussion", method = RequestMethod.GET)
	public String removeDiscussion(Model m, int boardId){
		try {
			if(boardService.remove(boardId)) {
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
