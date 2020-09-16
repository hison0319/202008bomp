package com.bomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.Criteria;
import com.bomp.domain.RecommentVO;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.CommentMapper;
import com.bomp.persistence.MemberMapper;
import com.bomp.persistence.RecommentMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class RecommentServiceImpl implements RecommentService{

	@Setter(onMethod_ = @Autowired)
	private RecommentMapper recommentMapper;
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper commentMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AlertMapper alertMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Transactional
	@Override
	public void register(RecommentVO recomment, AlertVO alert) {
		recommentMapper.insertSelectKey(recomment);
		commentMapper.updateRecommentCntPlus(recomment.getCommentId());
		alertMapper.insert(alert);
		memberMapper.updateAlertPlus(alert.getAlertMemberId());	
	}

	@Override
	public RecommentVO get(int recommentId) {
		return recommentMapper.read(recommentId);
	}

	@Override
	public boolean modify(RecommentVO recomment) {
		return recommentMapper.update(recomment) == 1;
	}
	
	@Transactional
	@Override
	public boolean remove(int recommentId, int commentId) throws Exception {
		if(recommentMapper.delete(recommentId) == 1) {
			commentMapper.updateRecommentCntMinus(commentId);
			return true;
		} else {
			throw new Exception();			
		}
	}

	@Override
	public List<RecommentVO> getList() {
		return recommentMapper.getList();
	}

	@Override
	public List<RecommentVO> getListLimitWithCommentIdJoinMemberNick(Criteria cri, int commentId) {
		return recommentMapper.getListLimitWithCommentIdJoinMemberNick(cri.getFirst(), cri.getAmount(), commentId);
	}

}
