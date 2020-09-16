package com.bomp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentVO {
	private int commentId;
	private int boardId;
	private String commentText;
	private int memberId;
	private Timestamp uDate;
	private int likeCnt;
	private int recommentCnt;
	private String memberNick;
	private boolean liked;
	private String uDateStr;
}
