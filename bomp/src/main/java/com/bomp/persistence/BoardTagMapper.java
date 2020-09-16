package com.bomp.persistence;

import java.util.List;

import com.bomp.domain.BoardTagVO;

public interface BoardTagMapper {
	public List<BoardTagVO> getList();
	
	public void insert(BoardTagVO boardTag);
	
	public void insertSelectKey(BoardTagVO boardTag);
	
	public BoardTagVO read(int boardId);
	
	public int delete(int boardTagId);
	
	public int update(BoardTagVO boardTag);
}
