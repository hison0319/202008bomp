package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.BoardMarkVO;

public interface BoardMarkService {
	public boolean register(BoardMarkVO boardMark);
	
	public BoardMarkVO get(int boardMarkId);
	
	public BoardMarkVO getMemberIdANDBoardId(int memberId, int boardId);
	
	public boolean confirmMemberIdANDBoardId(int memberId, int boardId);
	
	public boolean modify(BoardMarkVO boardMark);
	
	public boolean remove(int boardMarkId);
	
	public List<BoardMarkVO> getList();
}
