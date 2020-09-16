package com.bomp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bomp.domain.BoardVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.MemberVO;
import com.bomp.domain.TimeFmtDTO;
import com.bomp.service.BoardService;
import com.bomp.service.BoardTagService;
import com.bomp.service.BoardViewService;
import com.bomp.service.CommentLikeService;
import com.bomp.service.CommentService;

import lombok.Setter;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HostingController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;

	@Setter(onMethod_ = { @Autowired })
	private BoardTagService boardTagService;

	@Setter(onMethod_ = { @Autowired })
	private BoardViewService boardViewService;
	
	@Setter(onMethod_ = @Autowired)
	private CommentService commentService;
	
	@Setter(onMethod_ = @Autowired)
	private CommentLikeService commentLikeService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model m, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		
		// 게시글 리스트
		List<BoardVO> boardList = new ArrayList<>();
		boardList = boardService.getListBestBoard();
		
		// 게시글 태그, 댓글 리스트, 시간 정보
		TimeFmtDTO tfd = new TimeFmtDTO();
		List<String> boardUDateList = new ArrayList<>();
		List<CommentVO> commentListTemp = new ArrayList<CommentVO>();
		for (BoardVO bL : boardList) {
			bL.setBoardTag(boardTagService.get(bL.getBoardId()));
			commentListTemp = commentService.getListBoardBest(bL.getBoardId());
			if (!commentListTemp.isEmpty()) {
				for (CommentVO cL : commentListTemp) {
					if (memberS != null) {
						cL.setLiked(commentLikeService.confirmMemberIdANDCommentId(memberS.getMemberId(), cL.getCommentId()));
					}
					cL.setUDateStr(tfd.getTimeFmtSetDate(cL.getUDate()));
				}
				bL.setCommentList(commentListTemp);
			}
			boardUDateList.add(tfd.getTimeFmtSetDate(bL.getUDate()));
		}
		m.addAttribute("boardList",boardList);
		m.addAttribute("boardUDateList",boardUDateList);
		return "home/hosting";
	}
	
	@RequestMapping(value = "/portfolio", method = RequestMethod.GET)
	public String homeInfo() {
		return "home/homepage_info_for_portfolio";
	}
}
