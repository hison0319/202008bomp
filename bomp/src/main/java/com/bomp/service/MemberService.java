package com.bomp.service;

import java.util.List;

import com.bomp.domain.MemberVO;

public interface MemberService {
	public void register(MemberVO member);
	
	public MemberVO get(int memberId);
	
	public MemberVO getWithKakao(String memberKakao);
	
	public boolean checkMemberNick(String memberNick);
	
	public boolean modify(MemberVO member);
	
	public boolean modifyMemberNick(MemberVO member);
	
	public boolean remove(int memberId);
	
	public List<MemberVO> getList();
}
