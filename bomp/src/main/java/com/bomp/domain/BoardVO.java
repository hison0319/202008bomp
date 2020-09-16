package com.bomp.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	private int boardId;
	private String categoryName;
	private String title;
	private int memberId;
	private String content;
	private String imgAddress;
	private Timestamp regDate;
	private Timestamp uDate;
	private int likeCnt;
	private int commentCnt;
	private String imgAlt;
	private String memberNick;
	private boolean liked;
	private boolean marked;
	private BoardTagVO boardTag;
	private List<CommentVO> commentList;
}
