package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.RecommentVO;

public interface RecommentService {
	public void register(RecommentVO recomment, AlertVO alert);
	
	public RecommentVO get(int recommentId);
	
	public boolean modify(RecommentVO recomment);
	
	public boolean remove(int recommentId, int commentId) throws Exception ;
	
	public List<RecommentVO> getList();
	
	public List<RecommentVO> getListLimitWithCommentIdJoinMemberNick(Criteria cri, int commentId);
}
