package com.bomp.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bomp.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getListLimitAndCategoryWithMemberNick(@Param("first")int first, @Param("amount")int amount, @Param("categoryName")String categoryName);

	public List<BoardVO> getListLimitWithMemberNick(@Param("first")int first, @Param("amount")int amount);
	
	public List<BoardVO> getListBestBoard();
	
	public int getCountWithCategory(@Param("categoryName")String categoryName);
	
	public int getCount();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(int boardId);
	
	public BoardVO readOnlyUDate(int boardId);
	
	public int getBoardIdByCommentId(@Param("commentId")int commentId);
	
	public int delete(int boardId);
	
	public int update(BoardVO board);
	
	public int updateCommentCntPlus(int boardId);
	
	public int updateCommentCntMinus(int boardId);
	
	public int updateLikeCntPlus(@Param("boardId")int boardId, @Param("uDate")Timestamp uDate);
	
	public int updateLikeCntMinus(@Param("boardId")int boardId, @Param("uDate")Timestamp uDate);
	
	public int updateBestCommentLikeCntPlus(int boardId);

	public int updateBestCommentLikeCntMinus(int boardId);
}
