package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.BoardMarkVO;

public interface BoardMarkMapper {
	public List<BoardMarkVO> getList();
	
	public void insert(BoardMarkVO boardMark);
	
	public void insertSelectKey(BoardMarkVO boardMark);
	
	public BoardMarkVO read(int boardMarkId);
	
	public BoardMarkVO readMemberIdANDBoardId(@Param("memberId")int memberId, @Param("boardId")int boardId);
	
	public int getCountBoardIdWithMemberId(@Param("memberId")int memberId, @Param("boardId")int boardId);
	
	public int delete(int boardMarkId);
	
	public int update(BoardMarkVO boardMark);
}
