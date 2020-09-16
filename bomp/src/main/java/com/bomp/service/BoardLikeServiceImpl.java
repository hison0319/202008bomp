package com.bomp.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.BoardLikeVO;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.BoardLikeMapper;
import com.bomp.persistence.BoardMapper;
import com.bomp.persistence.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService{

	@Setter(onMethod_ = @Autowired)
	private BoardLikeMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AlertMapper alertMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Transactional
	@Override
	public boolean register(BoardLikeVO boardLike, AlertVO alert) {
		if(mapper.getCountBoardIdWithMemberId(boardLike.getMemberId(), boardLike.getBoardId())!=0) {
			return false;
		} else {
			mapper.insertSelectKey(boardLike);
			Timestamp uDate = boardMapper.readOnlyUDate(boardLike.getBoardId()).getUDate();
			boardMapper.updateLikeCntPlus(boardLike.getBoardId(),uDate);
			alertMapper.insert(alert);
			memberMapper.updateAlertPlus(alert.getAlertMemberId());
			return true;
		}
	}

	@Override
	public BoardLikeVO get(int boardLikeId) {
		return mapper.read(boardLikeId);
	}

	@Override
	public boolean modify(BoardLikeVO boardLike) {
		return mapper.update(boardLike) == 1;
	}

	@Transactional
	@Override
	public boolean remove(int boardLikeId) throws Exception {
		BoardLikeVO boardLike = mapper.read(boardLikeId);
		if(mapper.getCountBoardIdWithMemberId(boardLike.getMemberId(), boardLike.getBoardId())!=1) {
			return false;
		} else {
			if(mapper.delete(boardLikeId) == 1) {
				Timestamp uDate = boardMapper.readOnlyUDate(boardLike.getBoardId()).getUDate();
				boardMapper.updateLikeCntMinus(boardLike.getBoardId(),uDate);
				return true;
			} else {
				throw new Exception();			
			}
		}
	}

	@Override
	public List<BoardLikeVO> getList() {
		return mapper.getList();
	}

	@Override
	public boolean confirmMemberIdANDBoardId(int memberId, int boardId) {
		return (mapper.readMemberIdANDBoardId(memberId, boardId) != null);
	}

	@Override
	public BoardLikeVO getMemberIdANDBoardId(int memberId, int boardId) {
		return mapper.readMemberIdANDBoardId(memberId, boardId);
	}

}
