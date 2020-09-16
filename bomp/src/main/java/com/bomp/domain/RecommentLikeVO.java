package com.bomp.domain;

import lombok.Data;

@Data
public class RecommentLikeVO {
	private int recommentLikeId;
	private int memberId;
	private int recommentId;
	
	public RecommentLikeVO(int recommentId, int memberId) {
		this.recommentId = recommentId;
		this.memberId = memberId;
	}
}
