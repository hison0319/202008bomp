package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.CommentLikeVO;

public interface CommentLikeMapper {
public List<CommentLikeVO> getList();
	
	public void insert(CommentLikeVO commentLike);
	
	public void insertSelectKey(CommentLikeVO commentLike);
	
	public CommentLikeVO read(int commentLikeId);
	
	public CommentLikeVO readMemberIdANDCommentId(@Param("memberId")int memberId, @Param("commentId")int commentId);
	
	public int getCountCommentIdWithMemberId(@Param("memberId")int memberId, @Param("commentId")int commentId);
	
	public int delete(int commentLikeId);
	
	public int update(CommentLikeVO commentLike);
}
