package com.bomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.RecommentLikeVO;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.RecommentLikeMapper;
import com.bomp.persistence.RecommentMapper;
import com.bomp.persistence.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class RecommentLikeServiceImpl implements RecommentLikeService{

	@Setter(onMethod_ = @Autowired)
	private RecommentLikeMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private RecommentMapper recommentMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AlertMapper alertMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Transactional
	@Override
	public boolean register(RecommentLikeVO recommentLike, AlertVO alert) {
		if(mapper.getCountRecommentIdWithMemberId(recommentLike.getMemberId(), recommentLike.getRecommentId())!=0) {
			return false;
		} else {
			mapper.insertSelectKey(recommentLike);
			recommentMapper.updateLikeCntPlus(recommentLike.getRecommentId());
			alertMapper.insert(alert);
			memberMapper.updateAlertPlus(alert.getAlertMemberId());
			return true;
		}
	}

	@Override
	public RecommentLikeVO get(int recommentLikeId) {
		return mapper.read(recommentLikeId);
	}

	@Override
	public boolean modify(RecommentLikeVO recommentLike) {
		return mapper.update(recommentLike) == 1;
	}

	@Override
	public boolean remove(int recommentLikeId) throws Exception {
		RecommentLikeVO recommentLike = mapper.read(recommentLikeId);
		if(mapper.getCountRecommentIdWithMemberId(recommentLike.getMemberId(), recommentLike.getRecommentId())!=1) {
			return false;
		} else {
			if(mapper.delete(recommentLikeId) == 1) {
				recommentMapper.updateLikeCntMinus(recommentLike.getRecommentId());
				return true;
			} else {
				throw new Exception();			
			}
		}
	}

	@Override
	public List<RecommentLikeVO> getList() {
		return mapper.getList();
	}

	@Override
	public boolean confirmMemberIdANDRecommentId(int memberId, int recommentId) {
		return (mapper.readMemberIdANDRecommentId(memberId, recommentId) != null);
	}

	@Override
	public RecommentLikeVO getMemberIdANDRecommentId(int memberId, int recommentId) {
		return mapper.readMemberIdANDRecommentId(memberId, recommentId);
	}

}
