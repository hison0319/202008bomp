package com.bomp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	private int memberId;
	private String memberKakao;
	private String memberNick;
	private Timestamp regDate;
	private int followerCnt;
	private int followingCnt;
	private int activityFigures;
	private int alert;
	private int membershipLevel;
	private int boardLike;
	private int commentLike;
	private int boardComment;
	private int commentRecomment;
}
