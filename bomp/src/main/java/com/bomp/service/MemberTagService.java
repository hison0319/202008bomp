package com.bomp.service;

import java.util.List;

import com.bomp.domain.MemberTagVO;

public interface MemberTagService {
	public void register(int memberId, String memberTag);
	
	public MemberTagVO get(int memberTagId);
	
	public boolean modify(MemberTagVO memberTag);
	
	public boolean remove(int memberTagId);
	
	public List<MemberTagVO> getList();
	
	public List<MemberTagVO> getListWithMemberId(int memberId);
}
