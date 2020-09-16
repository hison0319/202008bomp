package com.bomp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.BoardMarkVO;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.BoardMarkMapper;
import com.bomp.persistence.BoardMapper;
import com.bomp.persistence.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardMarkServiceImpl implements BoardMarkService{

	@Setter(onMethod_ = @Autowired)
	private BoardMarkMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AlertMapper alertMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Transactional
	@Override
	public boolean register(BoardMarkVO boardMark) {
		if(mapper.getCountBoardIdWithMemberId(boardMark.getMemberId(), boardMark.getBoardId())!=0) {
			return false;
		} else {
			mapper.insert(boardMark);
			return true;
		}
	}

	@Override
	public BoardMarkVO get(int boardMarkId) {
		return mapper.read(boardMarkId);
	}

	@Override
	public boolean modify(BoardMarkVO boardMark) {
		return mapper.update(boardMark) == 1;
	}

	@Transactional
	@Override
	public boolean remove(int boardMarkId){
		BoardMarkVO boardMark = mapper.read(boardMarkId);
		if(mapper.getCountBoardIdWithMemberId(boardMark.getMemberId(), boardMark.getBoardId())!=1) {
			return false;
		} else {
			mapper.delete(boardMarkId);
			return true;
		}
	}

	@Override
	public List<BoardMarkVO> getList() {
		return mapper.getList();
	}

	@Override
	public boolean confirmMemberIdANDBoardId(int memberId, int boardId) {
		return (mapper.readMemberIdANDBoardId(memberId, boardId) != null);
	}

	@Override
	public BoardMarkVO getMemberIdANDBoardId(int memberId, int boardId) {
		return mapper.readMemberIdANDBoardId(memberId, boardId);
	}

}
