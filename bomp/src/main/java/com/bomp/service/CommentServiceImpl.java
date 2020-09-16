package com.bomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.CommentVO;
import com.bomp.domain.Criteria;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.BoardMapper;
import com.bomp.persistence.CommentMapper;
import com.bomp.persistence.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

	@Setter(onMethod_ = @Autowired)
	private CommentMapper commentMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AlertMapper alertMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Transactional
	@Override
	public void register(CommentVO comment, AlertVO alert) {
		commentMapper.insertSelectKey(comment);
		boardMapper.updateCommentCntPlus(comment.getBoardId());
		alertMapper.insert(alert);
		memberMapper.updateAlertPlus(alert.getAlertMemberId());
	}

	@Override
	public CommentVO get(int commentId) {
		return commentMapper.read(commentId);
	}

	@Override
	public boolean modify(CommentVO comment) {
		return commentMapper.update(comment) == 1;
	}

	@Transactional
	@Override
	public boolean remove(int commentId, int boardId) throws Exception {
		if(commentMapper.delete(commentId) == 1) {
			boardMapper.updateCommentCntMinus(boardId);
			return true;
		} else {
			throw new Exception();			
		}
	}

	@Override
	public List<CommentVO> getList() {
		return commentMapper.getList();
	}
	
	@Override
	public List<CommentVO> getListBoardBest(int boardId) {
		return commentMapper.getListBoardBest(boardId);
	}

	@Override
	public List<CommentVO> getListLimitWithBoradIdPopular(Criteria cri, int boradId) {
		return commentMapper.getListLimitWithBoradIdPopular(cri.getFirst(), cri.getAmount(), boradId);
	}
	
	@Override
	public List<CommentVO> getListLimitWithBoradIdUDate(Criteria cri, int boradId) {
		return commentMapper.getListLimitWithBoradIdUDate(cri.getFirst(), cri.getAmount(), boradId);
	}

	@Override
	public int getCommentIdByRecommentId(int recommentId) {
		return commentMapper.getCommentIdByRecommentId(recommentId);
	}

}
