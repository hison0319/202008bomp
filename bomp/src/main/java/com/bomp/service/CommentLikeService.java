package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.CommentLikeVO;

public interface CommentLikeService {
	public boolean register(CommentLikeVO commentLike, AlertVO alert);
	
	public CommentLikeVO get(int commentLikeId);
	
	public CommentLikeVO getMemberIdANDCommentId(int memberId, int commentId);
	
	public boolean confirmMemberIdANDCommentId(int memberId, int commentId);
	
	public boolean modify(CommentLikeVO commentLike);
	
	public boolean remove(int commentLikeId) throws Exception;
	
	public List<CommentLikeVO> getList();
}
