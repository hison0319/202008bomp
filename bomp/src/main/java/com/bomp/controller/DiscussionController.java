package com.bomp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bomp.domain.BoardTagVO;
import com.bomp.domain.BoardVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MemberVO;
import com.bomp.domain.PageDTO;
import com.bomp.domain.TimeFmtDTO;
import com.bomp.service.BoardLikeService;
import com.bomp.service.BoardMarkService;
import com.bomp.service.CommentLikeService;
import com.bomp.service.BoardService;
import com.bomp.service.BoardTagService;
import com.bomp.service.CommentService;

import lombok.Setter;

@Controller
@RequestMapping("/discussion")
public class DiscussionController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;

	@Setter(onMethod_ = { @Autowired })
	private BoardTagService boardTagService;

	@Setter(onMethod_ = { @Autowired })
	private CommentService commentService;

	@Setter(onMethod_ = { @Autowired })
	private BoardLikeService boardLikeService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardMarkService boardMarkService;

	@Setter(onMethod_ = { @Autowired })
	private CommentLikeService commentLikeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String discussionList(String pageNumStr, Model m) {
		if (pageNumStr == null || pageNumStr.equals("")) {
			pageNumStr = "1";
		}
		int pageNum = Integer.parseInt(pageNumStr);
		Criteria cri = new Criteria(pageNum, 5);
		// 리스트 출력
		List<BoardVO> noticeList = boardService.getListLimitAndCategoryWithMemberNick(cri, "공지사항");
		List<BoardVO> discussList = boardService.getListLimitAndCategoryWithMemberNick(cri, "토론방");
		m.addAttribute("noticeList", noticeList);
		m.addAttribute("discussList", discussList);
		// 시간 형식 설정
		TimeFmtDTO tfd = new TimeFmtDTO();
		List<String> nUDateList = new ArrayList<>();
		for (BoardVO nL : noticeList) {
			nUDateList.add(tfd.getTimeFmtSetDate(nL.getUDate()));
		}
		m.addAttribute("nUDateList", nUDateList);
		List<String> uDateList = new ArrayList<>();
		for (BoardVO dL : discussList) {
			uDateList.add(tfd.getTimeFmtSetDate(dL.getUDate()));
		}
		m.addAttribute("uDateList", uDateList);
		// 페이지네이션
		PageDTO pageMaker = new PageDTO(cri, boardService.getCountWithCategory("토론방"), pageNum);
		m.addAttribute("pageMaker", pageMaker);
		m.addAttribute("pageNum", pageNum);

		return "board/discussion_list";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String discussionShow(Model m, int boardId, String pageNum, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		// 게시물
		BoardVO discussion = boardService.get(boardId);
		m.addAttribute("discussion", discussion);
		// 페이지 정보
		BoardTagVO boardTag = boardTagService.get(boardId);
		m.addAttribute("boardTag", boardTag);
		m.addAttribute("memberNick", discussion.getMemberNick());
		m.addAttribute("pageNum", pageNum);
		// 게시물 좋아요 정보
		if (memberS != null) {
			discussion.setLiked(boardLikeService.confirmMemberIdANDBoardId(memberS.getMemberId(), boardId));
		}
		// 게시물 북마크 정보
		if (memberS != null) {
			discussion.setMarked(boardMarkService.confirmMemberIdANDBoardId(memberS.getMemberId(), boardId));
		}
		// 댓글 리스트
		List<CommentVO> commentList = commentService.getListBoardBest(boardId);
		m.addAttribute("commentList", commentList);
		// 댓글 좋아요 리스트
		if (memberS != null) {
			for (CommentVO cL : commentList) {
				cL.setLiked(commentLikeService.confirmMemberIdANDCommentId(memberS.getMemberId(), cL.getCommentId()));
			}
		}
		// 시간 정보
		TimeFmtDTO tfd = new TimeFmtDTO();
		m.addAttribute("uDate", tfd.getTimeFmtSetDate(discussion.getUDate()));
		List<String> commentUDateList = new ArrayList<>();
		for (CommentVO cL : commentList) {
			commentUDateList.add(tfd.getTimeFmtSetDate(cL.getUDate()));
		}
		m.addAttribute("commentUDateList", commentUDateList);

		return "board/discussion";
	}
}
