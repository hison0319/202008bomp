package com.bomp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AlertVO {
	private int alertId;
	private int memberId;	//보내는이
	private int alertMemberId;	//받는이
	private String alertComment;
	private Timestamp uDate;
	private int checked;
	private int kind;
	private String alertUserComment;
	private int bocoreId;
	private String memberNick;
}
