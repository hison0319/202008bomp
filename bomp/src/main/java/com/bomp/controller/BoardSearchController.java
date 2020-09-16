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

import lombok.Setter;

@Controller
@RequestMapping("/board/search")
public class BoardSearchController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardViewService boardViewService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model m, String keyword, String arrMethod) {
		m.addAttribute("keyword", keyword);
		m.addAttribute("arrMethod",arrMethod);
		return "/board/board_search";			
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String boardList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		int pageNum = (parameters.get("pageNum")!="")?Integer.parseInt((String) parameters.get("pageNum")):1;
		String arrMethod = (parameters.get("arrMethod")!="")?(String)parameters.get("arrMethod"):"p";
		arrMethod = arrMethod==null?"p":arrMethod;
		String keyword = (parameters.get("keyword")!="")?(String) parameters.get("keyword"):"";
		keyword = keyword==null?"":keyword;
		// 게시글 리스트
		Criteria cri = new Criteria(pageNum, 5);
		String boardListDownEndToggle = "f";
		List<BoardVO> boardList = new ArrayList<>();
				
		int totalTagCount = boardService.getCountSearchTag(keyword);
		int totalTagPageCount = totalTagCount/5;
		if(memberS != null) {
			if(arrMethod.equals("p")) {
				boardList = boardService.getSearchTagPopular(keyword, cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getSearchNoneTagPopular(keyword, cri);
					return boardViewService.boardSetView(boardList, memberS.getMemberId(), "t");
				}
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			} else {
				boardList = boardService.getSearchTagUDate(keyword, cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getSearchNoneTagUDate(keyword, cri);
					return boardViewService.boardSetView(boardList, memberS.getMemberId(), "t");
				}
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			}
		}else {
			if(arrMethod.equals("p")) {
				boardList = boardService.getSearchTagPopular(keyword, cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getSearchNoneTagPopular(keyword, cri);
					return boardViewService.boardSetView(boardList, 0, "t");
				}
				return boardViewService.boardSetView(boardList, 0, boardListDownEndToggle);
			} else {
				boardList = boardService.getSearchTagUDate(keyword, cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getSearchNoneTagUDate(keyword, cri);
					return boardViewService.boardSetView(boardList, 0, "t");
				}
				return boardViewService.boardSetView(boardList, 0, boardListDownEndToggle);
			}
		}
	}
}
