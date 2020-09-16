package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.RecommentLikeVO;

public interface RecommentLikeMapper {
public List<RecommentLikeVO> getList();
	
	public void insert(RecommentLikeVO recommentLike);
	
	public void insertSelectKey(RecommentLikeVO recommentLike);
	
	public RecommentLikeVO read(int recommentLikeId);
	
	public RecommentLikeVO readMemberIdANDRecommentId(@Param("memberId")int memberId, @Param("recommentId")int recommentId);
	
	public int getCountRecommentIdWithMemberId(@Param("memberId")int memberId, @Param("recommentId")int recommentId);
	
	public int delete(int recommentLikeId);
	
	public int update(RecommentLikeVO recommentLike);
}
