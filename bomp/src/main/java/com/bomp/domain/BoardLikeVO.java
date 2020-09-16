package com.bomp.domain;

import lombok.Data;

@Data
public class BoardLikeVO {
	private int boardLikeId;
	private int boardId;
	private int memberId;
	
	public BoardLikeVO(int boardId, int memberId) {
		this.boardId = boardId;
		this.memberId = memberId;
	}
}
