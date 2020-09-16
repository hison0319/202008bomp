package com.bomp.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bomp.domain.MemberVO;
import com.bomp.persistence.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Override
	public void register(MemberVO member) {
		mapper.insertSelectKey(member);
	}

	@Override
	public MemberVO get(int memberId) {
		return mapper.read(memberId);
	}

	@Override
	public boolean modify(MemberVO member) {
		return mapper.update(member) == 1;
	}

	@Override
	public boolean remove(int memberId) {
		return mapper.delete(memberId) == 1;
	}

	@Override
	public List<MemberVO> getList() {
		return mapper.getList();
	}

	@Override
	public MemberVO getWithKakao(String memberKakao) {
		return mapper.readWithKakao(memberKakao);
	}

	@Override
	public boolean checkMemberNick(String memberNick) {
		return mapper.checkMemberNick(memberNick) == 0; //중복 아닐 때 true
	}

	@Override
	public boolean modifyMemberNick(MemberVO member) {
		return mapper.updateMemberNick(member) ==1;
	}

}
