package com.bomp.controller;

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
import com.bomp.domain.BoardLikeVO;
import com.bomp.domain.BoardVO;
import com.bomp.domain.CommentLikeVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.MemberVO;
import com.bomp.domain.RecommentLikeVO;
import com.bomp.domain.RecommentVO;
import com.bomp.service.AlertService;
import com.bomp.service.BoardLikeService;
import com.bomp.service.BoardService;
import com.bomp.service.CommentLikeService;
import com.bomp.service.CommentService;
import com.bomp.service.RecommentLikeService;
import com.bomp.service.RecommentService;

import lombok.Setter;

@Controller
@RequestMapping("/like")
public class LikeController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardLikeService boardLikeService;
	
	@Setter(onMethod_ = { @Autowired })
	private CommentService commentService;
	
	@Setter(onMethod_ = { @Autowired })
	private CommentLikeService commentLikeService;
	
	@Setter(onMethod_ = { @Autowired })
	private RecommentService recommentService;
	
	@Setter(onMethod_ = { @Autowired })
	private RecommentLikeService recommentLikeService;
	
	@Setter(onMethod_ = { @Autowired })
	private AlertService alertService;
	
	@RequestMapping(value = "/board", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String boardLike(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int boardId = Integer.parseInt((String) parameters.get("boardId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
	    boolean liked = parameters.get("liked").equals("true");
	    if(!liked) {
	    	//좋아요가 아닌 상태 -> 좋아요
	    	BoardLikeVO boardLike =new BoardLikeVO(boardId, memberId);
	    	BoardVO board = boardService.get(boardId);
	    	AlertVO alert = alertService.alertRegSet(memberId, board.getTitle(), board.getMemberId(), board.getBoardId(), 5);
	    		    	
	    	try {
	    		boardLikeService.register(boardLike, alert);
	    		return "1";
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return "0";
	    	}
	    } else {
	    	//좋아요인 상태 -> 좋아요 취소
	    	try {
				boardLikeService.remove(boardLikeService.getMemberIdANDBoardId(memberId, boardId).getBoardLikeId());
				return "2";
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
	    }
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String commentLike(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int commentId = Integer.parseInt((String) parameters.get("commentId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
	    boolean liked = parameters.get("liked").equals("true");
	    if(!liked) {
	    	//좋아요가 아닌 상태 -> 좋아요
	    	CommentLikeVO commentLike =new CommentLikeVO(commentId, memberId);
	    	CommentVO comment = commentService.get(commentId);
	    	AlertVO alert = alertService.alertRegSet(memberId, comment.getCommentText(), comment.getMemberId(), comment.getCommentId(), 6);
	    	
	    	try {
	    		commentLikeService.register(commentLike, alert);
	    		return "1";
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return "0";
	    	}
	    } else {
	    	//좋아요인 상태 -> 좋아요 취소
	    	try {
	    		commentLikeService.remove(commentLikeService.getMemberIdANDCommentId(memberId, commentId).getCommentLikeId());
				return "2";
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
	    }
	}
	
	@RequestMapping(value = "/recomment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String recommentLike(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int recommentId = Integer.parseInt((String) parameters.get("recommentId"));
	    int memberId = Integer.parseInt((String) parameters.get("memberId"));
	    boolean liked = parameters.get("liked").equals("true");
	    if(!liked) {
	    	//좋아요가 아닌 상태 -> 좋아요
	    	RecommentLikeVO recommentLike =new RecommentLikeVO(recommentId, memberId);
	    	RecommentVO recomment = recommentService.get(recommentId);
	    	AlertVO alert = alertService.alertRegSet(memberId, recomment.getRecommentText(), recomment.getMemberId(), recomment.getRecommentId(), 7);
	    	
	    	try {
	    		recommentLikeService.register(recommentLike, alert);
	    		return "1";
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return "0";
	    	}
	    } else {
	    	//좋아요인 상태 -> 좋아요 취소
	    	try {
	    		recommentLikeService.remove(recommentLikeService.getMemberIdANDRecommentId(memberId, recommentId).getRecommentLikeId());
				return "2";
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
	    }
	}
}
