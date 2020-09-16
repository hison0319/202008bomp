package com.bomp.domain;

import lombok.Data;

@Data
public class BoardMarkVO {
	private int boardMarkId;
	private int memberId;
	private int boardId;
	
	public BoardMarkVO(int boardId, int memberId) {
		this.boardId = boardId;
		this.memberId = memberId;
	}
}
