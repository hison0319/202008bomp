package com.bomp.domain;

import lombok.Data;

@Data
public class CommentAndBoardDto {
	private int boardId;
	private int bestCommentLikeCnt;
	private int commentId;
	private int likeCnt;
}
