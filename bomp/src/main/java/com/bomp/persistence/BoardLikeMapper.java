package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.BoardLikeVO;

public interface BoardLikeMapper {
	public List<BoardLikeVO> getList();
	
	public void insert(BoardLikeVO boardLike);
	
	public void insertSelectKey(BoardLikeVO boardLike);
	
	public BoardLikeVO read(int boardLikeId);
	
	public BoardLikeVO readMemberIdANDBoardId(@Param("memberId")int memberId, @Param("boardId")int boardId);
	
	public int getCountBoardIdWithMemberId(@Param("memberId")int memberId, @Param("boardId")int boardId);
	
	public int delete(int boardLikeId);
	
	public int update(BoardLikeVO boardLike);
}
