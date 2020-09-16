package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.MemberTagVO;

public interface MemberTagMapper {
	public List<MemberTagVO> getList();

	public List<MemberTagVO> getListWithMemberId(@Param("memberId")int memberId);
	
	public void insert(MemberTagVO memberTag);
	
	public void insertSelectKey(MemberTagVO memberTag);
	
	public MemberTagVO read(int memberTagId);
	
	public int delete(int memberTagId);
	
	public void deleteWithMemberId(@Param("memberId")int memberId);
	
	public int update(MemberTagVO memberTag);
}
