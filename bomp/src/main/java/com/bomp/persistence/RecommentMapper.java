package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.RecommentVO;

public interface RecommentMapper {
public List<RecommentVO> getList();
	
	public void insert(RecommentVO recomment);
	
	public List<RecommentVO> getListLimitWithCommentIdJoinMemberNick(@Param("first")int first, @Param("amount")int amount, @Param("commentId")int commentId);
	
	public void insertSelectKey(RecommentVO recomment);
	
	public RecommentVO read(int recommentId);
	
	public int delete(int recommentId);
	
	public int update(RecommentVO recomment);
	
	public int updateLikeCntPlus(int recommentId);
	
	public int updateLikeCntMinus(int recommentId);
}
