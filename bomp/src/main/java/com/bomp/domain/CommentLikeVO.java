package com.bomp.domain;

import lombok.Data;

@Data
public class CommentLikeVO {
	private int commentLikeId;
	private int memberId;
	private int commentId;
	
	public CommentLikeVO(int commentId, int memberId) {
		this.commentId = commentId;
		this.memberId = memberId;
	}
}
