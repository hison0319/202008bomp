package com.bomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomp.domain.AlertVO;
import com.bomp.domain.CommentAndBoardDto;
import com.bomp.domain.CommentLikeVO;
import com.bomp.persistence.AlertMapper;
import com.bomp.persistence.BoardMapper;
import com.bomp.persistence.CommentLikeMapper;
import com.bomp.persistence.CommentMapper;
import com.bomp.persistence.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService{

	@Setter(onMethod_ = @Autowired)
	private CommentLikeMapper mapper;
		
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
	public boolean register(CommentLikeVO commentLike, AlertVO alert) {
		if(mapper.getCountCommentIdWithMemberId(commentLike.getMemberId(), commentLike.getCommentId())!=0) {
			return false;
		} else {
			mapper.insertSelectKey(commentLike);
			CommentAndBoardDto coAndBo = commentMapper.confirmBestComment(commentLike.getCommentId());
			if(coAndBo.getBestCommentLikeCnt() == coAndBo.getLikeCnt()) {
				boardMapper.updateBestCommentLikeCntPlus(coAndBo.getBoardId());
			}
			commentMapper.updateLikeCntPlus(commentLike.getCommentId());
			alertMapper.insert(alert);
			memberMapper.updateAlertPlus(alert.getAlertMemberId());
			return true;
		}
	}

	@Override
	public CommentLikeVO get(int commentLikeId) {
		return mapper.read(commentLikeId);
	}

	@Override
	public boolean modify(CommentLikeVO commentLike) {
		return mapper.update(commentLike) == 1;
	}

	@Override
	public boolean remove(int commentLikeId) throws Exception {
		CommentLikeVO commentLike = mapper.read(commentLikeId);
		if(mapper.getCountCommentIdWithMemberId(commentLike.getMemberId(), commentLike.getCommentId())!=1) {
			return false;
		} else {
			if(mapper.delete(commentLikeId) == 1) {
				CommentAndBoardDto coAndBo = commentMapper.confirmBestComment(commentLike.getCommentId());
				if(coAndBo.getBestCommentLikeCnt() == coAndBo.getLikeCnt()) {
					boardMapper.updateBestCommentLikeCntMinus(coAndBo.getBoardId());
				}
				commentMapper.updateLikeCntMinus(commentLike.getCommentId());
				return true;
			} else {
				throw new Exception();			
			}
		}
	}

	@Override
	public List<CommentLikeVO> getList() {
		return mapper.getList();
	}

	@Override
	public boolean confirmMemberIdANDCommentId(int memberId, int commentId) {
		return (mapper.readMemberIdANDCommentId(memberId, commentId) != null);
	}

	@Override
	public CommentLikeVO getMemberIdANDCommentId(int memberId, int commentId) {
		return mapper.readMemberIdANDCommentId(memberId, commentId);
	}

}
