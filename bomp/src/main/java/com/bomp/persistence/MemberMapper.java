package com.bomp.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bomp.domain.MemberVO;

public interface MemberMapper {
	public List<MemberVO> getList();
	
	public void insert(MemberVO member);
	
	public void insertSelectKey(MemberVO member);
	
	public MemberVO read(int memberId);
	
	public MemberVO readWithKakao(@Param("memberKakao")String memberKakao);
	
	public int checkMemberNick(@Param("memberNick")String memberNick);
	
	public int delete(int memberId);
	
	public int update(MemberVO member);
	
	public int updateMemberNick(MemberVO member);
	
	public int updateAlertPlus(int memberId);
	
	public int updateAlertMinus(int memberId);
}
