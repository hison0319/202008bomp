package com.bomp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RecommentVO {
	private int recommentId;
	private int commentId;
	private String recommentText;
	private int memberId;
	private Timestamp uDate;
	private int likeCnt;
	private String memberNick;
	private boolean liked;
	
	public RecommentVO(int commentId, int memberId, String recommentText) {
		this.commentId = commentId;
		this.memberId = memberId;
		this.recommentText = recommentText;
	}
}
