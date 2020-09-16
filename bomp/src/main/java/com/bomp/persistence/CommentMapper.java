package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.CommentAndBoardDto;
import com.bomp.domain.CommentVO;

public interface CommentMapper {
	public List<CommentVO> getList();
	
	public List<CommentVO> getListBoardBest(@Param("boardId")int boardId);
	
	public List<CommentVO> getListLimitWithBoradIdPopular(@Param("first")int first, @Param("amount")int amount, @Param("boardId")int boardId);
	
	public List<CommentVO> getListLimitWithBoradIdUDate(@Param("first")int first, @Param("amount")int amount, @Param("boardId")int boardId);

	public void insert(CommentVO comment);
	
	public void insertSelectKey(CommentVO comment);
	
	public CommentVO read(int commentId);
	
	public int getCommentIdByRecommentId(@Param("recommentId")int recommentId);
	
	public CommentAndBoardDto confirmBestComment(int commentId);
	
	public int delete(int commentId);
	
	public int update(CommentVO comment);
	
	public int updateRecommentCntPlus(int commentId);
	
	public int updateRecommentCntMinus(int commentId);
	
	public int updateLikeCntPlus(int commentId);
	
	public int updateLikeCntMinus(int commentId);
}
