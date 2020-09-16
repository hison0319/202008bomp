package com.bomp.service;

import java.util.List;

import com.bomp.domain.BoardTagVO;

public interface BoardTagService {
	public void register(BoardTagVO boardTag);
	
	public BoardTagVO get(int boardId);
	
	public boolean modify(BoardTagVO boardTag);
	
	public boolean remove(int boardTagId);
	
	public List<BoardTagVO> getList();
}
