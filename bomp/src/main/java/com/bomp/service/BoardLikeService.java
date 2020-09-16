package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.BoardLikeVO;

public interface BoardLikeService {
	public boolean register(BoardLikeVO boardLike, AlertVO alert);
	
	public BoardLikeVO get(int boardLikeId);
	
	public BoardLikeVO getMemberIdANDBoardId(int memberId, int boardId);
	
	public boolean confirmMemberIdANDBoardId(int memberId, int boardId);
	
	public boolean modify(BoardLikeVO boardLike);
	
	public boolean remove(int boardLikeId) throws Exception;
	
	public List<BoardLikeVO> getList();
}
