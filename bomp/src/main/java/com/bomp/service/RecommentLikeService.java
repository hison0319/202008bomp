package com.bomp.service;

import java.util.List;

import com.bomp.domain.AlertVO;
import com.bomp.domain.RecommentLikeVO;

public interface RecommentLikeService {
	public boolean register(RecommentLikeVO recommentLike, AlertVO alert);
	
	public RecommentLikeVO get(int recommentLikeId);
	
	public RecommentLikeVO getMemberIdANDRecommentId(int memberId, int recommentId);
	
	public boolean confirmMemberIdANDRecommentId(int memberId, int recommentId);
	
	public boolean modify(RecommentLikeVO recommentLike);
	
	public boolean remove(int recommentLikeId) throws Exception;
	
	public List<RecommentLikeVO> getList();
}
