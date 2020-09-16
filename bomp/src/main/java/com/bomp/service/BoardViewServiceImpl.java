package com.bomp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bomp.domain.BoardVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.MemberTagVO;
import com.bomp.domain.TimeFmtDTO;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardViewServiceImpl implements BoardViewService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardLikeService boardLikeService;
	
	@Setter(onMethod_ = @Autowired)
	private MemberTagService memberTagService;
	
	@Setter(onMethod_ = @Autowired)
	private BoardTagService boardTagService;
	
	@Setter(onMethod_ = @Autowired)
	private CommentService commentService;
	
	@Setter(onMethod_ = @Autowired)
	private CommentLikeService commentLikeService;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMarkService boardMarkService;
	
	@Override
	public String boardSetView(List<BoardVO> boardList, int memberSId, String boardListDownEndToggle) {
		Map<String, Object> retData = new HashMap<String, Object>();
		retData.put("code", "OK");
		List<MemberTagVO> memberTagList = new ArrayList<>();
		// 게시글 좋아요 리스트 / 게시물 북마크 정보/ 멤버 태그 리스트
		if (memberSId != 0) {
			for (BoardVO bL : boardList) {
				bL.setLiked(boardLikeService.confirmMemberIdANDBoardId(memberSId, bL.getBoardId()));
				bL.setMarked(boardMarkService.confirmMemberIdANDBoardId(memberSId, bL.getBoardId()));
			}
			memberTagList = memberTagService.getListWithMemberId(memberSId);
		}
		
		// 게시글 태그, 댓글 리스트, 시간 정보
		TimeFmtDTO tfd = new TimeFmtDTO();
		List<String> boardUDateList = new ArrayList<>();
		List<List<String>> commentUDateListArr = new ArrayList<>();
		List<String> commentUDateList = new ArrayList<>();
		List<CommentVO> commentListTemp = new ArrayList<CommentVO>();
		for (BoardVO bL : boardList) {
			bL.setBoardTag(boardTagService.get(bL.getBoardId()));
			commentListTemp = commentService.getListBoardBest(bL.getBoardId());
			if (!commentListTemp.isEmpty()) {
				for (CommentVO cL : commentListTemp) {
					if (memberSId != 0) {
						cL.setLiked(commentLikeService.confirmMemberIdANDCommentId(memberSId, cL.getCommentId()));
					}
					commentUDateList.add(tfd.getTimeFmtSetDate(cL.getUDate()));
				}
				bL.setCommentList(commentListTemp);
			}
			commentUDateListArr.add(commentUDateList);
			boardUDateList.add(tfd.getTimeFmtSetDate(bL.getUDate()));
		}
		retData.put("boardList", boardList);
		retData.put("boardUDateList", boardUDateList);
		retData.put("commentUDateListArr", commentUDateListArr);
		retData.put("boardListDownEndToggle", boardListDownEndToggle);
		retData.put("memberId", memberSId);
		retData.put("memberTagList", memberTagList);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(retData);
		return jsonStr;
	}

}
