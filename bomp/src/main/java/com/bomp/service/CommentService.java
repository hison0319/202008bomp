package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.Criteria;

public interface CommentService {
	public void register(CommentVO comment, AlertVO alert);
	
	public CommentVO get(int commentId);
	
	public int getCommentIdByRecommentId(int recommentId);

	public boolean modify(CommentVO comment);
	
	public boolean remove(int commentId, int boardId) throws Exception;
	
	public List<CommentVO> getList();
	
	public List<CommentVO> getListBoardBest(int boardId);
	
	public List<CommentVO> getListLimitWithBoradIdPopular(Criteria cri, int boradId);

	public List<CommentVO> getListLimitWithBoradIdUDate(Criteria cri, int boradId);
}
