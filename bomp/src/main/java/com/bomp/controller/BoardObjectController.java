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
@RequestMapping("/board/object")
public class BoardObjectController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardViewService boardViewService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model m, String arrMethod) {
		m.addAttribute("arrMethod",arrMethod);
		return "/board/category/board_list_object";			
	}
	
	private static final String CATEGORY = "사물";
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String boardList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		int pageNum = (parameters.get("pageNum")!="")?Integer.parseInt((String) parameters.get("pageNum")):1;
		String arrMethod = (parameters.get("arrMethod")!="")?(String)parameters.get("arrMethod"):"p";
		arrMethod = arrMethod==null?"p":arrMethod;
		// 게시글 리스트
		Criteria cri = new Criteria(pageNum, 5);
		List<BoardVO> boardList = new ArrayList<>();
		String boardListDownEndToggle = "f";
		if(memberS != null) { //로그인이 되어있을 시
			if(arrMethod.equals("p")) {
				int totalTagCount = boardService.getCountCategoryTag(memberS.getMemberId(), CATEGORY);
				int totalTagPageCount = totalTagCount/5;
				boardList = boardService.getListCategoryTagPopular(memberS.getMemberId(), CATEGORY, cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getListCategoryNoneTagPopular(memberS.getMemberId(), CATEGORY, cri);
					return boardViewService.boardSetView(boardList, memberS.getMemberId(), "t");
				}
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			} else { //최신순 정렬
				int totalTagCount = boardService.getCountCategoryTag(memberS.getMemberId(), CATEGORY);
				int totalTagPageCount = totalTagCount/5;
				boardList = boardService.getListCategoryTagUDate(memberS.getMemberId(), CATEGORY, cri);
				if(boardList.size()==0){
					if(totalTagCount == 0) {
						cri.setRNConAsChangePageNum(pageNum - totalTagPageCount);						
					} else {
						cri.setRNConAsChangePageNum(pageNum - (totalTagPageCount+1));						
					}
					boardList = boardService.getListCategoryNoneTagUDate(memberS.getMemberId(), CATEGORY, cri);
					return boardViewService.boardSetView(boardList, memberS.getMemberId(), "t");
				}
				return boardViewService.boardSetView(boardList, memberS.getMemberId(), boardListDownEndToggle);
			}
		} else { //미 로그인 시
			if(arrMethod.equals("p")) { //인기순
				boardList = boardService.getListCategoryPopular(CATEGORY, cri);
				return boardViewService.boardSetView(boardList, 0, "t");				
			} else { //최신순
				boardList = boardService.getListCategoryUDate(CATEGORY, cri);
				return boardViewService.boardSetView(boardList, 0, "t");					
			}
		}
	}
}
