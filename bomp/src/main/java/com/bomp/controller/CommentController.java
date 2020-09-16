package com.bomp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bomp.domain.AlertVO;
import com.bomp.domain.BoardTagVO;
import com.bomp.domain.BoardVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.MemberVO;
import com.bomp.domain.RecommentVO;
import com.bomp.domain.TimeFmtDTO;
import com.bomp.service.AlertService;
import com.bomp.service.BoardLikeService;
import com.bomp.service.BoardMarkService;
import com.bomp.service.BoardService;
import com.bomp.service.BoardTagService;
import com.bomp.service.CommentLikeService;
import com.bomp.service.CommentService;
import com.bomp.service.RecommentLikeService;
import com.bomp.service.RecommentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.Setter;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Setter(onMethod_ = { @Autowired })
	private BoardService boardService;

	@Setter(onMethod_ = { @Autowired })
	private BoardTagService boardTagService;

	@Setter(onMethod_ = { @Autowired })
	private CommentService commentService;

	@Setter(onMethod_ = { @Autowired })
	private RecommentService recommentService;

	@Setter(onMethod_ = { @Autowired })
	private BoardLikeService boardLikeService;

	@Setter(onMethod_ = { @Autowired })
	private CommentLikeService commentLikeService;
	
	@Setter(onMethod_ = { @Autowired })
	private RecommentLikeService recommentLikeService;
	
	@Setter(onMethod_ = { @Autowired })
	private BoardMarkService boardMarkService;
	
	@Setter(onMethod_ = { @Autowired })
	private AlertService alertService;

	@RequestMapping(value = "/recommentFromAlertList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String recommentFromAlert(Model m, int recommentId, HttpSession session) {
		int commentId = commentService.getCommentIdByRecommentId(recommentId);
		int boardId = boardService.getBoardIdByCommentId(commentId);
		boardShow(m, boardId, "p", session);
		return "comment/comment";
	}
	
	@RequestMapping(value = "/commentFromAlertList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String commentFromAlert(Model m, int commentId, HttpSession session) {
		int boardId = boardService.getBoardIdByCommentId(commentId);
		boardShow(m, boardId, "p", session);
		return "comment/comment";
	}
	
	@RequestMapping(value = "/boardFromAlertList", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String boardFromAlert(Model m, int boardId, HttpSession session) {
		boardShow(m, boardId, "p", session);
		return "comment/comment";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String boardShow(Model m, int boardId, String arrMethod, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		if(arrMethod == null || arrMethod.equals("")) {
			arrMethod = "p";
		}
		m.addAttribute("arrMethod",arrMethod);
		// 게시판 정보
		BoardVO board = boardService.get(boardId);
		m.addAttribute("board", board);
		m.addAttribute("memberNick", board.getMemberNick());
		// 게시판 태그 정보
		BoardTagVO boardTag = boardTagService.get(boardId);
		m.addAttribute("boardTag", boardTag);
		// 게시물 좋아요 정보
		if (memberS != null) {
			board.setLiked(boardLikeService.confirmMemberIdANDBoardId(memberS.getMemberId(), boardId));
		}
		// 게시물 북마크 정보
		if (memberS != null) {
			board.setMarked(boardMarkService.confirmMemberIdANDBoardId(memberS.getMemberId(), boardId));
		}
		// 시간 정보
		TimeFmtDTO tfd = new TimeFmtDTO();
		m.addAttribute("boardUDate", tfd.getTimeFmtSetDate(board.getUDate()));
		return "comment/comment";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String commentList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		int boardId = Integer.parseInt((String) parameters.get("boardId"));
		int pageNum = Integer.parseInt((String) parameters.get("commentPage"));
		String arrMethod = (parameters.get("arrMethod")!="")?(String)parameters.get("arrMethod"):"p";
		arrMethod = arrMethod==null?"p":arrMethod;
		
		// 댓글 리스트
		Criteria cri = new Criteria(pageNum, 10);
		List<CommentVO> commentList;
		//
		if(arrMethod.equals("p")) {
			commentList = commentService.getListLimitWithBoradIdPopular(cri, boardId);
		} else {
			commentList = commentService.getListLimitWithBoradIdUDate(cri, boardId);
		}
		//
		Map<String, Object> retData = new HashMap<String, Object>();
		retData.put("code", "OK");
		retData.put("commentList", commentList);
		// 댓글 좋아요 리스트
		if (memberS != null) {
			for (CommentVO cL : commentList) {
				cL.setLiked(commentLikeService.confirmMemberIdANDCommentId(memberS.getMemberId(), cL.getCommentId()));
			}
		}
		// 시간 정보
		TimeFmtDTO tfd = new TimeFmtDTO();
		List<String> commentUDateList = new ArrayList<>();
		for (CommentVO cL : commentList) {
			commentUDateList.add(tfd.getTimeFmtSetDate(cL.getUDate()));
		}
		retData.put("commentUDateList", commentUDateList);
		retData.put("memberId", (memberS != null)?memberS.getMemberId():0);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(retData);
		
		return jsonStr;
	}

	@RequestMapping(value = "/registComment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String commentRegist(Model m, CommentVO comment, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		BoardVO board = boardService.get(comment.getBoardId());
		AlertVO alert = alertService.alertRegSet(comment.getMemberId(), board.getTitle(), board.getMemberId(), comment.getBoardId(), 3);

		commentService.register(comment, alert);
		m.addAttribute("boardId", comment.getBoardId());
		return "complete/comp_regist_comment";
	}

	@RequestMapping(value = "/removeComment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String commentRemove(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int commentId = Integer.parseInt((String) parameters.get("commentId"));
		int boardId = Integer.parseInt((String) parameters.get("boardId"));
		try {
			commentService.remove(commentId, boardId);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	@RequestMapping(value = "/registRecomment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String recommentRegist(Model m, @RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int commentId = Integer.parseInt((String) parameters.get("commentId"));
		int memberId = Integer.parseInt((String) parameters.get("memberId"));
		String commentText = (String) parameters.get("commentText");
		RecommentVO recomment = new RecommentVO(commentId, memberId, commentText);
		CommentVO comment = commentService.get(commentId);
		AlertVO alert = alertService.alertRegSet(memberId, comment.getCommentText(), comment.getMemberId(), comment.getCommentId(), 4);

		try {
			recommentService.register(recomment, alert);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	@RequestMapping(value = "/removeRecomment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String recommentRemove(@RequestParam Map<String, Object> parameters, HttpSession session) {
		if (session.getAttribute("member") == null) {
			return "/error/login_error";
		}
		int recommentId = Integer.parseInt((String) parameters.get("recommentId"));
		int commentId = Integer.parseInt((String) parameters.get("commentId"));
		try {
			recommentService.remove(recommentId, commentId);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	@RequestMapping(value = "/listRecomment", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getRecommentList(@RequestParam Map<String, Object> parameters, HttpSession session) {
		MemberVO memberS = (MemberVO) session.getAttribute("member");
		
		int commentId = Integer.parseInt((String) parameters.get("commentId"));
		int recommentPage = Integer.parseInt((String) parameters.get("recommentPage"));
		Criteria cri = new Criteria(recommentPage, 10);
		List<RecommentVO> recommentList = recommentService.getListLimitWithCommentIdJoinMemberNick(cri, commentId);
		Map<String, Object> retData = new HashMap<String, Object>();
		retData.put("code", "OK");
		// 답글 좋아요 리스트
		if (memberS != null) {
			for (RecommentVO rL : recommentList) {
				rL.setLiked(recommentLikeService.confirmMemberIdANDRecommentId(memberS.getMemberId(), rL.getRecommentId()));
			}
		}		
		// 답글 정보
		retData.put("recommentList", recommentList);
		// 시간 정보
		TimeFmtDTO tfd = new TimeFmtDTO();
		List<String> recommentUDateList = new ArrayList<>();
		for (RecommentVO rL : recommentList) {
			recommentUDateList.add(tfd.getTimeFmtSetDate(rL.getUDate()));
		}
		retData.put("recommentUDateList", recommentUDateList);
		retData.put("memberId", (memberS != null)?memberS.getMemberId():0);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(retData);

		return jsonStr;
	}
}
